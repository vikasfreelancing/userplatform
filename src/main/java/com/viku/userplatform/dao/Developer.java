package com.viku.userplatform.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "developer")
@Getter
@Setter
public class Developer {
    @Id
    @Column(name = "id")
    Long id;
    @Column(name="name")
    String name;

    @Column(name="phone_number")
    String phoneNumber;

    @Column(name="team_id")
    Long teamId;


}
