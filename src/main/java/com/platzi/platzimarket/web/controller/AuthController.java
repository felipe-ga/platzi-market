package com.platzi.platzimarket.web.controller;

import com.platzi.platzimarket.domain.dto.AutenticationRequest;
import com.platzi.platzimarket.domain.dto.AutenticationResponse;
import com.platzi.platzimarket.domain.service.PlatziUserDetailsService;
import com.platzi.platzimarket.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PlatziUserDetailsService platziUserDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AutenticationResponse> createToken(@RequestBody AutenticationRequest autenticationRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(autenticationRequest.getUsername(),autenticationRequest.getPassword()));
            UserDetails userDetails = platziUserDetailsService.loadUserByUsername(autenticationRequest.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AutenticationResponse(jwt),HttpStatus.OK);
        }catch (BadCredentialsException e){
            System.out.println("error :" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
