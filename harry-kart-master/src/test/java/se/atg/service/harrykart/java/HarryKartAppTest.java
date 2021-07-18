package se.atg.service.harrykart.java;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("java-test")
public class HarryKartAppTest {

    private final static URI harryKartApp = URI.create("http://localhost:8181/java/api/play");

    @BeforeAll
    void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("Trying to GET instead of POST should return 405 Method not allowed")
    void useGetOnPostEndpointShouldNotBePossible() {
        when()
                .get(harryKartApp)
                .then()
                .assertThat()
                .statusCode(405);
    }

    @Test
    @DisplayName("The application knows how to play")
    void play() {
        String result = "{\"ranking\":[{\"position\":1,\"horse\":\"HERCULES BOKO\"},{\"position\":2,\"horse\":\"TIMETOBELUCKY\"},{\"position\":3,\"horse\":\"WAIKIKI SILVIO\"}]}";
        String inputRequest = "<harryKart><numberOfLoops>3</numberOfLoops><startList><participant><lane>1</lane><name>TIMETOBELUCKY</name><baseSpeed>10</baseSpeed></participant><participant><lane>2</lane><name>CARGO DOOR</name><baseSpeed>10</baseSpeed></participant><participant><lane>3</lane><name>HERCULES BOKO</name><baseSpeed>10</baseSpeed></participant><participant><lane>4</lane><name>WAIKIKI SILVIO</name><baseSpeed>10</baseSpeed></participant></startList><powerUps><loop number=\"1\"><lane number=\"1\">6</lane><lane number=\"2\">10</lane><lane number=\"3\">4</lane><lane number=\"4\">0</lane></loop><loop number=\"2\"><lane number=\"1\">0</lane><lane number=\"2\">-10</lane><lane number=\"3\">5</lane><lane number=\"4\">15</lane></loop></powerUps></harryKart>\n";
        given()
                .header("Content-Type", ContentType.XML)
                .body(inputRequest)
                .when()
                .post(harryKartApp)

                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .header("Content-Type", ContentType.JSON.toString())
                .and()
                .body(equalTo(result));
    }
}
