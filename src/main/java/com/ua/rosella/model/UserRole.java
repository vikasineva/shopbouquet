package com.ua.rosella.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public enum UserRole {
    ADMIN {
        @Override
        public String toString() {
            return "Адміністратор";
        }
    },
    CLIENT{
        @Override
        public String toString() {
            return "Клієнт";
        }
    }
}
