package ru.kpfu.itis.revibe.service.impl.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.revibe.dto.auth.LoginDto;
import ru.kpfu.itis.revibe.dto.auth.RegisterDto;
import ru.kpfu.itis.revibe.dto.auth.UserDto;
import ru.kpfu.itis.revibe.entity.user.User;
import ru.kpfu.itis.revibe.repository.user.UserRepository;
import ru.kpfu.itis.revibe.security.JwtUtil;
import ru.kpfu.itis.revibe.service.interfaces.user.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDto register(RegisterDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFullName(dto.getFullName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        userRepository.save(user);

        UserDto ud = new UserDto();
        ud.setId(user.getId());
        ud.setEmail(user.getEmail());
        ud.setFullName(user.getFullName());
        ud.setRole(user.getRole());
        return ud;
    }

    @Override
    public String login(LoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getId(), user.getRole().name());
    }

    @Override
    public UserDto getMe(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        UserDto ud = new UserDto();
        ud.setId(user.getId());
        ud.setEmail(user.getEmail());
        ud.setFullName(user.getFullName());
        ud.setRole(user.getRole());
        return ud;
    }
}