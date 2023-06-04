package com.ua.rosella.repository;

import com.ua.rosella.model.User;
import com.ua.rosella.token.Token;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    //@Query("{email: '?0'}")
    User findUserByEmail(String email);

}
