package com.scaler.userservice.services;

import com.scaler.userservice.dtos.SendEmailDto;
import com.scaler.userservice.exceptions.InvalidTokenException;
import com.scaler.userservice.exceptions.PasswordMismatchException;
import com.scaler.userservice.models.Token;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositories.TokenRepository;
import com.scaler.userservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;
    private SecretKey secretKey;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository, SecretKey secretKey, ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.secretKey = secretKey;
        this.objectMapper = objectMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public User signup(String name, String email, String password) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if(optionalUser.isPresent()){
//            // redirect to loginpage
//            return optionalUser.get();
//        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        SendEmailDto sendEmailDto = new SendEmailDto();
        sendEmailDto.setEmail(email);
        sendEmailDto.setSubject("Welcome to our Institution!");
        sendEmailDto.setBody("Welcome and new registraion has done!");

        kafkaTemplate.send(
                "sendWelcomeEmail",
                objectMapper.writeValueAsString(sendEmailDto)
        );

        return  userRepository.save(user);
    }

    @Override
    public String login(String email, String password) throws PasswordMismatchException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            // redirect to signuppage
            return null;
        }

        if(bCryptPasswordEncoder.matches(password,optionalUser.get().getPassword())){
            //// A JWT token consists of 3 parts
            //// 1st :- Header [Consists Base64 Encoded Algorithm name which is used do encrypt payload and Header]
            //// 2nd :- Payload [Consists Base64 Encoded User details]
            //// 3rd :- Signature [Consists Base64 Encoded Encrypted value of 1st and 2nd part of JWT]

        // -------------------------------Random Token Generation----------------------------------------

//            token.setTokenValue(RandomStringUtils.randomAlphanumeric(128));
//            Token token = new Token();
//            token.setUser(optionalUser.get());
//            Date today = new Date();
//            long thirtyDaysInMs = 30L * 24 * 60 * 60 * 1000;
//            Date futureDate = new Date(today.getTime() + thirtyDaysInMs);
//            token.setExpiryAt(futureDate);
//
//            String userData = "{\n" +
//                "   \"email\": \"deepak@gmail.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"ta\"\n" +
//                "   ],\n" +
//                "   \"expiryDate\": \"22ndSept2026\"\n" +
//                "}";
//
//            String jwtToken = Jwts.builder().content(userData).compact();
//            token.setTokenValue(jwtToken);
//
//            Token tokenVal = tokenRepository.save(token);
//
//            return tokenVal.getTokenValue();

        // ----------------------------------Proper Implementation of JWT------------------------------------

            //// We have to first set up our payload[claims]
            //// Then we have to select our Algorithm and then generate our secret key

            User user = optionalUser.get();

            Map<String, Object> claims = new HashMap<>();

            claims.put("iss","scaler.com");
            claims.put("userId", user.getId());

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, 30);
            Date expiryDate = calendar.getTime();

            claims.put("exp", expiryDate);
            claims.put("roles",user.getRoles());

            String jwtToken = Jwts.builder().claims(claims).signWith(secretKey).compact();

            Token token = new Token();
            token.setTokenValue(jwtToken);

            Token tokenVal = tokenRepository.save(token);

            return tokenVal.getTokenValue();

            //// Create a UserSession which stores the more client specific information as well, like browser name, IP address, etc. this will help us to provide one another level of security
            //// Store secret in AWS Vault [To store a single secret key, which doesn't get affected with server restart]

        }
        else{
            // Wrong Password
            throw new PasswordMismatchException("Incorrect Password");
        }
    }

    @Override
    public User validateToken(String token) throws InvalidTokenException {
//        Optional<Token> optionalToken = tokenRepository.findByTokenValueAndExpiryAtGreaterThan(token,  new Date());
//
//        if(optionalToken.isEmpty()){
//            throw new InvalidTokenException("Invalid Token");
//        }
//
//        Token tokenObject = optionalToken.get();
//
//        return tokenObject.getUser();

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        Long expiryTime = (Long) claims.get("exp");
        Long currentTime = Instant.now().getEpochSecond();

        if(expiryTime < currentTime){
            throw new InvalidTokenException("Invalid JWT Token");
        }

        // Safely extract as Long regardless of how it was serialized
        Long userId = claims.get("userId", Long.class);
        Optional<User> optionalUser = userRepository.findById(userId);

        return optionalUser.get();
    }
}
