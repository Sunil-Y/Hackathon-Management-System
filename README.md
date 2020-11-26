# Hackathon Event Mangement System 

## Tech stack used:

<img src="https://github.com/Sunil-Y/Hackathon-Management-System/blob/master/extra/flow.JPG">

###Short Summary
1) The purpose of developing this portal is to allow user to register for various hackathon
events. 
2) User can sign-up on the portal and login using the username and password.
3) User will be able to see all the hackathon events and will be able to register for those
events. 
5) User can Un-register for the event at any time.

###Functionality
1) User can sign-up and login into the system. 
2) User input validations are done in order to avoid any garbage value insertion into the systems. 
3) Admin user can be logged in using username and password. 
4) Admin user can add the hackathon events and update and delete them. 
5) Admin can set the number of seats for the events. 
6) On every event registration the number of seats decreases. 
7) When user un-register for the hackathon event number of seats will increase. 
8) User can download the PDF which contains details about the registered hackathon events.

###Technologies Used
#### 1) Spring MVC
Project is built on Spring MVC. Project contains layer segregations which includes VIEW, MODEL, DAO and POJO

#### 2) Hibernate
Hibernate is used for the connection to the database and perform HQL. HQL is used to perform criteria and projections to get the desired results.

#### 3) MYSQL
MySQL is used as database to store the information regarding the hackathon events, user registration.

#### 4)JSP
JSP is used to show the model and view data to the customer. Model is dynamically bind to the JSP.

#### 5) AJAX
Ajax is used to search for the hackathon events based on the event name. Ajax controller will search for the all events through ajax call method and will show the names of the matching events by comma separated.

#### 6)PDF generator
iText is used to generate the PDF which will contain information regarding registered events and user information.
