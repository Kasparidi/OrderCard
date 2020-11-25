package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderCardTest {

    @Test
    public void shouldReturnAllowedSymbols() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Марина Пробкина");
        $("[data-test-id=phone] input").setValue("+79161111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("p").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void ifUseEnglishLetters() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Marina Probkina");
        $("[data-test-id=phone] input").setValue("+79161111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}