# Java Maven Support Project

[![Master Branch Build Status](https://github.com/gordonforce/java-maven-support/actions/workflows/build.yml/badge.svg)](https://github.com/gordonforce/java-maven-support/actions/workflows/build.yml)

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
- Git with configured user.name and user.email

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

## Continuous Integration
This project uses GitHub Actions to automatically build and test the code:

- A webhook is configured to trigger builds after each push or merge to the master branch
- The build process compiles the code, runs tests, and generates test and coverage reports
- Build status is displayed in the badge at the top of this README
- You can view build history and logs in the [Actions tab](https://github.com/gordonforce/java-maven-support/actions)

## Maven Extensions
Maven extensions are components that extend or modify Maven's core functionality. Unlike plugins that operate within the build lifecycle, extensions operate at the Maven core level and can influence how Maven itself works.

### What are Maven Extensions?
- **Definition**: Maven extensions are JAR files that extend Maven's core functionality
- **Purpose**: They modify Maven's behavior at a fundamental level, beyond what plugins can do
- **Loading**: Extensions are loaded before the project object model (POM) is read

### How Maven Extensions Work
1. Extensions are defined in the `<build><extensions>` section of a POM file
2. Maven loads these extensions early in its startup process
3. Extensions can add new functionality, modify existing behavior, or integrate with external systems
4. Unlike plugins, extensions are not bound to specific build phases

### Examples in This Project
In the `archetype-java-library` module, we use the `archetype-packaging` extension:

```xml
<extensions>
    <extension>
        <groupId>org.apache.maven.archetype</groupId>
        <artifactId>archetype-packaging</artifactId>
        <version>${maven-archetype-plugin.version}</version>
    </extension>
</extensions>
```

This extension enables the `maven-archetype` packaging type, allowing the module to be built and used as a Maven archetype.

### Common Use Cases
- **Custom Packaging Types**: Adding support for new packaging types (like the archetype example above)
- **Build Process Integration**: Integrating with external build systems or tools
- **Repository Extensions**: Extending Maven's repository system for custom storage solutions
- **Wagon Providers**: Adding support for new transport protocols for artifact deployment
- **Build Lifecycle Extensions**: Modifying or extending Maven's build lifecycle

### How to Implement Maven Extensions
1. Add the extension to your POM file in the `<build><extensions>` section
2. Specify the groupId, artifactId, and version of the extension
3. The extension will be automatically loaded when Maven starts

### Best Practices
- Use extensions sparingly, as they affect Maven's core behavior
- Prefer plugins over extensions when possible
- Document the purpose and impact of extensions in your project
- Keep extension versions in sync with your Maven version

## Additional Resources
- [GitHub Repository](https://github.com/gordonforce/java-maven-support)
- [Issue Tracker](https://github.com/gordonforce/java-maven-support/issues)
- [Master Branch Build Webhook](https://github.com/gordonforce/java-maven-support/actions/workflows/build.yml)
