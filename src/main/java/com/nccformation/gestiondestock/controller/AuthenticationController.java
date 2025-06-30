package com.nccformation.gestiondestock.controller;

import com.nccformation.gestiondestock.dto.AuthenticationRequest;
import com.nccformation.gestiondestock.dto.AuthenticationResponse;
import com.nccformation.gestiondestock.dto.UtilisateurDto;
import com.nccformation.gestiondestock.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UtilisateurDto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
} 