package com.scaler.productservice.commons;

import com.scaler.productservice.dtos.UserDto;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthCommons {
    private static RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static boolean validateToken(String token) {
        // todo: Implement Client Side Load Balancing
        UserDto userDto = restTemplate.getForObject("http://localhost:8080/user/validate/{token}", UserDto.class);
        System.out.println("Validating Token");
        if(userDto == null) {
            return false;
        }
        return true;
    }
}
