package com.igka.dsm.pactconsumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.*;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
public class RetrievingPricePactTest {
    @Pact(consumer = "PriceCalculator", provider = "ProductCatalog")
    RequestResponsePact getAllProducts(PactDslWithProvider builder) {
        return builder.given("product code exists")
                .uponReceiving("retrieve price")
                .method("GET")
                .path("/products/993.283.34/price")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(newJsonBody(object -> {                    object.stringType("id", "993.283.34");
                    object.stringType("name", "APPLARO");
                    object.stringType("price", "179.83");
                    object.stringType("currency", "EUR");}).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getAllProducts")
    void getProductPrice_whenProductsExist(MockServer mockServer) {
        Price expectedPrice = new Price("179.83", "EUR");

        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        Price price = new ProductService(restTemplate).getPrice("993.283.34");

        assertEquals(expectedPrice, price);
    }

}
