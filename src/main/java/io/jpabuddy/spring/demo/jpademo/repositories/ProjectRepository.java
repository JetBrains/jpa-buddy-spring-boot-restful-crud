package io.jpabuddy.spring.demo.jpademo.repositories;

import io.jpabuddy.spring.demo.jpademo.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByName(String name);

    @Query("select p from Project p " +
            "where p.endDate is null " +
            "order by p.name")
    List<Project> findActiveProjects();
}