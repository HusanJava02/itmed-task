package com.exmple.it_med_task.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint( columnNames = {"value","system_url"}))
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Valid
    private Identifier identifier;

    @Column(name = "patient")
    private String patient;

    @Column(name = "practitioner")
    private String practitoner;

    @Column(name = "organization",unique = true)
    private String organization;
}
