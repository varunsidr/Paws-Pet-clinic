# Spring PetClinic Sample Application [![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)[![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/gradle-build.yml)

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/spring-projects/spring-petclinic) [![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=7517918)

## Understanding the Spring Petclinic application with a few diagrams

See the presentation here:  
[Spring Petclinic Sample Application (legacy slides)](https://speakerdeck.com/michaelisvy/spring-petclinic-sample-application?slide=20)

> **Note:** These slides refer to a legacy, pre–Spring Boot version of Petclinic and may not reflect the current Spring Boot–based implementation.  
> For up-to-date information, please refer to this repository and its documentation.


## Run Petclinic locally

Spring Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/) or [Gradle](https://spring.io/guides/gs/gradle/).
Java 17 or later is required for the build, and the application can run with Java 17 or newer.

You first need to clone the project locally:

```bash
git clone https://github.com/spring-projects/spring-petclinic.git
cd spring-petclinic
```
If you are using Maven, you can start the application on the command-line as follows:

```bash
./mvnw spring-boot:run
```
With Gradle, the command is as follows:

```bash
./gradlew bootRun
```

You can then access the Petclinic at <http://localhost:8080/>.

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

You can, of course, run Petclinic in your favorite IDE.
See below for more details.

## Building a Container

There is no `Dockerfile` in this project. You can build a container image (if you have a docker daemon) using the Spring Boot build plugin:

## Running the Container Image

```bash
./mvnw spring-boot:build-image
docker images | grep petclinic
docker run -p 8080:8080 docker.io/library/spring-petclinic:latest
```

## In case you find a bug/suggested improvement for Spring Petclinic

Our issue tracker is available [here](https://github.com/spring-projects/spring-petclinic/issues).

## Database configuration

By default, Petclinic uses an in-memory H2 database populated at startup. Access the H2 console at `http://localhost:8080/h2-console` (UUID printed at startup).

For MySQL or PostgreSQL, use a different profile: `spring.profiles.active=mysql` or `spring.profiles.active=postgres`.

**Docker Setup:**
```bash
docker compose up mysql    # or: docker compose up postgres
```

See [MySQL](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources/db/mysql/petclinic_db_setup_mysql.txt) and [PostgreSQL](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources/db/postgres/petclinic_db_setup_postgres.txt) docs for details.

## Viewing SQL Queries

To see the SQL queries executed by Hibernate/JPA, add the following to `src/main/resources/application.properties`:

```properties
# Enable SQL logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

# Pretty-print SQL (Spring Boot 2.3+)
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
```

Then run the application and SQL statements will appear in the console output.

**Alternative:** Use the H2 console or enable `spring.jpa.show-sql=true` for a simpler output.

## Test Applications

Test classes like `PetClinicIntegrationTests`, `MySqlTestApplication`, and `PostgresIntegrationTests` provide `main()` methods for quick feedback in your IDE. MySQL tests use Testcontainers; PostgreSQL tests use Docker Compose.

## Compiling CSS

If you modify `src/main/resources/scss/petclinic.scss`, recompile with: `./mvnw package -P css`

## Working with Petclinic in your IDE

### Prerequisites

- Java 17+ (full JDK, not JRE)
- [Git](https://help.github.com/articles/set-up-git)
- IDE: [Eclipse](https://www.eclipse.org/m2e/) (m2e), [STS](https://spring.io/tools), [IntelliJ](https://www.jetbrains.com/idea/), or [VS Code](https://code.visualstudio.com)

### Steps

1. Clone: `git clone https://github.com/spring-projects/spring-petclinic.git`

2. **Eclipse/STS:** File → Import → Maven → Existing Maven Project → select root directory

3. **IntelliJ:** File → Open → select `pom.xml`

4. **VS Code:** Open folder directly

5. Generate CSS: `./mvnw generate-resources`

6. Visit `http://localhost:8080`

## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [PetClinicApplication](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java) |
|Properties Files | [application.properties](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/resources) |
|Caching | [CacheConfiguration](https://github.com/spring-projects/spring-petclinic/blob/main/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java) |

## Interesting Spring Petclinic branches and forks

The Spring Petclinic "main" branch is the canonical implementation based on Spring Boot and Thymeleaf. See [spring-petclinic](https://github.com/spring-petclinic) for [community forks](https://spring-petclinic.github.io/docs/forks.html) with different technology stacks.

## Interaction with other open-source projects

Petclinic has contributed to improvements in Spring, Spring Data, Bean Validation, Hibernate, and Eclipse:

| Project | Issue |
|---------|-------|
| Spring JDBC | [SPR-10256](https://github.com/spring-projects/spring-framework/issues/14889), [SPR-10257](https://github.com/spring-projects/spring-framework/issues/14890) |
| Bean Validation / Hibernate | [HV-790](https://hibernate.atlassian.net/browse/HV-790), [HV-792](https://hibernate.atlassian.net/browse/HV-792) |
| Spring Data JPA | [DATAJPA-292](https://github.com/spring-projects/spring-data-jpa/issues/704) |

## Contributing

Submit bug reports, feature requests, and pull requests via the [issue tracker](https://github.com/spring-projects/spring-petclinic/issues). All commits must include a `Signed-off-by` trailer. See the [DCO blog post](https://spring.io/blog/2025/01/06/hello-dco-goodbye-cla-simplifying-contributions-to-spring) for details.

## License

The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
