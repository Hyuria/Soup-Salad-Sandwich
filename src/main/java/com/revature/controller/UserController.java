package com.revature.controller;

import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;
import io.javalin.http.Context;

public class UserController {
    private static UserService userService = new UserServiceImpl();

    public static void checkLogin(Context ctx){
        System.out.println("Checking Login");
        User user = ctx.sessionAttribute("user");
        if (user != null){
            System.out.println("Logged in as: " + user.getUsername());
            ctx.json(user);
            ctx.status(200);
        }else{
            System.out.println("Not Logged In");
            ctx.status(400);
        }
    }

    public static void login(Context ctx){
        System.out.println("Logging In");
        String username = ctx.queryParam("user");
        String password = ctx.queryParam("pass");

        User user = userService.getUserByUsername(username);
        if (user != null){
            if (user.getPassword() == password){
                // Authenticated
                System.out.println("Logged in as: " + user.getUsername());
                ctx.json(user);
                ctx.status(200);
                ctx.sessionAttribute("user", user);
            }else{
                // Wrong password
                System.out.println("Wrong password");
                ctx.status(400);
            }
        }else{
            // No user found with that username
            System.out.println("No user found");
            ctx.status(404);
        }
    }

    public static void logout(Context ctx){
      System.out.println("Loging out...");
      ctx.req.getSession().invalidate();
      ctx.status(200);
   }
}
