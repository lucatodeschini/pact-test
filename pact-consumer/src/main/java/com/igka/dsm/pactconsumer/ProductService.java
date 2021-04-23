package com.igka.dsm.pactconsumer;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class ProductService {
    private RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Price getPrice(String id) {
        Product product = restTemplate.exchange("/products/" + id + "/price",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Product>() {
                }).getBody();

        return new Price(product.price, product.currency);
    }
}