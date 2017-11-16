package com.amanuel.license.repositories;
import java.util.*;
import org.hibernate.mapping.List;
import org.springframework.data.repository.CrudRepository;

import com.amanuel.license.models.Person;

public interface PersonRepository extends CrudRepository<Person,Long>{
	ArrayList<Person> findAll();
}
