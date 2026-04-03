package com.abhishek.devcollab.auth;

import com.abhishek.devcollab.dto.LoginRequestDTO;
import com.abhishek.devcollab.user.User;
import com.abhishek.devcollab.user.UserRepository;
import com.abhishek.devcollab.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ REGISTER
    public String register(LoginRequestDTO request) {

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());

        // 🔐 encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔐 LOGIN
    public String login(LoginRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔐 compare encoded password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 🔥 generate JWT token
        return jwtUtil.generateToken(user.getEmail());
    }
}