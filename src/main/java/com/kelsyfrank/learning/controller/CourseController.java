package com.kelsyfrank.learning.controller;

import com.kelsyfrank.learning.model.Course;
import com.kelsyfrank.learning.service.CourseService;
import com.kelsyfrank.learning.dto.CourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service){
        this.service = service;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses() {
        return service.getAllCourses().stream()
                .map(course -> new CourseDTO(course.getId(), course.getTitle(), course.getDescription()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        return service.getCourseById(id)
                .map(course -> ResponseEntity.ok(new CourseDTO(course.getId(), course.getTitle(), course.getDescription())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return service.createCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
    }
}
