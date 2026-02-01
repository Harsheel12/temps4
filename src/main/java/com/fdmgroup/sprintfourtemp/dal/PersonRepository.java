package com.fdmgroup.sprintfourtemp.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.sprintfourtemp.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
