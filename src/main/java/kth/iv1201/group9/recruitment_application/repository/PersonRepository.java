package kth.iv1201.group9.recruitment_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kth.iv1201.group9.recruitment_application.domain.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByUsername(String username);
}
