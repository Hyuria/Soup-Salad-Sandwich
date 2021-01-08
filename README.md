# Soup-Salad-Sandwich
Revature: Project 1

## Project Description

This is an app design for the user base to debate on whether or not a food is under the catagorization of 'soup', 'salad', or 'sandwich'.

A user can introduce a new food, and if the food is not in the database, then it must go through admin approval.

After approval, it is introduced to the 'Chopping Block', during which users will debate on what category it falls under, and voting for it.

The food's category dynamically changes, based on which category gets the most votes, and has a forum associated with each food/dish in which users
debate and reason on their choice.

The app has a 'The Menu' section of exisiting dishes that was introduced again.

## Technologies Used

-Postgresql
-SpringBoot
-Hibernate
-HTML
-CSS
-Bootstrap
-JavaScript/TypeScript
-Angular
-Apache tomcat

## Features

- Users can log in
- Admin approval of food submission
- Two main sections of the application
	- Chopping Block
		- New Dishes
		- Hot Topic
	- The Menu
		- Three catagory to orgaize existing dishes


## Getting Started
   
>$ git clone https://github.com/Hyuria/Soup-Salad-Sandwich
>$ git clone https://github.com/Hyuria/Soup-Salad-Sandwich-Frontend

Backend applications run on an Apache Tomcat Server. You would need to create a PostgreSQL server on AWS RDS or locally, and change the Soup-Salad-Sandwich/src/main/resources/hibernate.cfg.xml file accordingly. 

- Frontend Repository: https://github.com/Hyuria/Soup-Salad-Sandwich-Frontend

## Database Structure

- Category (category_id, String(Soup, Salad, Sandwich, Undecided))

- Status(status_id, String(New Dish, Hot Topic, Classic) )

- User (user_id, username, password, role)

- Role (role_id, String(role))

- Comment(forum_id, Dish ID, User ID, date, like count, message)

- Dish(dish_id, Dish name, Category, photo_url)

- Vote(vote_id, dish_id, user_id, category_id) (unique constraint on dish_id, user_id)

- Like(like_id, forum_id, user_id, like_or_dislike)

## Usage

Start the frontend server using the command:
> ng serve

Start the backend in your IDE of choice, IntelliJ and Eclipse were used during development. Make sure to start it but using Apache Tomcat.

## Contributors

Contributors: Henry Chen, Jakeem, Ben Hsieh, Brodie Hufnagel

## License

This project uses the following license: [<APACHE LICENSE, VERSION 2.0>](<https://www.apache.org/licenses/LICENSE-2.0>).



