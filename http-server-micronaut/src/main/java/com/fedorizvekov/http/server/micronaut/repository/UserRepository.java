package com.fedorizvekov.http.server.micronaut.repository;

import java.util.List;
import com.fedorizvekov.http.server.micronaut.model.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User save(User entity);


    @Override
    List<User> findAll();

}
