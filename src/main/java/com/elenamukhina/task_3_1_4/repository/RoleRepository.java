package com.elenamukhina.task_3_1_4.repository;

import com.elenamukhina.task_3_1_4.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
