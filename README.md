# Movie Application

## Table of content
* [Purpose](https://github.com/agabunia/midterm-project?tab=readme-ov-file#purpose)
* [How to use](https://github.com/agabunia/midterm-project?tab=readme-ov-file#how-to-use)
* [Application architecture](https://github.com/agabunia/midterm-project?tab=readme-ov-file#application-architecture)


## Purpose:
This is movie application, which enables user to create personal account, browse movies and find detailed information about them.


## How to Use:
To use the app, the user needs to go through authorization on the login page. In case, user is not registered yet, they can go to the registration page by pressing the "Register now" button. It is necessary to enter a valid e-mail address and password for registration, the password must be repeated. After entering the valid data, the data will be automatically filled in the login page and only the "Login" button is needed be pressed. 

After authorization, the user will be navigated to the main page where they can see popular, trending, new and other movies. A user can tap on any movie they are interested at any time and it will navigate them to the movie's detailed description page. If you want to go back, the user has to press the back button. By pressing "All movies" button on the main page when the user will be naviagted on a new page where all movies and all genres are. The user can freely scroll and select a movie, or if he clicks on a genre, only movies of the selected genre will appear. Clicking the genre button again will remove the filter and show all movies again.

The user can enter his personal page by pressing the button in the upper right corner, where the user will see his personal data and the "Logout" button. If Logout button is pressed, the user will be returned to the authorization page and re-authorization will be required to enter the application. Until the user clicks the “Logout” button, the user can open the application at any time and go directly to the main page. 


## Application Architecture:

* MVI
* Clean Architecture
* Dependency Injection
*	Firebase
*	Datastore
* Moshi
* Splash Screen
* View Model
* View Binding

### URL
Data is fetched from URL: https://api.themoviedb.org




