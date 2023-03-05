

1 Create a task to-do
http://localhost:8080/api/task/create
{
    "name": "Go shopping",
    "description": "Buy dog feeds for the week"
}

2. Get all tasks
http://localhost:8080/api/task/all

3. Delete a task by name
http://localhost:8080/api/task/create/{name}

4. Delete a task by id
http://localhost:8080/api/task/create/{id}

5. Get a task by name
http://localhost:8080/api/task/{name}

6. Update a task
http://localhost:8080/api/task/update/{id}
{
    "name": "Updated name",
    "description": "Updated description"
}

7. Mark a task as completed
http://localhost:8080/api/task/update/{id}
{
    "name": "Updated name",
    "description": "Updated description"
}

