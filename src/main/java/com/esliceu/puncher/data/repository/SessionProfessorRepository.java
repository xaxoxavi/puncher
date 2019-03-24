package com.esliceu.puncher.data.repository;

import com.esliceu.puncher.data.model.ProfessorSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionProfessorRepository extends CrudRepository<ProfessorSession, Long> {

}
