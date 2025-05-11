package com.rjasw.demo.jpa_sql.repo;

import org.springframework.data.repository.CrudRepository;
import com.rjasw.demo.jpa_sql.entity.User;

public interface UserRepo extends CrudRepository<User, Integer> {

}
