# Integration de Spring et Hibernate

A simple Spring MVC + Hibernate application demonstrating:
- Product and Category entities with a Many-to-One relationship
- DAO layer for CRUD operations
- Spring MVC web interface with JSP views
- WAR packaging for deployment to Apache Tomcat
- JUnit tests for DAO

## Tech stack and main dependencies
- Java (JDK 8+ recommended)
- Spring Framework (Context, MVC, ORM, Transaction)
- Hibernate Core
- MySQL Connector/J
- JSTL and Servlet API (for JSP)
- JUnit 4 and Spring Test (scope: test)

All dependencies are declared in pom.xml with packaging set to `war`.

## Project structure (key paths)
- src/main/java/com/example/web/ProductController.java — Spring MVC controller for products and categories
- src/main/java/com/example/web/WebConfig.java — Spring MVC configuration (ViewResolver and static resources)
- src/main/webapp/WEB-INF/web.xml — DispatcherServlet configuration and context params
- src/main/webapp/WEB-INF/views/product/list.jsp — JSP view for listing products
- src/main/webapp/WEB-INF/views/product/add.jsp — JSP view for adding a product
- src/test/java/com/example/dao/ProductDaoTest.java — JUnit tests for DAO
- pom.xml — Maven build and dependency management

## Prerequisites
- IntelliJ IDEA (recommended) or any Java IDE
- Apache Tomcat 9.x (for local deployment)
- A running MySQL instance and a database/schema created for the app

## Configuration
1. Database connection:
   - Configure your Hibernate/DataSource settings in your configuration class and/or application.properties (driver, URL, username, password).
   - Ensure that Hibernate can connect to your MySQL database and that the schema exists.

2. JSP views:
   - Views are expected under `/WEB-INF/views/`.
   - Current product views:
     - `product/list.jsp`
     - `product/add.jsp`
   - The controller also returns `category/list`. If missing, create `/WEB-INF/views/category/list.jsp` accordingly.

## Build
- Using Maven (if installed):
  - From project root: `mvn clean package -DskipTests`
  - The WAR will be generated under `target/*.war`
- Using IntelliJ IDEA:
  - Build > Build Artifacts... > Select your WAR artifact > Build

## Run on Tomcat (via IntelliJ IDEA)
1. Download Tomcat 9.x and extract (e.g., `C:\Tomcat`).
2. In IntelliJ, open Run/Debug Configurations > Add (+) > Tomcat Server > Local.
3. Configure the Application Server to point to your Tomcat folder.
4. In the Deployment tab, add the project WAR artifact and set Application context (e.g., `/spring-hibernate`).
5. Click Run (green triangle). IntelliJ compiles, starts Tomcat, and deploys your app.
6. Access in browser: `http://localhost:8080/spring-hibernate/`

## Endpoints and pages
- GET `/products` — List all products (renders `product/list.jsp`)
- GET `/products/add` — Show add product form (renders `product/add.jsp`)
- POST `/products/add` — Create product (expects `categoryId` parameter); redirects to `/products`
- GET `/products/categories` — List categories (renders `category/list.jsp` if present)

## Tests
- DAO tests: `src/test/java/com/example/dao/ProductDaoTest.java`
- Run tests from your IDE (JUnit 4), or via Maven: `mvn test`

## Troubleshooting
- NoUniqueBeanDefinitionException for IDao: Ensure that the correct DAO bean is selected using a bean name or `@Qualifier` (e.g., `productDaoImpl` vs `categoryDaoImpl`).
- IntelliJ warning "URI is not registered": This is an IDE schema registration issue. Go to Settings > Languages & Frameworks > Schemas and DTDs and add `http://xmlns.jcp.org/xml/ns/javaee` for web-app 4.0.
- Maven not found: If Maven is not installed, use IntelliJ’s Build Artifacts and Tomcat Run configuration.
- 404 after deploy: Verify the Tomcat Application context matches the URLs you enter (e.g., `/spring-hibernate`).

## Notes
- Do not commit secrets (DB passwords) to version control. Prefer environment variables or IDE run configs.
- Ensure your database tables are created/mapped correctly by Hibernate (DDL auto settings as needed).