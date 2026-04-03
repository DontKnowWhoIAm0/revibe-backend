package ru.kpfu.itis.revibe.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.revibe.dto.auth.LoginDto;
import ru.kpfu.itis.revibe.dto.auth.RegisterDto;
import ru.kpfu.itis.revibe.dto.auth.UserDto;
import ru.kpfu.itis.revibe.entity.user.User;
import ru.kpfu.itis.revibe.repository.user.UserRepository;
import ru.kpfu.itis.revibe.security.JwtUtil;
import ru.kpfu.itis.revibe.service.interfaces.user.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDto dto) {
        return authService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto dto) {
        return authService.login(dto);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        var userId = jwtUtil.getUserIdFromJwt(token);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setRole(user.getRole());
        dto.setBonusPoints(user.getBonusPoints());

        return ResponseEntity.ok(dto);
    }
}