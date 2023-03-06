package com.example.basic.controller;

import com.example.basic.UserDetailsServiceImpl;
import com.example.basic.entity.AuthRequest;
import com.example.basic.filter.JwtResponse;
import com.example.basic.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class newStudentController {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authentication = (authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())));
        if (authentication.isAuthenticated()) {

            return userDetailsService.createJwtToken(authRequest);
//            return jwtUtil.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }

    }

    @GetMapping("/gogo")
    public String hello(){
        return "go india";
    }



}

