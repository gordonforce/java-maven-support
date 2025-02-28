# Maven Archetype: Java Library

## Overview
This Maven archetype provides a template for creating new Java library projects with pre-configured best practices, standard directory structure, and common dependencies.

## Features
- Standard Maven directory layout
- Pre-configured test environment
- Logging configuration (Logback)
- JUnit 5 test setup
- Git ignore templates
- Common development tools configuration

## Usage
To create a new library project using this archetype:

```bash
mvn archetype:generate \
  -DarchetypeGroupId=com.gordonforce \
  -DarchetypeArtifactId=archetype-java-library \
  -DarchetypeVersion=1.0.0-SNAPSHOT \
  -DgroupId=com.example \
  -DartifactId=my-library \
  -Dversion=1.0.0-SNAPSHOT
```

## Project Structure
The generated project includes:
```
my-library/
├── src/
│   ├── main/
│   │   ├── java/        # Source code
│   │   └── resources/   # Resources
│   └── test/
│       ├── java/        # Test code
│       └── resources/   # Test resources
├── .gitignore
└── pom.xml             # Project configuration
```

## Included Configuration
- Parent POM reference
- Dependencies BOM import
- Test resources:
  - logback-test.xml
  - junit-platform.properties
- Git ignore patterns
- Standard Maven plugins

## Customization
After generating your project:
1. Update the project description in pom.xml
2. Add your specific dependencies
3. Customize logging configuration if needed
4. Add your library's source code and tests