package com.amatta.scheduler.amatta_back.app.user.repository;

import com.amatta.scheduler.amatta_back.app.user.domain.UserMaster;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface UserMasterRepository extends CrudRepository<UserMaster,String>, JpaSpecificationExecutor<UserMaster> {
    Optional<UserMaster> findByEmail(String email);
}
