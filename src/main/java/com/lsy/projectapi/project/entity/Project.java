package com.lsy.projectapi.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="PROJECT")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

   @Column(name = "NAME", nullable = false)
    private String name;
}
