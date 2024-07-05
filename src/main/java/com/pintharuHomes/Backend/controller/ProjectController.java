package com.pintharuHomes.Backend.controller;

import com.pintharuHomes.Backend.dto.ProjectDto;
import com.pintharuHomes.Backend.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;

    //        Build add project REST API
    @PostMapping("/createproject")
    public ResponseEntity<?> creteProject(@RequestParam("image1") MultipartFile file1,
                                          @RequestParam("image2") MultipartFile file2,
                                          @RequestParam("image3") MultipartFile file3,
                                          @RequestParam("image4") MultipartFile file4,
                                          @ModelAttribute ProjectDto projectDto){
        ProjectDto savedProject = projectService.creteProject(file1, file2, file3, file4, projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.OK);
    }

    //        Build get all project REST API
    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects(){
        List<ProjectDto> allProjects = projectService.getAllProjects();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    //        Build get all project REST API
    @GetMapping("/count")
    public ResponseEntity<Integer> getProjectsCount(){
        Integer projectsCount = projectService.getProjectsCount();
        return new ResponseEntity<>(projectsCount, HttpStatus.OK);
    }

    //        Build get project by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable("id") Integer id){
        ProjectDto project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    //        Build update project REST API
    @PutMapping("{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Integer id,
                                           @RequestParam("image1") MultipartFile file1,
                                           @RequestParam("image2") MultipartFile file2,
                                           @RequestParam("image3") MultipartFile file3,
                                           @RequestParam("image4") MultipartFile file4,
                                           @ModelAttribute ProjectDto projectDto){
        ProjectDto updatedProject = projectService.updateProject(id, file1, file2, file3, file4, projectDto);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Integer id){
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project Deleted Successfully");
    }
}
