package br.com.twgl.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id){
        logger.info("Finding one person!");

        return repository.findById(id).orElseThrow();
    }

    public List<Person> findAll() {

        logger.info("Finding all persons!");

        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Create person!");

        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Update person!");

        Person entity = repository.findById(person.getId()).orElseThrow();
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Delete person!");

        Person entity = repository.findById(id).orElseThrow();

        repository.delete(entity);
    }

}
