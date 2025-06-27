package com.kelsyfrank.learning.controller;

import com.kelsyfrank.learning.model.Course;
import com.kelsyfrank.learning.repository.CourseRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseControllerTest {
    
    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");
    
    static {
        mysql.start();
    }

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driver-class-name", mysql::getDriverClassName);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepo;

    private Long testCourseId;

    @BeforeEach
    void setup() {
        Course course = Course.builder()
                .title("Test Course")
                .description("This is a test course")
                .build();
        testCourseId = courseRepo.save(course).getId();
    }

    @AfterEach
    void teardown() {
        courseRepo.deleteAll();
    }

    @Test
    void shouldReturnCourseList() throws Exception {
        mockMvc.perform(get("/api/courses"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].title", is("Test Course")));
    }

    @Test
    void shouldReturnSingleCourse() throws Exception {
        mockMvc.perform(get("/api/courses/" + testCourseId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.description", is("This is a test course")));
    }

    @Test
    void shouldReturn404ForMissingCourse() throws Exception {
        mockMvc.perform(get("/api/courses/9999"))
        .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewCourse() throws Exception {
        String newCourseJson = """
                {
                    "title": "New Course",
                    "description": "Newly created course"
                }
                """;

        mockMvc.perform(post("/api/courses")
                        .contentType("application/json")
                        .content(newCourseJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("New Course")));
    }

    @Test
    void shouldRejectCourseWithMissingTitle() throws Exception {
        String invalidCourseJson = """
                {
                    "description": "No title course"
                }
                """;

        mockMvc.perform(post("/api/courses")
                        .contentType("application/json")
                        .content(invalidCourseJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDeleteCourse() throws Exception {
        mockMvc.perform(delete("/api/courses/" + testCourseId))
                .andExpect(status().isOk());

        // Confirm original course was deleted
        mockMvc.perform(get("/api/courses/" + testCourseId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldListMultipleCourses() throws Exception {
        courseRepo.save(Course.builder().title("Course 2 Electric Boogaloo").description("A second course to test").build());

        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
