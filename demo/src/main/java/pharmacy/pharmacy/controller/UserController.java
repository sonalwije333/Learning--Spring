package pharmacy.pharmacy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pharmacy.pharmacy.entity.User;
import pharmacy.pharmacy.entity.UserRole;
import pharmacy.pharmacy.exception.GlobalException;
import pharmacy.pharmacy.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            throw new GlobalException("Error retrieving users", e);
        }
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (Exception e) {
            throw new GlobalException("Error retrieving user with id: " + id, e);
        }
    }

    // Create new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.createUser(user));
        } catch (Exception e) {
            throw new GlobalException("Error creating user", e);
        }
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (Exception e) {
            throw new GlobalException("Error updating user with id: " + id, e);
        }
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new GlobalException("Error deleting user with id: " + id, e);
        }
    }

    // Assign role to user
    @PostMapping("/{userId}/roles")
    public ResponseEntity<User> assignRoleToUser(@PathVariable int userId, @RequestBody UserRole role) {
        try {
            return ResponseEntity.ok(userService.assignRoleToUser(userId, role.getId()));
        } catch (Exception e) {
            throw new GlobalException("Error assigning role to user with id: " + userId, e);
        }
    }
}
