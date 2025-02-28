# Dependencies BOM (Bill of Materials)

## Overview
This module provides centralized dependency management for all Java projects in the organization. It defines a consistent set of dependencies and their versions to ensure compatibility across different modules and projects.

## Purpose
- Maintain consistent dependency versions across projects
- Prevent version conflicts and dependency hell
- Simplify dependency updates and security patches
- Ensure compatibility between different libraries

## Usage
To use this BOM in your project, add the following to your `pom.xml`:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.gordonforce</groupId>
            <artifactId>dependencies-bom</artifactId>
            <version>${project.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## Adding New Dependencies
1. Add new dependencies to the `pom.xml` in this module
2. Follow the existing structure and grouping
3. Include version properties in the properties section
4. Document any specific version requirements or compatibility issues

## Updating Dependencies
1. Review the dependency updates for breaking changes
2. Update version properties in the `pom.xml`
3. Test the changes across dependent projects
4. Document any migration steps if needed