package com.ua.rosella.model;
import com.ua.rosella.token.Token;
import org.bson.BsonObjectId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Document(collection = "Users")
public class User implements UserDetails {
    @Id
    ObjectId id;
    String email;
    String password;
    @Field(name="first_name")
    String firstName;
    @Field(name="last_name")
    String lastName;
    @Field(name="pic_profile")
    String picProfile;
    @Field(name="creation_date")
    Date creationDate;
    UserRole role;
    List<Order> orders;
    String phone;
    Boolean enabled;
    Date birthday;

    private List<Token> tokens = new LinkedList<>();
    public User() {}

    public User(String email, String password, String firstName, String lastName, String picProfile, Date creationDate, UserRole role, String phone, Boolean enabled, Date birthday) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picProfile = picProfile;
        this.creationDate = creationDate;
        this.role = role;
        this.phone = phone;
        this.enabled = enabled;
        this.birthday = birthday;
    }

    public User(String email, String password, String firstName, String picProfile, Date creationDate, UserRole role, String phone, Boolean enabled) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.picProfile = picProfile;
        this.creationDate = creationDate;
        this.role = role;
        this.phone = phone;
        this.enabled = enabled;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicProfile() {
        return picProfile;
    }

    public void setPicProfile(String picProfile) {
        this.picProfile = picProfile;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
    public void addToken(Token token) {
        List<Token> newTokensList = new LinkedList<Token>(tokens);
        newTokensList.add(token);
        setTokens(newTokensList);
    }
}
