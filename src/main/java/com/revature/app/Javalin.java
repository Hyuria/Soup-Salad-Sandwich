package com.revature.app;

import com.revature.controller.UserController;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Javalin {

    public static void main(String[] args) {
        io.javalin.Javalin app = io.javalin.Javalin.create((config) -> {
            config.addStaticFiles("/static");
            config.enableCorsForAllOrigins();
        });

        app.start(8080);

        app.routes(() -> {
            path("users", () -> {
                get(UserController::checkLogin);
                put(UserController::login);
            });


        });

    }
}
