package com.ua.rosella.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AuthenticationRequest {
    @NotNull(message = "Пошта не може бути порожньою")
    @NotEmpty(message = "Пошта не може бути порожньою")
    @NotBlank(message = "Пошта не може бути порожньою")
    String userEmail;
    @NotNull(message = "Пароль не може бути порожнім")
    @NotEmpty(message = "Пароль не може бути порожнім")
    @NotBlank(message = "Пароль не може бути порожнім")
    String password;

    public AuthenticationRequest() {}

    public AuthenticationRequest(@NotNull(message = "Пошта не може бути порожньою") String userEmail,
                                 @NotNull(message = "Пароль не може бути порожнім") String password) {
        this.userEmail = userEmail;
        this.password = password;
    }

    public @NotNull(message = "Пошта не може бути порожньою") String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(@NotNull(message = "Пошта не може бути порожньою") String userEmail) {
        this.userEmail = userEmail;
    }

    public @NotNull(message = "Пароль не може бути порожнім") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Пароль не може бути порожнім") String password) {
        this.password = password;
    }
}
