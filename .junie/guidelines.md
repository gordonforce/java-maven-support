# Java Maven Support Project Guidelines

## Project Overview
This project provides Maven configuration and support for Java projects, including:
- Parent POM configurations
- Dependency management (BOM)
- Build tools and plugins
- Standard project structure

## Tech Stack
- Java 21
- Spring Boot 3.4.3
- Maven build system
- GraalVM native compilation support

## Project Structure
```
java-maven-support/
├── dependencies-bom/        # Centralized dependency management
├── java-parent/            # Parent POM with common configurations
└── archetype-java-library/ # Maven archetype for Java libraries
```

### Module Descriptions
1. **dependencies-bom**
   - Manages all project dependencies
   - Defines compatible version sets
   - Prevents version conflicts
   - See `dependencies-bom/README.md` for usage

2. **java-parent**
   - Provides common Maven configurations
   - Defines build plugins and lifecycle
   - Sets up testing framework
   - See `java-parent/README.md` for details

3. **archetype-java-library**
   - Template for new Java libraries
   - Includes standard project structure
   - Pre-configured testing setup
   - See `archetype-java-library/README.md` for usage

## Documentation Standards
1. **README Files**
   - Each module must have a README.md
   - README should explain module's purpose
   - Include usage instructions
   - Document configuration options

2. **Code Documentation**
   - Use JavaDoc for public APIs
   - Include package-info.java
   - Document non-obvious implementations
   - Add examples for complex features

## Build and Development
### Prerequisites
- JDK 21
- Docker (for compose support)

### Build System
This project uses Maven Wrapper, which means you don't need to install Maven locally. The wrapper script will download the appropriate Maven version automatically.

- For Unix/Linux/macOS, use `./mvnw`
- For Windows, use `mvnw.cmd`

### Common Commands
```bash
# Build the project
./mvnw clean install

# Run tests
./mvnw test

# Generate test reports
./mvnw surefire-report:report

# Check code coverage
./mvnw jacoco:report
```

## Development Guidelines
1. **Dependency Management**
   - Add new dependencies to `dependencies-bom/pom.xml`
   - Use managed versions from the BOM
   - Document version constraints
   - Test compatibility before updating versions

2. **Testing**
   - Write unit tests using JUnit
   - Maintain code coverage (JaCoCo)
   - Use Spring Boot test utilities when applicable
   - Include integration tests where needed

3. **Best Practices**
   - Follow standard Java coding conventions
   - Use Java records with Record Builder when appropriate
   - Utilize Spring Boot's configuration processor
   - Enable Docker Compose for integration testing

4. **Version Control**
   - Use feature branches
   - Write meaningful commit messages
   - Keep changes focused and atomic
   - Update documentation with code changes

## Tools and Plugins
- Maven Compiler (3.13.0)
- JaCoCo (0.8.12)
- Surefire (3.5.2)
- Spring Boot Maven Plugin
- GraalVM Native Plugin

## Additional Resources
- [GitHub Repository](https://github.com/gordonforce/java-maven-support)
- [Issue Tracker](https://github.com/gordonforce/java-maven-support/issues)
- [CI/CD](https://github.com/gordonforce/java-maven-support/actions)
