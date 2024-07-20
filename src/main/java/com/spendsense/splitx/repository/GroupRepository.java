package com.spendsense.splitx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spendsense.splitx.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}