package com.revature.controller;

import com.revature.beans.User;
import com.revature.exception.NonUniqueUsernameException;
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

    public UserController(UserService u){
        userService = u;
    }

    @GetMapping
    public ResponseEntity<Object> checkLogin(HttpSession session){
        System.out.println("Checking Login");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> login(HttpSession session, @RequestParam("user") String username, @RequestParam("pass") String password){
        System.out.println("Logging In");
        User user = userService.getUserByUsername(username);
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
    public ResponseEntity<Void> registerUser(HttpSession session, @RequestBody User user){
        try {
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
