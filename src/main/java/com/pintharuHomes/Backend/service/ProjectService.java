package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.ProjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {
    public ProjectDto creteProject(MultipartFile file1,
                                   MultipartFile file2,
                                   MultipartFile file3,
                                   MultipartFile file4,
                                   ProjectDto projectDto);

    List<ProjectDto> getAllProjects();

    Integer getProjectsCount();

    ProjectDto getProjectById(Integer id);

    ProjectDto updateProject(Integer id,
                             MultipartFile file1,
                             MultipartFile file2,
                             MultipartFile file3,
                             MultipartFile file4,
                             ProjectDto projectDto);

    void deleteProject(Integer id);
}
