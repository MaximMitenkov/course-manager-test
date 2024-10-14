package org.mitenkov.coursemanager;

import org.junit.jupiter.api.BeforeAll;
import org.mitenkov.coursemanager.helper.DBCleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest
@ContextConfiguration(classes = CourseManagerApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BaseTest {

    static PostgreSQLContainer<?> container  = new PostgreSQLContainer<>("postgres:16.4-alpine");

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DBCleaner dbCleaner;

    @BeforeAll
    public static void setUp() {
        if (!container.isRunning()) {
            container.start();
        }
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
