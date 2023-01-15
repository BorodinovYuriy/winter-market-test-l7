package ru.gb.wintermarket.core.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.gb.wintermarket.api.dto.ProductDto;
import ru.gb.wintermarket.core.converters.ProductConverter;
import ru.gb.wintermarket.core.entity.Category;
import ru.gb.wintermarket.core.entity.Product;
import ru.gb.wintermarket.core.repositories.CategoryRepository;
import ru.gb.wintermarket.core.repositories.ProductRepository;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductConverter productConverter;


    //Не работает
    @Test
    void saveNewProduct() {
        Product p = new Product();
        p.setTitle("testProduct");
        p.setPrice(1111);
        p.setCategory(categoryRepository.findById(1L).orElseThrow());
        ProductDto pDto = productConverter.entityToDto(p);

        Page<ProductDto> returnPdto = webTestClient.post()
                .uri("/api/v1/products")
                .bodyValue(pDto)
                .exchange()
                .expectBody(Page.class)
                .returnResult().getResponseBody();




    }

    //Не работает
    @Test
    void saveTest() {
        Category c = categoryRepository.findById(1L).orElseThrow();
        log.warn("!!!!!!!!!!!!!!"+c.getTitle());

        Product p = new Product();
        p.setTitle("testProd");
        p.setPrice(111);
        p.setCategory(categoryRepository.findById(1L).orElseThrow());

        Product httpProduct = webTestClient.post()
                .uri("/api/v1/products/test")
                .bodyValue(p)
                .exchange()
                .expectBody(Product.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(httpProduct.getTitle(), p.getTitle());

    }


    @Test
    void findProductById() {
        ProductDto productByHttp = webTestClient.get()
                .uri("/api/v1/products/1")
                .exchange()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();
        log.warn(productByHttp.getTitle());

        Assertions.assertNotNull(productByHttp);
        Assertions.assertEquals(productByHttp.getTitle(),productRepository.findById(productByHttp.getId()).orElseThrow().getTitle());
    }
}