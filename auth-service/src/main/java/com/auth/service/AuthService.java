package com.auth.service;

import com.utility.web.request.user.LoginRequest;
import com.utility.web.response.user.AuthResponse;

public interface AuthService {

    AuthResponse authenticateUser(LoginRequest loginRequest);

    Long getUserIdFromToken(String token);

    String createPasswordResetToken(String email);

    Boolean validateToken(String token);
}
