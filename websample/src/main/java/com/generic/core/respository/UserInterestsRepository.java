package com.generic.core.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generic.core.model.entities.UserInterests;
import com.generic.core.model.entities.Users;

@Repository
public interface UserInterestsRepository extends JpaRepository<UserInterests, Users>{

}
