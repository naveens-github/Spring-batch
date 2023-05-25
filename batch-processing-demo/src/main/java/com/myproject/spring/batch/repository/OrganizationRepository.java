package com.myproject.spring.batch.repository;

import com.myproject.spring.batch.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

}
