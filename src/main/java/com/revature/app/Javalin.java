package com.revature.app;

import com.revature.controller.DishController;
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
                delete(UserController::logout);
            });

            path("dish", () -> {
                get(DishController::getAllDish);
                post(DishController::addDish);

                path(":category", () -> {
                    get(DishController::getDishByCategory);
                });

                path(":id", () -> {
                    get(DishController::getDishById);
                    put(DishController::updateDish);
                    delete(DishController::deleteDish);

                    path("comment", () -> {
                        get(DishController::getAllComment);

                        path(":comment_id", () -> {
                            get(DishController::getCommentByCommentId);
                            put(DishController::updateComment);
                            post(DishController::addComment);
                            delete(DishController::deleteComment);
                        });
                    });
                });
            });
        });

    }
}
