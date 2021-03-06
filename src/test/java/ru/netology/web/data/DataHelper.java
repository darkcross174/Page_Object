package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    @AllArgsConstructor
    public static class CardInfo {
        private String cardNumber;
        private String cardBalance;
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559000000000001", "10000");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559000000000002", "10000");
    }

    public static int getBalanceOfFirstCardAfterTransfer(int balance, int amount) {
        int newBalance = balance - amount;
        return newBalance;

    }

    public static int getBalanceOfSecondCardAfterTransfer(int balance, int amount) {
        int newBalance = balance + amount;
        return newBalance;
    }

}
