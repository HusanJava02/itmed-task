package com.exmple.it_med_task.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"value","system_url"}))
public class Identifier {

    @Column(name = "system_url")
    private String system;

    @Column(name = "value")
    private String value;
}
