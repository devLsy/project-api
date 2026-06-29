package com.lsy.projectapi.project.service;

import com.lsy.projectapi.project.dto.ProjectRequest;
import com.lsy.projectapi.project.entity.Project;
import com.lsy.projectapi.project.repository.ProjectRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Commit
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void 프로젝트_등록() {
        // given
        ProjectRequest request = new ProjectRequest();
        request.setName("Vue3");

        Project savedProject = Project.builder()
                                        .id(1L)
                                        .name("Vue3")
                                        .build();

        when(projectRepository.save(any(Project.class))).thenReturn(savedProject);

        // when
        var response = projectService.save(request);

        // then
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Vue3");

        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    @Disabled
    void 프로젝트_삭제() {
        // when
        projectService.delete(1L);
        // then
        verify(projectRepository).deleteById(1L);
    }

    @Test
    @Disabled
    void 프로젝트_조회() {

        when(projectRepository.findAll())
                .thenReturn(List.of(Project.builder()
                                                .id(1L)
                                                .name("Vue")
                                                .build()
                ));

        var result = projectService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Vue");
    }

    @Test
    @Disabled
    void 프로젝트_수정() {
        Project project = Project.builder()
                .id(1L)
                .name("React")
                .build();

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        when(projectRepository.save(any(Project.class)))
                .thenReturn(project);

        ProjectRequest request = new ProjectRequest();
        request.setName("Vue3");

        var response = projectService.update(1L, request);

        assertThat(response.getName()).isEqualTo("Vue3");
    }
}