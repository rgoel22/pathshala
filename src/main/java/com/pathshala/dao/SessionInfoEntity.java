package com.pathshala.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "sessioninfo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfoEntity extends MetaData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="userid")
    private Long userId;
    @Column(name = "sessiontoken")
    private String sessionToken;
    @Column(name = "isactive")
    private Boolean isActive;
}
