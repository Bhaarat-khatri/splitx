package com.spendsense.splitx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.UserTransactionMapping;

@Repository
public interface UserTransactionMappingRepository extends JpaRepository<UserTransactionMapping, Long>{

}
