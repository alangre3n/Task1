package ru.dexsys.steps;

import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import ru.dexsys.RestfulBooker;

public class MySteps {

    @Когда("^Пользователь авторизуется на сайте$")
    public void пользовательАвторизуетсяНаСайте() {
        RestfulBooker.authorization();
        System.out.println("Пользователь успешно авторизовался на сайте");
    }

    @И("^Пользователь добавляет заказ$")
    public void пользовательДобавляетЗаказ() {
        RestfulBooker.createBooking();
        System.out.println("Пользователь успешно добавил заказ");
    }

    @Тогда("^Пользователь проверяет свой заказ$")
    public void пользовательПроверяетСвойЗаказ() {
        RestfulBooker.checkBooking();
    }
}
