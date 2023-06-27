package com.ua.rosella.request;


import com.ua.rosella.model.PaymentMethod;
import com.ua.rosella.model.Product;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

public class OrderRequest {
    public static class Recipient{
        @NotNull(message = "Ім'я не може бути порожнім")
        @NotEmpty(message = "Ім'я не може бути порожнім")
        @NotBlank(message = "Ім'я не може бути порожнім")
        @Pattern(regexp = "^[a-zA-Zа-яА-ЯіІїЇґҐєЄёЁ]+$", message = "Ім'я повинно складатися лише з літер без пробілів")
        @Pattern(regexp = "^.{2,30}$", message = "Ім'я повинно мати від 2 до 30 літер")
        String firstName;
        @NotNull(message = "Номер телефону не може бути порожнім")
        @NotEmpty(message = "Номер телефону не може бути порожнім")
        @NotBlank(message = "Номер телефону не може бути порожнім")
        @Pattern(regexp = "^\\d{10}$", message = "Номер телефону повинен складатися з 10 цифр")
        String phone;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class Delivery{

        @NotNull(message = "Дата і час доставки не можуть бути порожніми")
        @NotEmpty(message = "Дата і час доставки не можуть бути порожніми")
        @NotBlank(message = "Дата і час доставки не можуть бути порожніми")
        @Future(message = "Дата і час доставки не можуть бути у минулому")
        Date arrivalDate;
        @NotNull(message = "Місто не може бути порожнім")
        @NotEmpty(message = "Місто не може бути порожнім")
        @NotBlank(message = "Місто не може бути порожнім")
        @Pattern(regexp = "^[a-zA-Zа-яА-ЯіІїЇґҐєЄёЁ ]+$", message = "Назва міста повинна складатися лише з літер")
        String city;
        @NotNull(message = "Вулиця не може бути порожньою")
        @NotEmpty(message = "Вулиця не може бути порожньою")
        @NotBlank(message = "Вулиця не може бути порожньою")
        @Pattern(regexp = "^[a-zA-Zа-яА-ЯіІїЇґҐєЄёЁ ]+$", message = "Назва вулиці повинна складатися лише з літер")
        String street;
        @NotNull(message = "Номер дому не може бути порожнім")
        @NotEmpty(message = "Номер дому не може бути порожнім")
        @NotBlank(message = "Номер дому не може бути порожнім")
        @Pattern(regexp = "^[a-zA-Zа-яА-ЯіІїЇґҐєЄёЁ\\d]+$", message = "Номер дому повинен складатися лише з цифр або з додаванням літер")
        String house;
        @Pattern(regexp="^\\d+$", message = "Номер квартири повинен складатися лише з цифр")
        Integer apartment;

        public Date getArrivalDate() {
            return arrivalDate;
        }

        public void setArrivalDate(Date arrivalDate) {
            this.arrivalDate = arrivalDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getHouse() {
            return house;
        }

        public void setHouse(String house) {
            this.house = house;
        }

        public Integer getApartment() {
            return apartment;
        }

        public void setApartment(Integer apartment) {
            this.apartment = apartment;
        }
    }

    public static class OrderDetail {
        Product item;
        Double price;

        Integer amount;

        public Product getItem() {
            return item;
        }

        public void setItem(Product item) {
            this.item = item;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }

    Recipient recipient;

    Delivery delivery;

    List<OrderDetail> items;

    PaymentMethod kindPay;

    Boolean isAnonymous;

    String postcardText;

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public List<OrderDetail> getItems() {
        return items;
    }

    public void setItems(List<OrderDetail> items) {
        this.items = items;
    }

    public PaymentMethod getKindPay() {
        return kindPay;
    }

    public void setKindPay(PaymentMethod kindPay) {
        this.kindPay = kindPay;
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public String getPostcardText() {
        return postcardText;
    }

    public void setPostcardText(String postcardText) {
        this.postcardText = postcardText;
    }
}
