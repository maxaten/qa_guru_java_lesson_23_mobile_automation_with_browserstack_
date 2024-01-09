package test;

import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;


@Tag("android")
public class SearchTestSelenide extends TestBase {


    String name1 = "Appium";
    String name2 = "Batman";

    @Test
    void successfulSearchTest() {
        step("Кликнуть по полю поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step(String.format("Вставить значение %s", name1), () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(name1);
        });

        step("Проверка искомого запроса", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_title")).shouldHave(sizeGreaterThan(0));
        });
    }

    @Test
    void successfulSearchAndClickTest() {
        String name2 = "Batman";

        step("Кликнуть по полю поиска", () -> {
            $(accessibilityId("Search Wikipedia")).click();
        });

        step(String.format("Вставить значение %s", name2), () -> {
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys(name2);
        });

        step(String.format("Выбрать элемент %s в списке", name2), () -> {
            $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_container")).first().click();
        });

        step(String.format("Выбрать элемент %s в списке", name2), () -> {
            $(xpath("//android.webkit.WebView[@text='Batman']"))
                    .shouldHave(visible)
                    .shouldHave(text(name2));
        });
    }
}
