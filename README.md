# Getting Started

This App app is made for task assignment and contains Features like :
1. Task Assignment
2. Task Viewing
3. Registration
4. Login
5. User Deletion
6. Task Completion

To start off with an admin User, you need to add in a Admin User.
This can be done by adding one into the ```user_roles``` table. Just change the role from ROLE_USER to ROLE_ADMIN.
# Application Properties

```xml
spring.application.name=Ex0025_AuthenticationWithDatabase

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/POS14BHIF
spring.datasource.username=postgres
spring.datasource.password=

spring.jpa.show-sql=true

spring.jpa.hibernate.ddl-auto=update
```

Made with <3 by Lukas Hofer and Thomas Müller, 4BHIF

