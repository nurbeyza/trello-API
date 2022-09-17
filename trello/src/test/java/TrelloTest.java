import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloTest {

    static String pet_id[];
    String boardName="Yeni";
    static String id;
    static String key="ab543fc7e0d4d68aa6b9540c8e487103";
    static String token="04cb6aa875ef1291d9bbc445768574e0719dd0036c0feb90e5a294ecb0308ac6";
    Response response;
    public TrelloTest(){

        baseURI="https://api.trello.com/";

    }


    @Test
    @Order(1)
    public void createBoard(){

        response = given()
                .header("Content-Type","application/json")
                .when()
                .queryParam("key",key)
                .queryParam("token",token )
                .post(baseURI+"1/boards?name="+boardName)
                .then()
                //status kod direkt burada kontrol edildi.
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();

        id= response.then().contentType(ContentType.JSON).extract().path("id");


    }

    @Test
    @Order(2)
    public void createCard(){
        String [] name={"card1","card2"};

       for(int i=0;i<2;i++) {
           System.out.println(baseURI +"1/cards?name=" + name[i] + "&idList="+id);
           response = given()
                   .header("Content-Type", "application/json")
                   .when()
                   .queryParam("key",key)
                   .queryParam("token",token )
                   .post(baseURI +"1/cards?name=" + name[i] + "&idList=6325a7e055d2cc010f83a595")
                   .then()
                   //status kod direkt burada kontrol edildi.
                   .statusCode(200)
                   .contentType(ContentType.JSON)
                   .extract().response();

          pet_id[i] = response.then().contentType(ContentType.JSON).extract().path("id");



       }

    }


    @Test
    @Order(3)
    public void deleteCard(){
        for(int i=0;i<2;i++) {

            response = given()
                    .header("Content-Type", "application/json")
                    .when()
                    .queryParam("key",key)
                    .queryParam("token",token )
                    .delete(baseURI +"1/cards?id=" + pet_id[i] + "&idList=6325a7e055d2cc010f83a595")
                    .then()
                    //status kod direkt burada kontrol edildi.
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract().response();


        }

    }



    @Test
    @Order(4)
    public void deleteBoard(){
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .queryParam("key",key)
                .queryParam("token",token )
                .delete(baseURI +"1/boards/"+id)
                .then()
                //status kod direkt burada kontrol edildi.
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
    }


}


