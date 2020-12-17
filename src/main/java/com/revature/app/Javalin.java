package com.revature.app;

public class Javalin {

    public static void main(String[] args) {
        io.javalin.Javalin app = io.javalin.Javalin.create((config) -> {
            config.addStaticFiles("/static");
            config.enableCorsForAllOrigins();
        });

        app.start(8080);


    }
}
