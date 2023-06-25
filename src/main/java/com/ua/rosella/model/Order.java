package com.ua.rosella.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ua.rosella.request.OrderRequest;
import com.ua.rosella.util.ObjectIdDeserializer;
import com.ua.rosella.util.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Document(collection = "Orders")
public class Order {
    @Id
    @JsonSerialize(using = ObjectIdSerializer.class)
    @JsonDeserialize(using = ObjectIdDeserializer.class)
    ObjectId id;
    @DBRef
    User admin;
    @DBRef
    User customer;

    public static class Recipient{
        String firstName;
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

    Recipient recipient;

    public static class Delivery{

        Date arrivalDate;
        String city;
        String street;
        String house;
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
    Delivery delivery;
    Boolean isPaid;
    @Field(name = "items")
    List<OrderDetail> items;
    PaymentMethod kindPay;
    public static class StatusStamp{
        OrderStatus status;
        Date changeTime;

        public StatusStamp(OrderStatus status, Date changeTime) {
            this.status = status;
            this.changeTime = changeTime;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public void setStatus(OrderStatus status) {
            this.status = status;
        }

        public Date getChangeTime() {
            return changeTime;
        }

        public void setChangeTime(Date changeTime) {
            this.changeTime = changeTime;
        }
    }

    public class OrderDetail {
        @DBRef
        Product item;
        Double price;
        Integer amount;

        public OrderDetail(Product item, Double price, Integer amount) {
            this.item = item;
            this.price = price;
            this.amount = amount;
        }

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

    List<StatusStamp> history;

    Integer orderNumber;

    Boolean isAnonymous;

    String postcardText;

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

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

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
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

    public List<StatusStamp> getHistory() {
        return history;
    }

    public void setHistory(List<StatusStamp> history) {
        this.history = history;
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

    public Double getTotalPrice(){
        double total = 0.0;
        for(OrderDetail detail : items){
            total += detail.getPrice()* detail.getAmount();
        }
        return total;
    }

    public Order(User admin, User customer, Recipient recipient, Delivery delivery, Boolean isPaid, List<OrderDetail> items, PaymentMethod kindPay, List<StatusStamp> history, Integer orderNumber, Boolean isAnonymous, String postcardText) {
        this.admin = admin;
        this.customer = customer;
        this.recipient = recipient;
        this.delivery = delivery;
        this.isPaid = isPaid;
        this.items = items;
        this.kindPay = kindPay;
        this.history = history;
        this.orderNumber = orderNumber;
        this.isAnonymous = isAnonymous;
        this.postcardText = postcardText;
    }

    public Order(OrderRequest request){
        this.recipient = new Recipient();
        this.recipient.setFirstName(request.getRecipient().getFirstName());
        this.recipient.setPhone(request.getRecipient().getPhone());
        this.delivery = new Delivery();
        this.delivery.setArrivalDate(request.getDelivery().getArrivalDate());
        this.delivery.setCity(request.getDelivery().getCity());
        this.delivery.setStreet(request.getDelivery().getStreet());
        this.delivery.setHouse(request.getDelivery().getHouse());
        this.delivery.setApartment(request.getDelivery().getApartment());
        this.items = new LinkedList<>();
        for(OrderRequest.OrderDetail detail: request.getItems()){
            this.items.add(new Order.OrderDetail(
                    detail.getItem(),
                    detail.getPrice(),
                    detail.getAmount()
            ));
        }
        this.kindPay = request.getKindPay();
        this.isAnonymous = request.getIsAnonymous();
        this.postcardText = request.getPostcardText();
        this.history = new LinkedList<>();
    }
}
