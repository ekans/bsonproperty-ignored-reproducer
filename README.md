# Reproducer

- start a mongo instance
```
$ docker run -p 27056:27017 --name quarkus-reproducer -d mongo:latest
```
- launch tests
```
$ ./mvnw clean test
```
