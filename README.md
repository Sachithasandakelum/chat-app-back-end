# Group Chat Application with Websocket

## Introduction

This project serves as the WebSocket handler responsible for managing real-time communication within the group chat.
This controller utilizes the Spring Framework's WebSocket capabilities to handle connection establishment, message processing, and connection closure.
This WebSocket controller is an integral part of the group chat functionality, enabling real-time communication between users. Ensure that WebSocket support is configured in your Spring application, and this controller is registered to handle WebSocket messages.
**ObjectMapper:** Autowired for JSON serialization and deserialization. **LocalValidatorFactoryBean:** Autowired for message validation against predefined constraints.


## Technologies Used

1. Spring Framework (Spring WebMVC)
2. Java Servlet API
3. Hibernate Validator
4. Jackson Databind
5. Spring WebSocket
6. Project Lombok


## License

This project is licensed under the [MIT License](LICENSE.txt).