package com.esliceu.puncher.data.repository;

import com.esliceu.puncher.data.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  StudentRepository extends CrudRepository<Student, String> {

}