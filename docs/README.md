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

### Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details