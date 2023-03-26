package br.com.twgl.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") Long id) throws Exception {

        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() throws Exception {

        return service.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) throws Exception {

        return service.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) throws Exception {

        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}