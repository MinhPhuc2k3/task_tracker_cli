# Task CLI Application
https://roadmap.sh/projects/task-tracker
This is a simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal.

## Features

- **Add a Task:** Add a new task with a description.
- **Update a Task:** Update the description of an existing task.
- **Delete a Task:** Remove a task by its ID.
- **Mark a Task:** Mark a task as "in progress" or "done."
- **List Tasks:** List all tasks or filter them by status (e.g., `todo`, `in progress`, `done`).

## Installation

1. **Clone the repository:**

   ```bash
   git clone git@github.com:MinhPhuc2k3/task_tracker_cli.git
   cd Task_Tracker_CLI/src

2. **Compile the source code:**
    ```bash
   javac TaskCLIApp.java ./model/Task.java ./services/TaskService.java ./model/TaskStatus.java
3. **Run the application:**
    ```bash
   java TaskCLIApp <command> [arguments]
   ```
## Usage
```bash
# Adding a new task
java TaskCLIApp add "Buy groceries"
# Output: Success!

# Updating a task
java TaskCLIApp update 1 "Buy groceries and cook dinner"
# Output: Success!

# Deleting a task
java TaskCLIApp delete 1
# Output: Success!
# Marking a task as in progress
java TaskCLIApp mark 1 in-progress
# Output: Success!
# Marking a task as done
java TaskCLIApp mark 1 done
# Output: Success!
# Listing all tasks
java TaskCLIApp list
# Output: List of all tasks

# Listing tasks by status
java TaskCLIApp list todo
java TaskCLIApp list in-progress
java TaskCLIApp list done

```