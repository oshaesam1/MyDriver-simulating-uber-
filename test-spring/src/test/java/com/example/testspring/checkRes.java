package com.example.testspring;

//import org.springframework.boot.test.context.SpringBootApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class checkRes  {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldPassIfStringMatches(){
        assertEquals("Hello world from spring boot",
                testRestTemplate.getForObject("http://localhost:"+port+
                        "/",String.class));

    }
}
