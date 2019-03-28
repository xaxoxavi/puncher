package com.esliceu.puncher.repositories;

import com.esliceu.puncher.data.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByRfid(String rfid);
    User findByUsername(String username);

}
