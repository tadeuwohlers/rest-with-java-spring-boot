package br.com.twgl.services;

import br.com.twgl.data.vo.v1.PersonVO;
import br.com.twgl.mapper.ModelMapper;
import br.com.twgl.model.Person;
import br.com.twgl.repository.PersonRepository;
import br.com.twgl.restapi.PersonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO findById(Long id) throws Exception {
        logger.info("Finding one person!");

        PersonVO vo = ModelMapper.parseObject(repository.findById(id).orElseThrow(), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return vo;
    }

    public List<PersonVO> findAll() {

        logger.info("Finding all persons!");

        var persons = ModelMapper.parseListObjects(repository.findAll(), PersonVO.class);

        persons.stream().forEach(p ->
                {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        return persons;
    }

    public PersonVO create(PersonVO person) throws Exception {
        logger.info("Create person!");

        var entity = ModelMapper.parseObject(person, Person.class);

        var vo = ModelMapper.parseObject(repository.save(entity), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public PersonVO update(PersonVO person) throws Exception {
        logger.info("Update person!");

        Person entity = repository.findById(person.getId()).orElseThrow();
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        var vo = ModelMapper.parseObject(repository.save(entity), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getId())).withSelfRel());

        return vo;
    }

    public void delete(Long id){
        logger.info("Delete person!");

        Person entity = repository.findById(id).orElseThrow();

        repository.delete(entity);
    }

}
