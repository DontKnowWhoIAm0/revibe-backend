package ru.kpfu.itis.revibe.service.impl.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.revibe.dto.auth.LoginDto;
import ru.kpfu.itis.revibe.dto.auth.RegisterDto;
import ru.kpfu.itis.revibe.entity.user.User;
import ru.kpfu.itis.revibe.repository.user.UserRepository;
import ru.kpfu.itis.revibe.security.JwtUtil;
import ru.kpfu.itis.revibe.service.interfaces.user.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtils;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String register(RegisterDto registerDto) {
        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setFullName(registerDto.getFullName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(registerDto.getRole() != null ? registerDto.getRole() : ru.kpfu.itis.revibe.entity.enums.Role.USER);
        userRepository.save(user);

        return jwtUtils.generateToken(user.getId(), user.getRole().name());
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtils.generateToken(user.getId(), user.getRole().name());
    }
}
