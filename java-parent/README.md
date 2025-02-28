# Java Parent POM

## Overview
This module provides a parent POM configuration for Java projects, establishing common build settings, plugins, and best practices across all child projects.

## Features
- Standardized Java compiler settings (Java 21)
- Common plugin configurations
- Default project structure
- Unified build lifecycle
- Test configuration and reporting
- Code quality tools integration

## Usage
To use this parent POM in your project, set it as the parent in your `pom.xml`:

```xml
<parent>
    <groupId>com.gordonforce</groupId>
    <artifactId>java-parent</artifactId>
    <version>${project.version}</version>
</parent>
```

## Included Configurations

### Build Plugins
- Maven Compiler Plugin (Java 21)
- Maven Surefire Plugin (Testing)
- JaCoCo Plugin (Code Coverage)
- Maven Source Plugin
- Maven JavaDoc Plugin
- Spring Boot Maven Plugin
- GraalVM Native Plugin

### Code Quality
- Checkstyle configuration
- SpotBugs integration
- PMD rules
- JaCoCo coverage thresholds

### Testing Support
- JUnit 5 configuration
- Mockito integration
- Test resource filtering
- Test logging configuration

## Customization
While the parent POM provides sensible defaults, projects can override any configuration:
1. Override properties in your project's pom.xml
2. Add additional plugins as needed
3. Customize existing plugin configurations
4. Override dependencies or add new ones