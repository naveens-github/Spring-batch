package com.myproject.spring.batch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORG_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    @Id
    @Column(name = "ORG_INDEX")
    private int index;
    @Column(name = "ORG_ID")
    private String organizationId;
    @Column(name = "ORG_NAME")
    private String name;
    @Column(name = "ORG_WEBSITE")
    private String website;
    @Column(name = "ORG_COUNTRY")
    private String country;
    @Column(name = "ORG_DESCRIPTION")
    private String description;
    @Column(name = "ORG_FOUNDED")
    private String founded;
    @Column(name = "ORG_INDUSTRY")
    private String industry;
    @Column(name = "ORG_NUMOFEMPLOYEES")
    private int numberOfEmployees;

}
