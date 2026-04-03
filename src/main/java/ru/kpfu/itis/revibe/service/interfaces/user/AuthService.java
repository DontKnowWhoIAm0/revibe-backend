package ru.kpfu.itis.revibe.service.interfaces.user;

import ru.kpfu.itis.revibe.dto.auth.LoginDto;
import ru.kpfu.itis.revibe.dto.auth.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
