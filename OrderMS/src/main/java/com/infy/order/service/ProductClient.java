package com.infy.order.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.infy.order.dto.ProductDto;

@Service
public class ProductClient {
    
    private final WebClient webClient;
    
    public ProductClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8100").build();
    }
    
    public ProductDto getProductById(Long productId) {
        return webClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }
}
