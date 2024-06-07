package com.pintharuHomes.Backend.mapper;

import com.pintharuHomes.Backend.dto.ProjectDto;
import com.pintharuHomes.Backend.entity.Project;

public class ProjectMapper {

    public static ProjectDto mapToProjectDto(Project project){
        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                null,
                null,
                null,
                null
        );
    }

    public static Project mapToProject(ProjectDto projectDto){
        return new Project(
                projectDto.getId(),
                projectDto.getName(),
                projectDto.getDescription(),
                null,
                null,
                null,
                null
        );
    }
}
