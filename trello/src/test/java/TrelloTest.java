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

    String pet_id[];
    String boardName="Yeni";
    String id;
    Response response;
    public TrelloTest(){

        baseURI="https://api.trello.com/";
    }


    @Test
    @Order(1)
    public void createBoard(){

        System.out.println(baseURI+"1/boards?name="+boardName+"&key=ab543fc7e0d4d68aa6b9540c8e487103&token=04cb6aa875ef1291d9bbc445768574e0719dd0036c0feb90e5a294ecb0308ac6");
        response = given()
                .header("Content-Type","application/json")
                .when()
                .post(baseURI+"1/boards?name="+boardName+"&key=ab543fc7e0d4d68aa6b9540c8e487103&token=04cb6aa875ef1291d9bbc445768574e0719dd0036c0feb90e5a294ecb0308ac6")
                .then()
                //status kod direkt burada kontrol edildi.
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();



    }


    @Test
    @Order(2)
    public void createCard(){
        String [] name={"card1","card2"};

       for(int i=0;i<2;i++) {

           response = given()
                   .header("Content-Type", "application/json")
                   .when()
                   .delete(baseURI +"1/cards?name=" + name[i] + "&key=ab543fc7e0d4d68aa6b9540c8e487103&token=04cb6aa875ef1291d9bbc445768574e0719dd0036c0feb90e5a294ecb0308ac6&idList=63251143f6fd4101fcbe916c")
                   .then()
                   //status kod direkt burada kontrol edildi.
                   .statusCode(200)
                   .contentType(ContentType.JSON)
                   .extract().response();

           List<?> elements = response.jsonPath().get("id");
           pet_id[i] = elements.get(1).toString();



       }

    }


    @Test
    @Order(3)
    public void deleteCard(){
        for(int i=0;i<2;i++) {

            response = given()
                    .header("Content-Type", "application/json")
                    .when()
                    .post(baseURI +"1/cards?id=" + pet_id[i] + "&key=ab543fc7e0d4d68aa6b9540c8e487103&token=04cb6aa875ef1291d9bbc445768574e0719dd0036c0feb90e5a294ecb0308ac6&idList=63251143f6fd4101fcbe916c")
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
                .delete(baseURI +"1/boards?"+"632516f1347e6f015b611e5f?"+ "&key=ab543fc7e0d4d68aa6b9540c8e487103&token=04cb6aa875ef1291d9bbc445768574e0719dd0036c0feb90e5a294ecb0308ac6&idList=63251143f6fd4101fcbe916c")
                .then()
                //status kod direkt burada kontrol edildi.
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().response();
    }


}


