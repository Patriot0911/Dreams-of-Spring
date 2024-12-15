package org.dreamsofspring.tasks.controller;

import org.dreamsofspring.tasks.mapper.UserDto;
import org.dreamsofspring.tasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// http://localhost:3000/swagger-ui/index.html
@RestController
@RequestMapping("/users")
public class UserController implements UserControllerInterface {
    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/{userId}")
    public UserDto findUser(@PathVariable Long userId) {
        var res = userService.getUserWithTasks(userId);
        return res;
    }

    @Override
    @GetMapping("/user")
    public Object findUserNamedQuery() {
        return userService.user();
    }

    @Override
    @DeleteMapping("/agent/{id}")
    public ResponseEntity<Void> dropAgent(@PathVariable Long id) {
        userService.dropAgent(id);
        return ResponseEntity.noContent().build();
    }
}
