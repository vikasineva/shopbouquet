package com.ua.rosella.model;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class Order {
    ObjectId id;
    User admin;
    User customer;
    public class Delivery{
        String city;
        String street;
        String house;
        Integer apartment;

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
    Delivery delivery;
    Boolean isPaid;
    List<OrderDetail> orderDetails;
    PaymentMethod kindPay;
    public class StatusStamp{
        OrderStatus status;
        Date date;

        public OrderStatus getStatus() {
            return status;
        }

        public void setStatus(OrderStatus status) {
            this.status = status;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
    List<StatusStamp> history;
}
