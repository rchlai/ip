# RCApp Chatbot User Guide

## Product Screenshot

![Image](https://github.com/user-attachments/assets/952d3033-28df-44fa-83ed-d500cea69891)

## Introduction

Welcome to RCApp, a **Command-Line Interface** (CLI) chatbot designed to 
**help you record tasks efficiently**. With RCApp, you can input commands 
to add, list, and manage your tasks with ease.

## Installation

1. Ensure you have `Java 17` or above installed in your computer.
2. Download the latest `.jar` file.
3. Copy the file to the folder you want to use as the *home folder* for your RCApp application.
4. Open a command terminal, `cd`, into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.

## Features

> [!NOTE]
> Useful information about the command format:
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo meet Dad at airport`.
> - Parameters must be in a **specific order**.
    e.g. if the command specifies `/from START_DATE /to END_DATE`, `/to END_DATE /from START_DATE` is unacceptable.
> - **Extraneous parameters** for commands that do not take in parameters (such as `list`, and `bye`) will generate an error.
    e.g. `list 123` is unacceptable.

### Adding a task to do: `todo`

Adds a to-do task to a task list stored in the chatbot.

Format: `todo DESCRIPTION`

Example: `todo feed the dog`

Outcome:
```
This task has been added:
[T][ ] add task
This task has been added:
[E][ ] Raymond Lai attend CS2113 lecture Friday 21 Feb 2025 (from: 4 to: 6pm)
=========================================================================================
User says:
todo feed the dog
This task has been added:
[T][ ] feed the dog
You have 3 task(s) in the list
=========================================================================================
User says:
```

### Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline DESCRIPTION /by DUE_TIME`

Example: `deadline submit work /by Monday 9am`

Outcome:
```
User says:
deadline submit work /by Monday 9am
This task has been added:
[D][ ] submit work (by: Monday 9am)
You have 4 task(s) in the list
=========================================================================================
User says:
```

### Adding an event task: `event`

Adds an event task to the task list.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Example: `event meeting /from Tuesday 11am /to 12 noon`

Outcome:
```
User says:
event meeting /from Tuesday 11am /to 12 noon
This task has been added:
[E][ ] meeting (from: Tuesday 11am to: 12 noon)
You have 5 task(s) in the list
=========================================================================================
User says:
```

### Listing all tasks: `list`

Prints out a list of all recorded tasks.

Format: `list`

Outcome:
```
User says:
list
Here are the tasks in your list:
1.[T][ ] add task
2.[E][ ] Raymond Lai attend CS2113 lecture Friday 21 Feb 2025 (from: 4 to: 6pm)
3.[T][ ] feed the dog
4.[D][ ] submit work (by: Monday 9am)
5.[E][ ] meeting (from: Tuesday 11am to: 12 noon)
=========================================================================================
User says:
```

### Locating tasks: `find`

Searches for tasks whose descriptions matches the specified keyword.

Format: `find KEYWORD`

- Only the task description is searched.
- Only finds texts that matches the `KEYWORD` as a whole word. e.g. `find me` will not return `meeting` which contains the substring `me`.

Example: `find task`

Outcome:
```
Coming soon...
```

## Saving the data

Task list data are saved in a text file (*.txt*) automatically after any command that changes the data. 
There is no need to save manually.