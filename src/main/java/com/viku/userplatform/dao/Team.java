package com.viku.userplatform.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "team")
@Getter
@Setter
public class Team {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name="name")
    String name;
}
