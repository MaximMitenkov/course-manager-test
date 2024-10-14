package org.mitenkov.coursemanager.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mitenkov.coursemanager.dto.CoursePickRequestDto;
import org.mitenkov.coursemanager.dto.EnrollmentDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
@RequiredArgsConstructor
public class EnrollmentClient {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public EnrollmentDto pickCourse(CoursePickRequestDto requestDto) {
        String requestBody = objectMapper.writeValueAsString(requestDto);
        String json = this.mockMvc.perform(post("/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(json, EnrollmentDto.class);
    }

    @SneakyThrows
    public int pickCourseGetStatus(CoursePickRequestDto requestDto) {
        String requestBody = objectMapper.writeValueAsString(requestDto);
        return this.mockMvc.perform(post("/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn().getResponse().getStatus();
    }
}
