package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoTests {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenValidJson_whenPostCollectionResource_thenReturnMethodNotAllowed() {
        HttpEntity<Object> requestEntity = createRequestEntity(new Person());
        ResponseEntity<String> actual = template.exchange("/people", HttpMethod.POST, requestEntity, String.class);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, actual.getStatusCode());
    }

    @Test
    public void givenNoJson_whenPostCollectionResource_thenReturnMethodNotAllowed() {
        HttpEntity<Object> requestEntity = createRequestEntity(null);
        ResponseEntity<String> actual = template.exchange("/people", HttpMethod.POST, requestEntity, String.class);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, actual.getStatusCode());
    }

    @Test
    public void givenInvalidJson_whenPostCollectionResource_thenReturnMethodNotAllowed() {
        HttpEntity<Object> requestEntity = createRequestEntity("{INVALID_JSON}");
        ResponseEntity<String> actual = template.exchange("/people", HttpMethod.POST, requestEntity, String.class);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, actual.getStatusCode());
    }

    private HttpEntity<Object> createRequestEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

}
