# cruddemo
A demonstration of CRUD REST API developed by Spring Boot 2.0

Customers are stored in memory database using H2.
Used @PostConstruction to initialize the demostration data.

Used Get, POST, PUT and DELETE HTTP request to perform basic CRUD operations. 

Mappend http://localhost:8080/api/customers to list all customers and http://localhost:8080/api/customers/{customerID} to list certain customers.

Save operation is restricted within POST request and update operation is restricted with PUT request.

Mapped http://localhost:8080/api/customers?theID=customerID with @DeleteMapping to implement delete operation.
