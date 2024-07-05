package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.dto.ProjectDto;
import com.pintharuHomes.Backend.entity.Project;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.mapper.ProjectMapper;
import com.pintharuHomes.Backend.repository.ProjectRepository;
import com.pintharuHomes.Backend.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static com.pintharuHomes.Backend.service.impl.PaintServiceImpl.getImageData;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    private final String FOLDER_PATH = "D:/sem6/Project II/Pintharu-Homes-Backend/src/main/java/com/pintharuHomes/Backend/upload_project_img/";
    @Override
    public ProjectDto creteProject(MultipartFile file1,
                                   MultipartFile file2,
                                   MultipartFile file3,
                                   MultipartFile file4,
                                   ProjectDto projectDto) {
        String filePath1 = FOLDER_PATH+file1.getOriginalFilename();
        String filePath2 = FOLDER_PATH+file2.getOriginalFilename();
        String filePath3 = FOLDER_PATH+file3.getOriginalFilename();
        String filePath4 = FOLDER_PATH+file4.getOriginalFilename();

        Project savedProject = projectRepository.save(Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .image1(filePath1)
                .image2(filePath2)
                .image3(filePath3)
                .image4(filePath4)
                .build());

        try {
            file1.transferTo(new File(filePath1));
            file2.transferTo(new File(filePath2));
            file3.transferTo(new File(filePath3));
            file4.transferTo(new File(filePath4));
        }catch (Exception e){
            System.out.println(e);
        }

        return ProjectMapper.mapToProjectDto(savedProject);
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(
                project -> {
                    ProjectDto projectDto = ProjectMapper.mapToProjectDto(project);
                    try {
                        projectDto.setImageData1(PaintServiceImpl.getImageData(project.getImage1()));
                        projectDto.setImageData2(PaintServiceImpl.getImageData(project.getImage2()));
                        projectDto.setImageData3(PaintServiceImpl.getImageData(project.getImage3()));
                        projectDto.setImageData4(PaintServiceImpl.getImageData(project.getImage4()));
                    }catch (Exception e){
                        throw new RuntimeException();
                    }
                    return projectDto;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public Integer getProjectsCount() {
        List<Project> projects = projectRepository.findAll();
        return projects.size();
    }

    @Override
    public ProjectDto getProjectById(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No paint exist for ID:" + id));
        ProjectDto projectDto = ProjectMapper.mapToProjectDto(project);
        try {
            projectDto.setImageData1(PaintServiceImpl.getImageData(project.getImage1()));
            projectDto.setImageData2(PaintServiceImpl.getImageData(project.getImage2()));
            projectDto.setImageData3(PaintServiceImpl.getImageData(project.getImage3()));
            projectDto.setImageData4(PaintServiceImpl.getImageData(project.getImage4()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return projectDto;
    }

    @Override
    public ProjectDto updateProject(Integer id, MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, ProjectDto projectDto) {
        String filePath1 = FOLDER_PATH+file1.getOriginalFilename();
        String filePath2 = FOLDER_PATH+file2.getOriginalFilename();
        String filePath3 = FOLDER_PATH+file3.getOriginalFilename();
        String filePath4 = FOLDER_PATH+file4.getOriginalFilename();

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No paint exist for ID:" + id));
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setImage1(filePath1);
        project.setImage2(filePath2);
        project.setImage3(filePath3);
        project.setImage4(filePath4);

        try {
            file1.transferTo(new File(filePath1));
            file2.transferTo(new File(filePath2));
            file3.transferTo(new File(filePath3));
            file4.transferTo(new File(filePath4));
        }catch (Exception e){
            System.out.println(e);
        }
        Project saveedProject = projectRepository.save(project);
        return ProjectMapper.mapToProjectDto(saveedProject);
    }

    @Override
    public void deleteProject(Integer id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No paint exist for ID:" + id));

        String filePath1 = project.getImage1();
        String filePath2 = project.getImage2();
        String filePath3 = project.getImage3();
        String filePath4 = project.getImage4();

        if (filePath1 != null || filePath2 != null || filePath3 != null || filePath4 != null) {
            File file1 = new File(filePath1);
            File file2 = new File(filePath2);
            File file3 = new File(filePath3);
            File file4 = new File(filePath4);
            if (file1.exists() || file2.exists() || file3.exists() || file4.exists()) {
                file1.delete();
                file2.delete();
                file3.delete();
                file4.delete();
            }
        }

        projectRepository.deleteById(id);
    }
}
