package com.lsy.projectapi.project.service;

import com.lsy.projectapi.project.dto.ProjectRequest;
import com.lsy.projectapi.project.dto.ProjectResponse;
import com.lsy.projectapi.project.entity.Project;
import com.lsy.projectapi.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    /**
     * 목록 조회
     * @return
     */
    public List<ProjectResponse> findAll() {
        return projectRepository.findAll()
                .stream()
                .map(project -> ProjectResponse.builder()
                        .id(project.getId())
                        .name(project.getName())
                        .build())
                .toList();
    }

    /**
     * 저장
     * @param projectRequest
     * @return
     */
    public ProjectResponse save(ProjectRequest projectRequest) {
        Project project = Project.builder().name(projectRequest.getName()).build();
        Project saved = projectRepository.save(project);
        return ProjectResponse.builder().id(saved.getId()).name(saved.getName()).build();
    }

    /**
     * 수정
     * @param id
     * @param projectRequest
     * @return
     */
    public ProjectResponse update(Long id, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(id).orElseThrow();
        project.setName(projectRequest.getName());

        Project updated = projectRepository.save(project);
        return ProjectResponse.builder().id(updated.getId()).name(updated.getName()).build();
    }

    /**
     * 삭제
     * @param id
     */
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
