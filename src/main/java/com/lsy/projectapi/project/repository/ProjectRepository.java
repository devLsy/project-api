package com.lsy.projectapi.project.repository;


import com.lsy.projectapi.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
