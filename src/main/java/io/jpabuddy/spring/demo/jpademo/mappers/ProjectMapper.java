package io.jpabuddy.spring.demo.jpademo.mappers;

import io.jpabuddy.spring.demo.jpademo.dtos.ProjectDto;
import io.jpabuddy.spring.demo.jpademo.entities.Project;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProjectMapper {
    Project projectDtoToProject(ProjectDto projectDto);

    ProjectDto projectToProjectDto(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromProjectDto(ProjectDto projectDto, @MappingTarget Project project);

    @AfterMapping
    default void linkTasks(@MappingTarget Project project) {
        project.getTasks().forEach(task -> task.setProject(project));
    }
}
