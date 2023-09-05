package br.com.twgl.restapi;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloWorld() throws Exception {

        return "Hello World";
    }
}
