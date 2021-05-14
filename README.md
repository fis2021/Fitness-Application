# Fitness Application
## General Description
This application aims to help customers by offering advice on how to have a healthy lifestyle. 
This includes exercises, the number of calories the customer needs to consume each day and private talk with trainers.
## Technologies Used
* [Java 15 or 16](https://www.oracle.com/java/technologies/javase-downloads.html)
* [JavaFX](https://openjfx.io/openjfx-docs/) (as GUI)
* [Maven](https://maven.apache.org/) / [Gradle](https://gradle.org/) (as build tools)
* [Nitrite Java](https://www.dizitart.org/nitrite-database.html) (as Database)
## Registration
The user needs to first register into the application by selecting one of the 2 roles:
* customer
* trainer

These roles require a unique username, a password and the basic information like full name and email.
## Customer
A customer needs to login into the application where he will be able to access his profile where 
he would give information about himself which he can change at any time. This information
includes age, gender, height, weight, description.
Also, after logging in, he will also be able to talk to one of the trainers which will help him with
advices regarding exercises.
A customer will be able to see the exercises assigned by the trainer for him.
## Trainer
After the trainer logs in, he will be able to see the customers.
The trainer can add/remove exercises for the customer.
The trainer can talk to the customer.
## Issue Tracking
In order to manage our workflow, we created a Jira instance that can be found [here](https://fitness-application.atlassian.net/).
