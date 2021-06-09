package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cardsInfo =$$(".list__item [data-test-id]");
    private static SelenideElement firstBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] .button");
    private static SelenideElement secondBalance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] .button");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public Dashboard() {
    }

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransactionPage pushFirstCard() {
        firstBalance.click();
        return new TransactionPage();
    }

    public TransactionPage pushSecondCard() {
        secondBalance.click();
        return new TransactionPage();
    }

    public int getCardBalance(String id) {
        val cardBalance1 = $(".list__item [data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").getText();
        val cardBalance2 = $(".list__item [data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").getText();
        return extractBalance(id);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
