#INNO QA Tech Test

### Compile and Test code
1. mvn clean compile install

### Run all containers
1. docker-compose up
   2. inno-redis-server:6379
   3. prices-service:8080

### Testing
1. Import in postman INNOQA.postman_collection

### Key URIs
1. http://localhost:8080/swagger-ui/index.html [Swagger Doc]
2. http://localhost:8080/v3/api-docs [Open API]
3. http://localhost:8080/api/v1/techtest?brandID=1&productID=35455&applyDate=2020-06-14-10:00:00 [EndPoint v1]
4. http://localhost:8080/api/v2/techtest?brandID=1&productID=35455&applyDate=2020-06-14-10:00:00 [EndPoint v2]
