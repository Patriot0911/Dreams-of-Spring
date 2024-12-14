package org.dreamsofspring.tasks.controller.interfaces;

import java.util.List;
import java.util.Optional;

import org.dreamsofspring.tasks.entity.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Task", description = "Operations with Task")
public interface TaskApiControllerInterface {

    @Operation(
        summary = "Get Tasks with params (search filter & sort)",
        description = "Get a paginated list of tasks with filtration"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tasks retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
        )
    })
    ResponseEntity<List<Task>> getTasks(
        @Parameter(description = "Page number for pagination. Defaults to 1 if not provided", example = "1")
        Optional<Integer> pageNumber,

        @Parameter(description = "Page size for pagination. Defaults to Integer.MAX_VALUE if not provided.", example = "10")
        Optional<Integer> pageSize,

        @Parameter(description = "Task object used for filtering results", required = true)
        MultiValueMap<String, String> params
    );

    @Operation(
        summary = "Get all Tasks",
        description = "Get a list of all tasks"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tasks retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
        )
    })
    ResponseEntity<List<Task>> getAllTasks();

    @Operation(
        summary = "Get Task by Id",
        description = "Get Task by identifier"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Task retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Task Not Found",
            content = @Content(mediaType = "application/json")
        )
    })
    ResponseEntity<Task> getTaskById(
        @Parameter(name = "id", description = "Task id", example = "d1c0217d-1f95-4cc0-b048-ca29a3823709", required = true)
        String id
    );


    @Operation(
        summary = "Create a new Task",
        description = "Create a new task with the provided details."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Task created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
        )
    })
    ResponseEntity<Task> createTask(Task task);


    @Operation(
        summary = "Update an existing Task",
        description = "Update the details of an existing task by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Task successfully updated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Task Not Found",
            content = @Content(mediaType = "application/json")
        ),
    })
    ResponseEntity<Task> updateTaskById(
        @Parameter(name = "id", description = "Task id", example = "d1c0217d-1f95-4cc0-b048-ca29a3823709", required = true)
        String id,

        Task task
    );


    @Operation(
        summary = "Delete Task by Id",
        description = "Delete Task by identifier"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Success: no such task in the list",
            content = @Content(mediaType = "application/json")
        )
    })
    ResponseEntity<Task> deleteTaskById(
        @Parameter(name = "id", description = "Task id", example = "d1c0217d-1f95-4cc0-b048-ca29a3823709", required = true)
        String id
    );

    @Operation(
        summary = "Get all Completed Tasks",
        description = "Get a completed list of tasks"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Tasks retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))
        )
    })
    ResponseEntity<List<Task>> getAllCompleted(@RequestParam(required = true) Boolean state);
};
