package br.com.twgl.restapi;

import br.com.twgl.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Tadeu");
        person.setLastName("Wohlers");
        person.setGender("Male");
        person.setAddress("Rua São Paulo, 178 - Toledo MG");

        return person;
    }

    public List<Person> findAll() {

        logger.info("Finding all persons!");

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person){
        logger.info("Create person!");

        return person;
    }

    public Person update(Person person){
        logger.info("Update person!");

        return person;
    }

    public void delete(String id){
        logger.info("Delete person!");
    }

    private Person mockPerson(int i)
    {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Tadeu " + i);
        person.setLastName("Wohlers");
        person.setGender("Male");
        person.setAddress("Rua São Paulo, 178 - Toledo MG");

        return person;
    }

}
