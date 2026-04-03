package com.abhishek.devcollab.auth;

import com.abhishek.devcollab.dto.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 🔐 LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

    // 🆕 REGISTER API
    @PostMapping("/register")
    public String register(@RequestBody LoginRequestDTO request) {
        return authService.register(request);
    }
}