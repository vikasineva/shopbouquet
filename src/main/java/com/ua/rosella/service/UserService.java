package com.ua.rosella.service;

import com.ua.rosella.model.User;
import com.ua.rosella.repository.UserRepository;
import com.ua.rosella.token.Token;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByUserEmail(String userEmail) {
        var user = userRepository.findUserByEmail(userEmail);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return Optional.of(user);
    }

    // valid means expired OR revoked equals false
    public List<Token> getValidTokensByUserId(ObjectId id) {
        // find user with specified email
        MatchOperation matchUserId = Aggregation.match(Criteria.where("_id").is(id));

        // unwind array of token objects
        UnwindOperation unwindTokens = Aggregation.unwind("tokens");

        // find tokens with expired or revoked false
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("tokens.expired").is(false),
                Criteria.where("tokens.revoked").is(false)
        );
        MatchOperation matchExpiredRevokedTokens = Aggregation.match(criteria);

        // display only array tokens
        ProjectionOperation displayOnlyTokens = Aggregation.project()
                .and("tokens.token").as("token")
                .and("tokens.tokenType").as("tokenType")
                .and("tokens.expired").as("expired")
                .and("tokens.revoked").as("revoked");

        TypedAggregation<Token> aggregation = Aggregation.newAggregation(Token.class, matchUserId, unwindTokens, matchExpiredRevokedTokens, displayOnlyTokens);
        AggregationResults<Token> results = mongoTemplate.aggregate(aggregation, Token.class);
        return results.getMappedResults();
    }

    public Optional<Token> getTokenByItself(String token) {
        // unwind array of token objects
        UnwindOperation unwindTokens = Aggregation.unwind("tokens");

        // display only array tokens
        ProjectionOperation displayOnlyTokens = Aggregation.project()
                .and("tokens.token").as("token")
                .and("tokens.tokenType").as("tokenType")
                .and("tokens.expired").as("expired")
                .and("tokens.revoked").as("revoked");

        // find tokens by itself
        MatchOperation matchToken = Aggregation.match(Criteria.where("token").is(token));

        TypedAggregation<Token> aggregation = Aggregation.newAggregation(Token.class, unwindTokens, displayOnlyTokens, matchToken);
        AggregationResults<Token> results = mongoTemplate.aggregate(aggregation, Token.class);
        return Optional.ofNullable(results.getUniqueMappedResult());
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
