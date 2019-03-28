package com.esliceu.puncher.repositories;

import com.esliceu.puncher.data.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {
}
