package org.mitenkov.coursemanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.controller.converter.CourseConverter;
import org.mitenkov.coursemanager.dto.CourseDto;
import org.mitenkov.coursemanager.dto.ErrorMessageDto;
import org.mitenkov.coursemanager.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/courses")
@RestController
@Tag(name = "Courses")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied, bad request",
                content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
})
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseConverter courseConverter;

    @GetMapping
    @Operation(summary = "Get all courses", description = "By default return all courses. " +
            "Add parameter activeOnly=true to get only courses with active registration.")
    public Page<CourseDto> getCourses(
            @RequestParam(
                    name = "activeOnly",
                    defaultValue = "false",
                    required = false
            )
            Boolean activeOnly,
            Pageable pageable) {
        if (!activeOnly) {
            return courseService.getAllCourses(pageable).map(courseConverter::convertToDto);
        }
        return courseService.getAllActiveCourses(pageable).map(courseConverter::convertToDto);
    }
}
