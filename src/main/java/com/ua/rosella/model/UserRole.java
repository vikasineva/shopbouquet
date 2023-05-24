package com.ua.rosella.model;

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
