package com.kelsyfrank.learning.controller;

import com.kelsyfrank.learning.model.Course;
import com.kelsyfrank.learning.model.User;
import com.kelsyfrank.learning.repository.CourseRepository;
import com.kelsyfrank.learning.repository.UserRepository;
import com.kelsyfrank.learning.dto.UserDTO;
import com.kelsyfrank.learning.dto.CourseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final UserRepository userRepo;
    private final CourseRepository courseRepo;

    public EnrollmentController(UserRepository userRepo, CourseRepository courseRepo) {
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    @PostMapping
    public ResponseEntity<String> enrollUserInCourse(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        
        Optional<User> userOpt = userRepo.findById(userId);
        Optional<Course> courseOpt = courseRepo.findById(courseId);

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            User user = userOpt.get();
            Course course = courseOpt.get();

            // Prevent duplicate enrollments
            if (user.getCourses().contains(course)) {
                return ResponseEntity.badRequest().body("User is already enrolled in this course.");
            }

            // Enroll user
            user.getCourses().add(course);
            userRepo.save(user);

            return ResponseEntity.ok("User was enrolled in course successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid userId or courseId, user could not be enrolled.");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> unenrollUserFromCourse(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        
        Optional<User> userOpt = userRepo.findById(userId);
        Optional<Course> courseOpt = courseRepo.findById(courseId);
        
        if (userOpt.isPresent() && courseOpt.isPresent()) {
            User user = userOpt.get();
            Course course = courseOpt.get();

            if (user.getCourses().remove(course)) {
                userRepo.save(user);
                return ResponseEntity.ok("User successfully unenrolled from course!");
            } else {
                return ResponseEntity.badRequest().body("User not found in this course's enrollments.");
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid userId or courseId, user could not be unenrolled.");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getEnrolledCourses(@PathVariable Long userId) {
        Optional<User> userOpt = userRepo.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            List<CourseDTO> courses = user.getCourses().stream()
                    .map(course -> new CourseDTO(course.getId(), course.getTitle(), course.getDescription()))
                    .toList();

            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.badRequest().body("User not found.");
        }
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<?> getUsersEnrolledInCourse(@PathVariable Long courseId) {
        Optional<Course> courseOpt = courseRepo.findById(courseId);

        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();

            // Use DTO to make sure sensitive info isn't returned.
            List<UserDTO> users = course.getUsers().stream()
                    .map(user -> new UserDTO(user.getId(), user.getFullName(), user.getEmail(), user.getRole()))
                    .toList();

            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.badRequest().body("Course not found.");
        }
    }
}
