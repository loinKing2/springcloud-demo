package com.imooc.springcloud;

public class AuthServiceImpl implements IAuthService{
    @Override
    public AuthResponse login(String username, String password) {
        return null;
    }

    @Override
    public AuthResponse authVerifyToken(String token, String username) {
        return null;
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        return null;
    }
}
