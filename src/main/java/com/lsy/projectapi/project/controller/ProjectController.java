package com.lsy.projectapi.project.controller;

import com.lsy.projectapi.project.dto.ProjectRequest;
import com.lsy.projectapi.project.dto.ProjectResponse;
import com.lsy.projectapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
    private final ProjectService projectService;

    /**
     * 목록 조회
     * @return
     */
    @GetMapping
    public List<ProjectResponse> findAll() {
        return projectService.findAll();
    }

    /**
     * 저장
     * @param request
     * @return
     */
    @PostMapping
    public ProjectResponse save(@RequestBody ProjectRequest request) {
        return projectService.save(request);
    }

    /**
     * 수정
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/{id}")
    public ProjectResponse update(
            @PathVariable("id") Long id,
            @RequestBody ProjectRequest request) {

        return projectService.update(id, request);
    }

    /**
     * 삭제
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        projectService.delete(id);
    }
}
