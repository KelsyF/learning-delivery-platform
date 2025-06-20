package com.kelsyfrank.learning.repository;

import com.kelsyfrank.learning.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {  
}
