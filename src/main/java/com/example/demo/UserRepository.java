package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserClassByUserName(String userName);
    User findByUserName(String userName);
    User findByEmail(String email);
}
