# Fibonacci Service

This Fibonacci service provides a RESTful API endpoint to calculate Fibonacci numbers.

# Live demo

<p align="center">
    <img src="/assets/screenshot.png" height="85%" width="85%" alt="App screenshot">
</p>

[Check live demo on this link](http://159.112.188.59/)

## Prerequisites

- Java Development Kit (JDK) installed
- Maven build tool installed
- Docker installed (optional, for containerization)

## Installation and Setup

1. Clone this repository:

    ```bash
    git clone <repository_url>
    ```

2. Navigate to the project directory:

    ```bash
    cd fibonacci-api
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    java -jar target/fibonacci-api-0.0.1-SNAPSHOT.jar
    ```

   The service will start running on `http://localhost:8080`.

## Usage

### Calculate Fibonacci Number

To calculate the Fibonacci number for a given `n`, make a GET request to the `/fib` endpoint with the `n` query parameter.

Example request:

```
GET /fib?n=10
```
Example response: 

```
55
```

## Docker Support

You can also run the Fibonacci service using Docker. After building the project with Maven, create a Docker image:

```bash
docker build -t fibonacci-api .
```

Then run the Docker container:

```bash
docker run -p 8080:8080 fibonacci-service
```

The service will be accessible on http://localhost:8080.

## License

This project is licensed under the [MIT License](LICENSE).
