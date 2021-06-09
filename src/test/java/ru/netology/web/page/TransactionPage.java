package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;



import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private final SelenideElement amountField = $("[data-test-id=amount] input");
    private final SelenideElement fromField = $("[data-test-id=from] input");
    private final SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public TransactionPage() {
        $(withText("Пополнение карты")).shouldBe(visible);
    }

    public DashboardPage transferMoneyFromCardToAnotherCard(DataHelper.CardInfo, int transferAmount) {
        String str = String.valueOf(transferAmount);
        amountField.setValue(String.valueOf(transferAmount));
        fromField.setValue(DataHelper.CardInfo.getCardNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void getNotification () {
        $(withText("На балансе недостаточно средств")).shouldBe(Condition.visible);
    }

}
