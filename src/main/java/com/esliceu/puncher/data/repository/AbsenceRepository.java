package com.esliceu.puncher.data.repository;

import com.esliceu.puncher.data.model.Absence;
import org.springframework.data.repository.CrudRepository;

public interface AbsenceRepository extends CrudRepository<Absence, Integer> {
    Absence findAbsenceById(Integer absenceId);
}
