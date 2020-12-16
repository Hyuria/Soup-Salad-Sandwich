# Soup-Salad-Sandwich
Revature: Project 1

Contributors: Henry Chen, 

Soup Salad Sandwich

This is an app design for the user base to debate on whether or not a food is under the catagorization of 'soup', 'salad', or 'sandwich'.

A user can introduce a new food, and if the food is not in the database, then it must go through admin approval.

After approval, it is introduced to the 'Chopping Block', during which users will debate on what category it falls under, and voting for it.

The food's category dynamically changes, based on which category gets the most votes, and has a forum associated with each food/dish in which users
debate and reason on their choice.

The app will also have a 'The Menu' section of exisiting dishes that was introduced again.




Product Backlog:
- Users can log in
- Admin approval of food submission
- Two main sections of the application
	- Chopping Block
		- New Dishes

		- Hot Topic

	- The Menu
		- Three catagory to orgaize existing dishes



Database Structure

- Category (category_id, Soup, Salad, Sandwich, Undecided)

- Status(status_id, New Dish, Hot Topic, Classic)

- User (user_id, username, password, role)

- Role (role_id, User, Admin)

- Forum(forum_id, Dish ID, User ID, date, like count, message)

- Dish(dish_id, Dish name, Category, photo_url)

- Vote(vote_id, dish_id, user_id, category_id) (unique constraint on dish_id, user_id)

- Like(like_id, forum_id, user_id, like_or_dislike)