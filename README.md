# Chat Server-Client Application

This is a simple multi-client chat application built using Java. It consists of a **server** that can handle multiple clients and allows for broadcasting messages to all connected clients, as well as a **client** application that connects to the server and sends/receives messages.

## Features

### Server-side
- **Handles multiple clients**: The server can handle multiple client connections at once and can broadcast messages to all connected clients.
- **Nickname Management**: Clients can change their nickname using the `/nick <new_nickname>` command. This will notify other clients in the chat about the change.
- **Message Broadcasting**: All messages sent by a client are broadcast to all connected clients in real-time.
- **Graceful Shutdown**: The server gracefully shuts down when instructed or if an error occurs, closing all client connections and releasing resources.
  
### Client-side
- **Connect to Server**: The client connects to the server (default IP `127.0.0.1` and port `9999`).
- **User Input**: Users can send messages to the server through the command line interface.
- **Exit Command**: Clients can exit the chat by typing `/quit`, which sends the exit command to the server and closes the connection.
- **Asynchronous Input**: A separate thread listens for user input while the client also listens for incoming messages from the server.

## Requirements

- **Java**: Version 8 or higher.
- **IDE/Editor**: Any text editor (e.g., IntelliJ IDEA, Eclipse, VS Code) or a terminal-based IDE.

## Project Structure

The project is divided into two main classes: **Server** and **Client**.

### 1. `Server.java`
- **Purpose**: The server listens for incoming client connections, manages these connections, and broadcasts messages to all connected clients.
- **Components**:
  - `ServerSocket`: Listens on port `9999` for incoming connections.
  - `ConnectionHandler`: Handles each individual client connection. It manages the input/output streams and processes client messages.
  - **Multithreading**: Uses an `ExecutorService` to handle multiple clients concurrently.
  - **Broadcast**: Sends messages to all connected clients in real-time.
  - **Nickname Change**: Supports the `/nick <new_nickname>` command for changing the user's nickname.

### 2. `Client.java`
- **Purpose**: The client connects to the server and allows users to send messages. It also receives and displays messages from the server.
- **Components**:
  - `Socket`: Connects to the server at `127.0.0.1:9999`.
  - **Input Handler**: Listens for user input in a separate thread, allowing the user to type messages while also receiving messages from the server.
  - **Graceful Shutdown**: Handles the closing of connections and input streams when the user types `/quit`.

### 3. `InputHandler.java`
- **Purpose**: Handles user input asynchronously in a separate thread. It listens for messages to be sent to the server and handles commands like `/quit`.

## How to Run

### Prerequisites
Ensure you have Java 8 or higher installed. You can check your Java version by running:

```bash
java -version
javac Server.java
javac Client.java
java Server
java Client
