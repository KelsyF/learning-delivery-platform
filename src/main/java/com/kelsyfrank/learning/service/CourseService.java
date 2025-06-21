package com.kelsyfrank.learning.service;

import com.kelsyfrank.learning.model.Course;
import com.kelsyfrank.learning.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return repo.findById(id);
    }

    public Course createCourse(Course course) {
        return repo.save(course);
    }

    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }
}
