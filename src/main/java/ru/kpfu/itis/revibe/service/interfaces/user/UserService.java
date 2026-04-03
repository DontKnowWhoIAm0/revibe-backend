package ru.kpfu.itis.revibe.service.interfaces.user;

import ru.kpfu.itis.revibe.dto.auth.LoginDto;
import ru.kpfu.itis.revibe.dto.auth.RegisterDto;
import ru.kpfu.itis.revibe.dto.auth.UserDto;

public interface UserService {
    UserDto register(RegisterDto dto);
    String login(LoginDto dto);
    UserDto getMe(String email);
}