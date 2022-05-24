package com.elenamukhina.task_3_1_4.repository;

import com.elenamukhina.task_3_1_4.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserServiceCrudRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
