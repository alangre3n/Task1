package ru.dexsys;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class RestfulBooker {
    private static final String authUri = "https://restful-booker.herokuapp.com/auth";
    private static final String createBookingUri = "https://restful-booker.herokuapp.com/booking/";
    private static int idOrder = 0;
    private static JsonPath idJsonFirst;

    private static final String authBody = "{\n" +
            "    \"username\" : \"admin\",\n" +
            "    \"password\" : \"password123\"\n" +
            "}";

    private static final String createBookingBody = "{\n" +
            "    \"firstname\" : \"Alan\",\n" +
            "    \"lastname\" : \"Gimatov\",\n" +
            "    \"totalprice\" : 1000000,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2021-01-01\",\n" +
            "        \"checkout\" : \"2022-01-01\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Brunch\"\n" +
            "}";

    public static void authorization(){
       RestAssured.given()
                .body(authBody)
                .contentType(ContentType.JSON)
                .post(authUri)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200);
    }

    public static void createBooking(){
        idJsonFirst = given()
                .body(createBookingBody)
                .contentType(ContentType.JSON)
                .post(createBookingUri)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .statusCode(200)
                .extract()
                .response()
                .jsonPath();
        idOrder = idJsonFirst.getInt("bookingid");
    }

    public static void checkBooking(){
        JsonPath idJsonSecond = given()
                .contentType(ContentType.JSON)
                .get(createBookingUri + idOrder)
                .then()
                .log().ifValidationFails(LogDetail.ALL)
                .extract()
                .response()
                .jsonPath();
        // К сожалению не нашел более изящного способа
        if(idJsonFirst.getString("booking").equals(idJsonSecond.getString(""))){
            System.out.println("Заказ успешно прошел проверку");
        }else{
            System.out.println("Заказ отстствует на сайте");
        }
    }
}
