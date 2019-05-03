package com.esliceu.puncher.data.repository;

import com.esliceu.puncher.data.model.Signing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SigningRepository extends CrudRepository<Signing, Long> {

    @Query("select s from Signing s inner join s.user u where u.id=?1")
    List<Signing> findByUserId(Long userId);
}

