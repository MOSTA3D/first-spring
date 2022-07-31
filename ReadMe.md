# Users Crud Operations
this is the Spring crud operations assignment at cegedim intership

## How to use
Apply Rest-api to `/users` route, and an extra `/users/signin` route that accept users credentials.

## Rest Methods
* `get /users` will return all the users in the system.
* `get /users/:id` get the user with the specified id.
* `post /users` with a body that contains `firstname lastname, email and password` object and this user will be created. 
* `post /users/signin` this will check the users credentials provided against the one that is in the database.
* `put /users` with a body that contains `first and lastname` object, and the user will be updated with this data.
* `delete /users/:id` that will delete the user with the specified id.
