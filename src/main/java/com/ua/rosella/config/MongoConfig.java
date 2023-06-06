package com.ua.rosella.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    private static final String CONNECTION_STRING = "mongodb+srv://%s:%s@%s/%s";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "fte3tbEqnKGUsqhr";
    private static final String HOST = "rosella.f5cthio.mongodb.net";
    private static final String DATABASE = "Rosella";

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient(), getDatabaseName()));
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory() {
        ConnectionString connectionString = new ConnectionString(
                String.format(CONNECTION_STRING, USERNAME, PASSWORD, HOST, DATABASE));

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return new SimpleMongoClientDatabaseFactory(MongoClients.create(mongoClientSettings), DATABASE);
    }

    /*@Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }*/

    @Override
    protected String getDatabaseName() {
        return DATABASE;
    }


}

