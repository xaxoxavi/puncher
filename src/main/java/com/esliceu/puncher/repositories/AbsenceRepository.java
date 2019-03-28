package com.esliceu.puncher.repositories;

import com.esliceu.puncher.data.model.Absence;
import org.springframework.data.repository.CrudRepository;

public interface AbsenceRepository extends CrudRepository<Absence, Integer> {
    Absence findAbsenceById(Integer absenceId);
}
