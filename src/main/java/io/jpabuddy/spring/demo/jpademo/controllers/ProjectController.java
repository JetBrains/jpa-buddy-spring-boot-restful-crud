package io.jpabuddy.spring.demo.jpademo.controllers;

import io.jpabuddy.spring.demo.jpademo.dtos.ProjectDto;
import io.jpabuddy.spring.demo.jpademo.entities.Project;
import io.jpabuddy.spring.demo.jpademo.mappers.ProjectMapper;
import io.jpabuddy.spring.demo.jpademo.repositories.ProjectRepository;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @GetMapping("/project/find/{name}")
    public List<ProjectDto> findByName(@PathVariable String name) {
        List<Project> projectList = projectRepository.findByName(name);
        return projectList.stream().map(projectMapper::projectToProjectDto).collect(Collectors.toList());
    }

    @PostMapping("/project/new")
    public ProjectDto saveProject(@RequestBody @NonNull @Valid ProjectDto projectDto) {
        Project projectEntity = projectMapper.projectDtoToProject(projectDto);
        return projectMapper.projectToProjectDto(projectRepository.save(projectEntity));
    }

    @PostMapping("/project/update")
    public ProjectDto updateProject(@RequestBody @NonNull @Valid ProjectDto projectDto) {
        if (projectDto.getId() == null)
            throw new IllegalArgumentException("Project ID is missing. Use /new to create a project");
        Project projectEntity = projectRepository.findById(projectDto.getId()).orElseThrow(EntityNotFoundException::new);

        projectMapper.updateProjectFromProjectDto(projectDto, projectEntity);
        return projectMapper.projectToProjectDto(projectRepository.save(projectEntity));
    }
}