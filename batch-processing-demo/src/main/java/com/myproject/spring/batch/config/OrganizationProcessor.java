package com.myproject.spring.batch.config;

import com.myproject.spring.batch.entity.Organization;
import org.springframework.batch.item.ItemProcessor;

public class OrganizationProcessor implements ItemProcessor<Organization, Organization> {
    @Override
    public Organization process(Organization organization) throws Exception {
        return organization;
    }
}
