package com.ua.rosella.model;

public enum OrderStatus {
    NEW{
        @Override
        public String toString() {
            return "Нове";
        }
    },
    ACCEPTED{
        @Override
        public String toString() {
            return "Прийняте";
        }
    },
    SHIPPED{
        @Override
        public String toString() {
            return "Відправлене";
        }
    },
    DELIVERED{
        @Override
        public String toString() {
            return "Доставлене";
        }
    },
    CANCELLED{
        @Override
        public String toString() {
            return "Скасоване";
        }
    }
}
