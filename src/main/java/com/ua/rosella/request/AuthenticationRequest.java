package com.ua.rosella.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AuthenticationRequest {
    @NotNull(message = "Пошта не може бути порожньою")
    @NotEmpty(message = "Пошта не може бути порожньою")
    @NotBlank(message = "Пошта не може бути порожньою")
    //@Email(message = "Невірний формат пошти") // pass if value is like 'example@gmail'
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Невірний формат пошти")
    String userEmail;
    @NotNull(message = "Пароль не може бути порожнім")
    @NotEmpty(message = "Пароль не може бути порожнім")
    @NotBlank(message = "Пароль не може бути порожнім")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).+$", message = "Пароль повинен мати хоча б одну велику і малу літери") //^(?=.*[a-zA-Z])(?=.*\d).{6,20}$
    @Pattern(regexp = ".*\\d.*", message = "Пароль повинен мати хоча б одну цифру")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()\\-_=+\\\\\\[\\]{};:\\'\",.<>/?]).+$", message = "Пароль повинен мати хоча б один спецсимвол")
    @Pattern(regexp = ".{8,20}", message = "Пароль повинен мати не менше 8 символів та не більше 20")
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
