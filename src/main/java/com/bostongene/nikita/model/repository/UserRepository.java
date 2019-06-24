package com.bostongene.nikita.model.repository;

import com.bostongene.nikita.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsByEmail(String email);
}
