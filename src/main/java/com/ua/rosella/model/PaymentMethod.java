package com.ua.rosella.model;

public enum PaymentMethod {


    CASH {
        @Override
        public String toString() {
            return "Готівка";
        }
    },
    BANK_CARD{
        @Override
        public String toString() {
            return "Банківська карта";
        }
    }

}
