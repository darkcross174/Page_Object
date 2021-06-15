package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.getBalanceOfFirstCardAfterTransfer;
import static ru.netology.web.data.DataHelper.getBalanceOfSecondCardAfterTransfer;

public class MoneyTransferTest {

    @BeforeEach
    void setUpAll() {
        val DashboardPage = new DashboardPage();
        String numberFirstCard = "5559 0000 0000 0001";
        String numberSecondCard = "5559 0000 0000 0002";
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void  shouldTransferZeroAmountOnFirstCard() {
        val amount = 500;
        val balanceOfFirstCardBefore = DashboardPage.getCardBalanceFirstCard();
        val balanceOfSecondCardBefore = DashboardPage.getCardBalanceSecondCard();
        val transactionPage = DashboardPage.pushSecondCard();
        val cardInfo = DataHelper.getFirstCard();
        transactionPage.transactionCard(cardInfo, amount);
        val balanceAfterTransactionFirstCard = getBalanceOfFirstCardAfterTransfer (balanceOfFirstCardBefore, amount);
        val balanceAfterTransactionSecondCard = getBalanceOfSecondCardAfterTransfer(balanceOfSecondCardBefore, amount);
        val balanceOfFirstCardAfter = DashboardPage.getCardBalanceFirstCard();
        val balanceOfSecondCardAfter = DashboardPage.getCardBalanceSecondCard();
        assertEquals(balanceAfterTransactionFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransactionSecondCard, balanceOfSecondCardAfter);
    }

    @Test
    void shouldTransactionFromSecondToFirst() {
        val dashboardPage = new DashboardPage();
        val amount = 800;
        val balanceOfFirstCardBefore = DashboardPage.getCardBalanceFirstCard();
        val balanceOfSecondCardBefore = DashboardPage.getCardBalanceSecondCard();
        val transactionPage = dashboardPage.pushFirstCard();
        val cardInfo = DataHelper.getSecondCard();
        transactionPage.transactionCard(cardInfo, amount);
        val balanceAfterTransactionFirstCard = getBalanceOfSecondCardAfterTransfer(balanceOfFirstCardBefore, amount);
        val balanceAfterTransactionSecondCard = getBalanceOfFirstCardAfterTransfer(balanceOfSecondCardBefore, amount);
        val balanceOfFirstCardAfter = DashboardPage.getCardBalanceFirstCard();
        val balanceOfSecondCardAfter = DashboardPage.getCardBalanceSecondCard();
        assertEquals(balanceAfterTransactionFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransactionSecondCard, balanceOfSecondCardAfter);
    }


    @Test
    void shouldNotTransactionMoreThanRestOfBalance() {
        val dashboardPage = new DashboardPage();
        val amount = 10500;
        val transactionPage = dashboardPage.pushFirstCard();
        val cardInfo = DataHelper.getSecondCard();
        transactionPage.transactionCard(cardInfo, amount);
        transactionPage.getNotification();
    }
}
