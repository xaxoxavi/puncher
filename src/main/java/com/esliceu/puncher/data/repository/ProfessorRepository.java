package com.esliceu.puncher.data.repository;

import com.esliceu.puncher.data.model.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long> {

}
