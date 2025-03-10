package com.github.gordonforce.java.archetype;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for the Java Library Archetype.
 * This test verifies that a project generated from the archetype has the expected configuration.
 */
public class ArchetypeIT {

    private static final Path ARCHETYPE_RESOURCES = Paths.get("src/main/resources/archetype-resources");
    private static final Path PARENT_POM = Paths.get("../java-parent/pom.xml");
    private static final Path DEPENDENCIES_BOM = Paths.get("../dependencies-bom/pom.xml");

    private static String parentPomContent;
    private static String dependenciesBomContent;

    @BeforeAll
    static void setUp() throws IOException {
        parentPomContent = Files.readString(PARENT_POM);
        dependenciesBomContent = Files.readString(DEPENDENCIES_BOM);
    }

    @Test
    void shouldUseJava21() {
        // Verify that the project uses Java 21 by checking the dependencies-bom pom.xml
        assertThat(dependenciesBomContent).contains("<java.release.version>21</java.release.version>");

        // Verify that the maven-compiler-plugin is configured to use the Java release version
        assertThat(parentPomContent).contains("<release>${java.release.version}</release>");
    }

    @Test
    void shouldUseJUnit5() {
        // Verify that the project uses JUnit 5 by checking the parent pom.xml
        assertThat(parentPomContent)
            .contains("<groupId>org.junit.jupiter</groupId>")
            .contains("<artifactId>junit-jupiter-params</artifactId>");
    }

    @Test
    void shouldUseAssertJ() {
        // Verify that the project uses AssertJ by checking the parent pom.xml
        assertThat(parentPomContent)
            .contains("<groupId>org.assertj</groupId>")
            .contains("<artifactId>assertj-core</artifactId>");
    }

    @Test
    void shouldUseMockito() {
        // Verify that the project uses Mockito by checking the parent pom.xml
        assertThat(parentPomContent)
            .contains("<groupId>org.mockito</groupId>")
            .contains("<artifactId>mockito-core</artifactId>");
    }

    @Test
    void shouldUseSlf4jAndLogback() throws IOException {
        // Verify that the project uses slf4j and logback by checking the parent pom.xml
        assertThat(parentPomContent)
            .contains("<groupId>org.slf4j</groupId>")
            .contains("<artifactId>slf4j-api</artifactId>")
            .contains("<groupId>ch.qos.logback</groupId>")
            .contains("<artifactId>logback-classic</artifactId>");

        // Also check for the presence of logback-test.xml
        final Path logbackConfigFile = ARCHETYPE_RESOURCES.resolve("src/test/resources/logback-test.xml");
        assertThat(Files.exists(logbackConfigFile)).isTrue();
        assertThat(Files.readString(logbackConfigFile))
            .contains("<configuration")
            .contains("<appender name=\"CONSOLE\"");
    }

    @Test
    void shouldEnableParallelClassExecutionForJUnit5() throws IOException {
        // Check for the presence of junit-platform.properties
        final Path junitConfigFile = ARCHETYPE_RESOURCES.resolve("src/test/resources/junit-platform.properties");
        assertThat(Files.exists(junitConfigFile)).isTrue();
        assertThat(Files.readString(junitConfigFile))
            .contains("junit.jupiter.execution.parallel.enabled=true")
            .contains("junit.jupiter.execution.parallel.mode.default=concurrent");
    }
}
