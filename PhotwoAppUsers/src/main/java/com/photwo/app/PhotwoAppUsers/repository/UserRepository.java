package com.photwo.app.PhotwoAppUsers.repository;

import com.photwo.app.PhotwoAppUsers.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
