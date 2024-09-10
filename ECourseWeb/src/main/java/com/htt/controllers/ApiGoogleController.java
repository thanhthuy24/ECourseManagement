/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htt.controllers;

/**
 *
 * @author Admin
 */
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.htt.components.GoogleService;
import com.htt.components.JwtService;
import com.htt.service.UserService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiGoogleController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/google")
    @CrossOrigin
    public ResponseEntity<String> googleLogin(@RequestBody Map<String, String> payload) {
        String token = payload.get("token");
        System.out.println("Received token: " + token);
        try {
            GoogleIdToken.Payload googlePayload = GoogleService.verifyToken(token);

            String username = (String) googlePayload.get("name");
            String email = (String) googlePayload.getEmail();
            String lastName = (String) googlePayload.get("given_name");
            String firstName = (String) googlePayload.get("family_name");
            String avatar = (String) googlePayload.get("picture");
            userService.addUserGG(username, email, firstName, lastName, avatar);

            String jwtToken = jwtService.generateTokenLogin(username);
            return ResponseEntity.ok(jwtToken);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Google Token");
        }
    }
}
