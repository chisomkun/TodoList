Simple REST API for managing a Todo list. https://github.com/chisomkun/TodoList

How it all works
1. Create a user. Send a POST request to: api/registration with the following information: 
   1. FirstName, lastName, email and password
2. Login at api/login: 
   1. You will be given a jwt token to be used to authorize your requests
   2. You can create todo items via POST,GET and DELETE todo items via /api/todos
   3. To update an item send a PUT to: /api/todos
   4. Use api/todos/search to get a full list of available todos. You can also include query parameters limit and page to filter your todos