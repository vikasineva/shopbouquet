package com.ua.rosella.request;

import jakarta.validation.constraints.*;
import java.util.Date;

public class RegisterRequest {
    @NotNull(message = "Пошта не може бути порожньою")
    @NotEmpty(message = "Пошта не може бути порожньою")
    @NotBlank(message = "Пошта не може бути порожньою")
    //@Email(message = "Невірний формат пошти") // pass if value is like 'example@gmail'
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Невірний формат пошти")
    String email;
    @NotNull(message = "Пароль не може бути порожнім")
    @NotEmpty(message = "Пароль не може бути порожнім")
    @NotBlank(message = "Пароль не може бути порожнім")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).+$", message = "Пароль повинен мати хоча б одну велику і малу літери") //^(?=.*[a-zA-Z])(?=.*\d).{6,20}$
    @Pattern(regexp = ".*\\d.*", message = "Пароль повинен мати хоча б одну цифру")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()\\-_=+\\\\\\[\\]{};:\\'\",.<>/?]).+$", message = "Пароль повинен мати хоча б один спецсимвол")
    @Pattern(regexp = ".{8,20}", message = "Пароль повинен мати не менше 8 символів та не більше 20")
    String password;
    @NotNull(message = "Ім'я не може бути порожнім")
    @NotEmpty(message = "Ім'я не може бути порожнім")
    @NotBlank(message = "Ім'я не може бути порожнім")
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯіІїЇґҐєЄёЁ]+$", message = "Ім'я повинно складатися лише з літер без пробілів")
    @Pattern(regexp = "^.{2,30}$", message = "Ім'я повинно мати від 2 до 30 літер")
    String firstName;
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯіІїЇґҐєЄёЁ]+$", message = "Прізвище повинно складатися лише з літер без пробілів")
    @Pattern(regexp = "^.{2,30}$", message = "Прізвище повинно мати від 2 до 30 літер")
    String lastName;
    Date creationDate;
    @NotNull(message = "Номер телефону не може бути порожнім")
    @NotEmpty(message = "Номер телефону не може бути порожнім")
    @NotBlank(message = "Номер телефону не може бути порожнім")
    @Pattern(regexp = "^\\d{10}$", message = "Номер телефону повинен складатися з 10 цифр")
    String phone;
    Boolean enabled;
    Date birthday;

    public RegisterRequest() {}
    public RegisterRequest(@NotNull(message = "Пошта не може бути порожньою") String email,
                           @NotNull(message = "Пароль не може бути порожнім") String password,
                           @NotNull(message = "Ім'я не може бути порожнім") String firstName,
                           String lastName,
                           Date creationDate,
                           @NotNull(message = "Номер телефону не може бути порожнім") String phone,
                           Boolean enabled,
                           Date birthday) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.phone = phone;
        this.enabled = enabled;
        this.birthday = birthday;
    }

    public @NotNull(message = "Пошта не може бути порожньою") String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "Пошта не може бути порожньою") String email) {
        this.email = email;
    }

    public @NotNull(message = "Пароль не може бути порожнім") String getPassword() {
        return password;
    }

    public void setPassword(@NotNull(message = "Пароль не може бути порожнім") String password) {
        this.password = password;
    }

    public @NotNull(message = "Ім'я не може бути порожнім") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "Ім'я не може бути порожнім") String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public @NotNull(message = "Номер телефону не може бути порожнім") String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull(message = "Номер телефону не може бути порожнім") String phone) {
        this.phone = phone;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
