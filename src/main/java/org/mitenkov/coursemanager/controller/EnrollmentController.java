package org.mitenkov.coursemanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mitenkov.coursemanager.controller.converter.EnrollmentConverter;
import org.mitenkov.coursemanager.dto.CoursePickRequestDto;
import org.mitenkov.coursemanager.dto.EnrollmentDto;
import org.mitenkov.coursemanager.dto.ErrorMessageDto;
import org.mitenkov.coursemanager.service.EnrollmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enrollments")
@Tag(name = "Enrollments")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "Invalid data supplied, bad request",
                content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))),
        @ApiResponse(responseCode = "404", description = "Object not found",
                content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))),
        @ApiResponse(responseCode = "409", description = "Such element has already created",
                content = @Content(schema = @Schema(implementation = ErrorMessageDto.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content(schema = @Schema(implementation = ErrorMessageDto.class)))
})
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final EnrollmentConverter enrollmentConverter;

    @PostMapping
    @Operation(summary = "create enrollment")
    public EnrollmentDto pickCourse(@RequestBody CoursePickRequestDto request) {
        return enrollmentConverter.toDto(enrollmentService.pickCourse(request));
    }
}
