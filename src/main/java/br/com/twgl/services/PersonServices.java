package br.com.twgl.services;

import br.com.twgl.data.vo.v1.PersonVO;
import br.com.twgl.mapper.ModelMapper;
import br.com.twgl.model.Person;
import br.com.twgl.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public PersonVO findById(Long id){
        logger.info("Finding one person!");

        return ModelMapper.parseObject(repository.findById(id).orElseThrow(), PersonVO.class);
    }

    public List<PersonVO> findAll() {

        logger.info("Finding all persons!");

        return ModelMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person){
        logger.info("Create person!");

        var entity = ModelMapper.parseObject(person, Person.class);

        return ModelMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO person){
        logger.info("Update person!");

        Person entity = repository.findById(person.getId()).orElseThrow();
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());

        return ModelMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id){
        logger.info("Delete person!");

        Person entity = repository.findById(id).orElseThrow();

        repository.delete(entity);
    }

}
