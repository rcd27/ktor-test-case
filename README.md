Билд и запуск в докер-контейнере:
```
./gradlew build
docker build -t ktor-test-case .
docker run -p 8080:8080 --rm ktor-test-case
```

Почистить докер `images`:
```
./dockerRemoveAll
```