package com.lsy.projectapi.project.service;

import com.lsy.projectapi.project.dto.ProjectRequest;
import com.lsy.projectapi.project.dto.ProjectResponse;
import com.lsy.projectapi.project.entity.Project;
import com.lsy.projectapi.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    /**
     * 목록 조회
     * @param pageable
     * @param keyword
     * @return
     */
    public Page<ProjectResponse> getProjects(Pageable pageable, String keyword) {

        Page<Project> projects = (keyword == null || keyword.isBlank())
            ? projectRepository.findAll(pageable)
            : projectRepository.findByNameContaining(keyword, pageable);

        return projects.map(project ->
            new ProjectResponse(project.getId(), project.getName())
        );
    }

    /**
     * 저장
     * @param projectRequest
     * @return
     */
    public ProjectResponse save(ProjectRequest projectRequest) {
        Project project = Project.builder().name(projectRequest.getName()).build();
        Project saved = projectRepository.save(project);
        return new ProjectResponse(saved.getId(), saved.getName());
    }

    /**
     * 수정
     * @param id
     * @param projectRequest
     * @return
     */
    public ProjectResponse update(Long id, ProjectRequest projectRequest) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setName(projectRequest.getName());

        Project updated = projectRepository.save(project);
        return new ProjectResponse(updated.getId(), updated.getName());
    }

    /**
     * 삭제
     * @param id
     */
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
