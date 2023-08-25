# Groovy Twitter Test Project

Welcome to the Groovy Twitter test project! This repository contains a project that simulates Twitter-like functionality, allowing users to perform various actions like creating posts, following other users, leaving likes, and more. This README provides instructions on how to run the project, available options, available requests, and how to set up test data.

## How to Run the Project

Follow these steps to run the project locally using Docker:

1. Open a terminal window.
2. Navigate to the project directory.
3. Run the following command to build and start the project:
   ```shell
   docker-compose up --build
   ```
4. Once the project is up and running, you can access it in your browser or through API requests.

## Checking Health

After running the project, you can check its health by making a GET request to the following link: [http://localhost:8080/success](http://localhost:8080/success)

## Available Options

The project provides the following functionalities:

- Create a user
- Edit user details
- Delete a user
- Create a post
- Edit a post
- Delete a post
- Leave/remove a like on a post
- Subscribe to a user
- Unsubscribe from a user
- Comment on a post
- Get the user's feed (including likes and comments)
- Get another user's feed
- Get comments for a post

## Exploring Available Requests

You can explore all the available API requests using test data. To get started, you can import the Postman collection by following this link: [Postman Collection](https://api.postman.com/collections/20171165-ce0614ff-1fed-471a-9729-e1de0a64ffe9?access_key=PMAT-01H8PNWPNB9NW3YSH0N7XNF2PX)

### Injecting Base Data

To ensure the correct operation of the application, you need to inject base data. You can achieve this by making the following request:

```http request
GET http://localhost:8080/inject
```

This request will set up the necessary initial data for testing various functionalities.
