package org.dreamsofspring.tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.dreamsofspring.tasks.mapper.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserControllerInterface {

    @Operation(
            summary = "Find user by id",
            description = "Finds the right user (ex. by id = 1)"
    )
    UserDto findUser(@PathVariable Long userId);


    @Operation(
            summary = "Find all users",
            description = "Find all users to test named query"
    )
    Object findUserNamedQuery();

    @Operation(
            summary = "Drops agent",
            description = "Removes agent from the database"
    )
    ResponseEntity<Void> dropAgent(@PathVariable Long id);
}
