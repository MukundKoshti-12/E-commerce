package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.SignupRequestDto;
import com.scaler.userservice.dtos.LoginRequestDto;
import com.scaler.userservice.dtos.UserDto;
import com.scaler.userservice.exceptions.InvalidTokenException;
import com.scaler.userservice.exceptions.PasswordMismatchException;
import com.scaler.userservice.models.User;
import com.scaler.userservice.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignupRequestDto signupRequestDto){

        User user = userService.signup(signupRequestDto.getName(), signupRequestDto.getEmail(), signupRequestDto.getPassword());

        return UserDto.from(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) throws PasswordMismatchException {
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @GetMapping("/validate/{tokenValue}")
    public UserDto validateToken(@PathVariable("tokenValue") String tokenValue) throws InvalidTokenException {
        User user = userService.validateToken(tokenValue);

        return UserDto.from(user);
    }

    @GetMapping("/sample")
    public void sampleApi(){
        System.out.println("sampleApi called by ProductService");
    }

//    public  logout(){
//        return null;
//    }
}
