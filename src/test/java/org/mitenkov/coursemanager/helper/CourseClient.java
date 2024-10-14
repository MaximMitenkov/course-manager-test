package org.mitenkov.coursemanager.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mitenkov.coursemanager.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@RequiredArgsConstructor
public class CourseClient {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public List<CourseDto> getCourses() {
        String response = this.mockMvc.perform(get("/courses"))
                .andReturn().getResponse().getContentAsString();
        Page<CourseDto> result = objectMapper.readValue(response, new TypeReference<>() {
        });
        return result.toList();
    }

    @SneakyThrows
    public List<CourseDto> getCourses(boolean activeOnly, int expectedStatus) {
        String json = this.mockMvc.perform(get("/courses")
                        .param("activeOnly", activeOnly ? "true" : "false"))
                .andExpect(status().is(expectedStatus))
                .andReturn().getResponse().getContentAsString();
        Page<CourseDto> result = objectMapper.readValue(json, new TypeReference<>() {
        });
        return result.toList();
    }

}
