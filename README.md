# TECH_BLOGGER

## Simple Blog Android Application

[![Video Demo](https://img.youtube.com/vi/G861_Sj6UBM/0.jpg)](https://www.youtube.com/watch?v=G861_Sj6UBM)


Simple Blog Android Application

The task is to create a very simple blog android application.
You will be using below mock api endpoint for getting the initial blog post data:
https://my-json-server.typicode.com/sohel-cse/simple-blog-api/db


Tasks: 
Get the list of blogs from the api endpoint specified above  and save it into mobile local database ( Use sqlite database with “Room” library)
Show the list of blogs in a Recycler view
When clicking on the cover photo of a blog post from the list, open a new screen and show the detail view of the blog
In the blog list screen, add a button to Provide an option to create new blog
When clicking the button, open a new screen where user can create their own blog with the below information: 
Title (text input)
Description (Multiline text input)
Categories ( Multi select field)
Use the below author  information for blog author when creating new blog: 
Id: 1
Name: John Doe
Avatar: https://i.pravatar.cc/250
Profession: Content Writer
Save the new blog into local db (Use sqlite database with “Room” library)
These offline blog posts also should be visible in the blog list screen
On the blog detail screen, provide edit option to edit the blog post and save it into local db
After first time usage of the application, user should be able to see all the posts, post detail and also user should be able to create new blog post and edit even if device is not connected to internet







used:
Java
Databinding
Dagger 2 for Dependency injection
Retrofit 2 for api calls
Room ORM for local db
ViewModel

