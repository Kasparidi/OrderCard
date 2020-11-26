package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldReturnAllowedSymbols() {
        $("[data-test-id=name] input").setValue("Марина Пробкина");
        $("[data-test-id=phone] input").setValue("+79161111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void ifUseEnglishLetters() {
        $("[data-test-id=name] input").setValue("Marina Probkina");
        $("[data-test-id=phone] input").setValue("+79161111111");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name.input_invalid.input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void ifUseLettersInsteadNumbers() {
        $("[data-test-id=name] input").setValue("Натали Орейро");
        $("[data-test-id=phone] input").setValue("lkjhgfdsaiu");
        $("[data-test-id=agreement]").click();
        $("button").click();
        SelenideElement form = $("[data-test-id=phone]");
        form.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void ifUseTenNumberInsteadEleven() {
        $("[data-test-id=name] input").setValue("Натали Орейро");
        $("[data-test-id=phone] input").setValue("+1234567890");
        $("[data-test-id=agreement]").click();
        $("button").click();
        SelenideElement form = $("[data-test-id=phone]");
        form.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void ifNotUseCheck() {
        $("[data-test-id=name] input").setValue("Натали Орейро");
        $("[data-test-id=phone] input").setValue("+12345678901");
        $("button").click();
        SelenideElement form = $("[data-test-id=agreement]");
        form.$(".checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй.")).shouldBe(cssValue(".input_invalid", "color:#ff5c5c!important"));
    }

    @Test
    public void ifStayEmptyFieldName() {
        $("[data-test-id=phone] input").setValue("+1234567890");
        $("[data-test-id=agreement]").click();
        $("button").click();
        SelenideElement form = $("[data-test-id=name]");
        form.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void ifStayEmptyFieldPhone() {
        $("[data-test-id=name] input").setValue("Натали Орейро");
//        $("[data-test-id=phone] input").setValue("+1234567890");
        $("[data-test-id=agreement]").click();
        $("button").click();
        SelenideElement form = $("[data-test-id=phone]");
        form.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}