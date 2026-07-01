package com.lsy.projectapi.project.controller;

import com.lsy.projectapi.project.dto.ProjectRequest;
import com.lsy.projectapi.project.dto.ProjectResponse;
import com.lsy.projectapi.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
    private final ProjectService projectService;

    /**
     * 목록 조회(페이지네이션)
     * @param pageable
     * @param keyword
     * @return
     */
    @GetMapping
    public Page<ProjectResponse> getProjects(Pageable pageable, @RequestParam(required = false, name = "keyword") String keyword) {
        return projectService.getProjects(pageable, keyword);
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
