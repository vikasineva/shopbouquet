package com.ua.rosella.service;

import com.ua.rosella.model.User;
import com.ua.rosella.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LogoutService implements LogoutHandler {
    private final UserService userService;
    private final JwtService jwtService;

    public LogoutService(UserService userService, JwtService jwtService) {
        this.userService =  userService;
        this.jwtService = jwtService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        jwt = authHeader.substring(7); // 7 because previous letters are 'Bearer '
        userEmail = jwtService.extractUsername(jwt);

        Token storedToken = userService.getTokenByItself(jwt).orElse(null);
        if (storedToken != null) {
            User user = userService.getUserByUserEmail(userEmail);
            List<Token> tempList = new LinkedList<>(user.getTokens());
            int tokenIndex = tempList.indexOf(tempList.stream().filter(token -> jwt.equals(token.getToken())).findAny().orElse(null));

            storedToken.setExpired(true);
            storedToken.setRevoked(true);

            tempList.set(tokenIndex, storedToken);
            user.setTokens(tempList);

            userService.save(user);
        }
    }
}
