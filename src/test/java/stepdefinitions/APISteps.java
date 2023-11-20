package stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.messages.JSON;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;

import static Hooks.API.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class APISteps {
    JSONObject expData;
    HashMap<String, Object> reqBody;
    Response response;
    String token;

    @Given("Token create etmek icin gerekli olan path parametresi set edilir")
    public void token_create_etmek_icin_gerekli_olan_path_parametresi_set_edilir() {

        spec.pathParam("pp1", "gettoken");


    }

    @Then("Get token Post methodu icin gerekli olan reqBody olusturulur")
    public void get_token_post_methodu_icin_gerekli_olan_req_body_olusturulur() {

        reqBody = new HashMap<>();
        reqBody.put("email", "banuyurdakul.admin@agileswiftcargo.com");
        reqBody.put("password", "123123123");


    }

    @Then("Get token icin gerekli olan request gönderilir")
    public void get_token_icin_gerekli_olan_request_gönderilir() {

        response = given().spec(spec).contentType(ContentType.JSON).when().body(reqBody).post("/{pp1}");
        // response.prettyPeek();

    }

    @Then("Get token requesti icin donen Response bodysinden Token degeri alinir")
    public void get_token_requesti_icin_donen_response_bodysinden_token_degeri_alinir() {
        JsonPath respJP = response.jsonPath();

        token = respJP.get("data.token");
        System.out.println("Token :" + token);
    }


    @Given("Get Hub id api icin gerekli path parametreleri set edilir")
    public void get_hub_id_api_icin_gerekli_path_parametreleri_set_edilir() {
        spec.pathParams("pp1", "hub", "pp2", 1);
    }

    @Then("Get Hub id api icin Expected Data hazirlanir")
    public void get_hub_id_api_icin_expected_data_hazirlanir() {
        /*
        {
            "id": 1,
            "name": "New York City",
            "phone": "01000000001",
            "address": "New York City, New York",
            "current_balance": "0.00",
            "status": 1,
            "created_at": "2023-08-01T14:12:21.000000Z",
            "updated_at": "2023-08-01T14:12:21.000000Z"
        }
         */
        expData = new JSONObject();
        expData.put("id", 1);
        expData.put("name", "New York City");
        expData.put("phone", "01000000001");
        expData.put("address", "New York City, New York");
        expData.put("current_balance", "0.00");
        expData.put("status", 1);
        expData.put("created_at", "2023-08-01T14:12:21.000000Z");
        expData.put("updated_at", "2023-08-01T14:12:21.000000Z");
    }

    @Then("Get Hub id icin get Request yapilir")
    public void get_hub_id_icin_get_request_yapilir() {
        response = given().spec(spec).header("Authorization", "Bearer " + token).when().get("/{pp1}/{pp2}");

    }
    @Then("Get Hub id icin donen response'in dogrulamasi yapilir")
    public void get_hub_id_icin_donen_response_in_dogrulamasi_yapilir() {

        JsonPath respJP = response.jsonPath();
        assertEquals( expData.get("id")    , respJP.get("id")    );
        assertEquals( expData.get("name")    , respJP.get("name")    );
        assertEquals( expData.get("phone")    , respJP.get("phone")    );
        assertEquals( expData.get("address")    , respJP.get("address")    );
        assertEquals( expData.get("current_balance")    , respJP.get("current_balance")    );
        assertEquals( expData.get("status")    , respJP.get("status")    );
        assertEquals( expData.get("created_at")    , respJP.get("created_at")    );
        assertEquals( expData.get("updated_at")    , respJP.get("updated_at")    );


    }

}