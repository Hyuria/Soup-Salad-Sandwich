package com.revature.controller;

import com.revature.beans.Role;
import com.revature.beans.User;
import com.revature.exception.NonUniqueUsernameException;
import com.revature.services.RoleService;
import com.revature.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/users")
public class UserController {
    private static UserService userService;
    private static RoleService roleService;

    public UserController(UserService u, RoleService r){
        userService = u;
        roleService = r;
    }

    @GetMapping
    public ResponseEntity<Object> checkLogin(HttpSession session){
        System.out.println("Checking Login");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("No logged in user.");
            return ResponseEntity.badRequest().build();
        }
        System.out.println("Logged in user: " + user.getUsername());
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> login(HttpSession session, @RequestParam("user") String username, @RequestParam("pass") String password){
        System.out.println("Logging In");
        User user = userService.getUserByUsername(username);
        System.out.println(user);
        if (user != null){
            if (user.getPassword().equals(password)){
                // Authenticated
                System.out.println("Logged in as: " + user.getUsername());
                session.setAttribute("user", user);
                return ResponseEntity.ok(user);
            }else{
                // Wrong password
                System.out.println("Wrong password");
                return ResponseEntity.badRequest().build();
            }
        }else{
            // No user found with that username
            System.out.println("No user found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(HttpSession session, @RequestParam("user") String username, @RequestParam("pass") String password){
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(roleService.getRoleById(1));
            userService.addUser(user);
        }
        catch(NonUniqueUsernameException e){
            System.out.println("Username already taken :(");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> logOut(HttpSession session) {
        System.out.println("Logging Out");
        session.invalidate();
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path="/{id}")
	public ResponseEntity<Void> updateUser(HttpSession session, @PathVariable("id") Integer id, 
			@RequestBody User user) {
		User loggedUser = (User) session.getAttribute("user");
		if (loggedUser != null && loggedUser.getId().equals(id)) {
			userService.updateUser(user);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}


}
