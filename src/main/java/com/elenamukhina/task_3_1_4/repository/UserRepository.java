package com.elenamukhina.task_3_1_4.repository;

import com.elenamukhina.task_3_1_4.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}



