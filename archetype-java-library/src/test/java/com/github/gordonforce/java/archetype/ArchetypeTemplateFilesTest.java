package com.github.gordonforce.java.archetype;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the archetype template files.
 * These tests verify that the template files are present and contain the expected content.
 */
public class ArchetypeTemplateFilesTest {

    private static final Path ARCHETYPE_RESOURCES = Paths.get("src/main/resources/archetype-resources");

    @Test
    void shouldHaveGitIgnoreFile() throws IOException {
        // Verify that the .gitignore file exists
        Path gitIgnoreFile = ARCHETYPE_RESOURCES.resolve(".gitignore");
        assertThat(Files.exists(gitIgnoreFile)).isTrue();

        // Verify that the .gitignore file contains common patterns
        String gitIgnoreContent = Files.readString(gitIgnoreFile);
        assertThat(gitIgnoreContent).contains("target/");
        assertThat(gitIgnoreContent).contains(".idea/");
        assertThat(gitIgnoreContent).contains("*.iml");
    }

    @Test
    void shouldHavePomXmlFile() throws IOException {
        // Verify that the pom.xml file exists
        Path pomFile = ARCHETYPE_RESOURCES.resolve("pom.xml");
        assertThat(Files.exists(pomFile)).isTrue();

        // Verify that the pom.xml file contains the expected content
        String pomContent = Files.readString(pomFile);
        assertThat(pomContent).contains("<parent>");
        assertThat(pomContent).contains("<groupId>com.github.gordonforce.java</groupId>");
        assertThat(pomContent).contains("<artifactId>java-parent</artifactId>");
        assertThat(pomContent).contains("<groupId>${groupId}</groupId>");
        assertThat(pomContent).contains("<artifactId>${artifactId}</artifactId>");
        assertThat(pomContent).contains("<version>${version}</version>");
    }

    @Test
    void shouldHaveLogbackTestConfig() throws IOException {
        // Verify that the logback-test.xml file exists
        Path logbackConfigFile = ARCHETYPE_RESOURCES.resolve("src/test/resources/logback-test.xml");
        assertThat(Files.exists(logbackConfigFile)).isTrue();

        // Verify that the logback-test.xml file contains the expected content
        String logbackContent = Files.readString(logbackConfigFile);
        assertThat(logbackContent).contains("<configuration");
        assertThat(logbackContent).contains("<appender name=\"CONSOLE\"");
    }

    @Test
    void shouldHaveJUnitPlatformProperties() throws IOException {
        // Verify that the junit-platform.properties file exists
        Path junitConfigFile = ARCHETYPE_RESOURCES.resolve("src/test/resources/junit-platform.properties");
        assertThat(Files.exists(junitConfigFile)).isTrue();

        // Verify that the junit-platform.properties file contains the expected content
        String junitContent = Files.readString(junitConfigFile);
        assertThat(junitContent).contains("junit.jupiter.execution.parallel.enabled=true");
        assertThat(junitContent).contains("junit.jupiter.execution.parallel.mode.default=concurrent");
    }

    @Test
    void shouldHaveStandardDirectoryStructure() {
        // Verify that the src directory exists
        assertThat(Files.exists(ARCHETYPE_RESOURCES.resolve("src"))).isTrue();

        // Verify that the src/test directory exists
        assertThat(Files.exists(ARCHETYPE_RESOURCES.resolve("src/test"))).isTrue();

        // Verify that the src/test/resources directory exists
        assertThat(Files.exists(ARCHETYPE_RESOURCES.resolve("src/test/resources"))).isTrue();

        // Note: The other standard Maven directories (src/main/java, src/main/resources, src/test/java)
        // are defined in the archetype descriptor but don't exist in the archetype resources.
        // They will be created by the Maven Archetype Plugin when generating a project.
    }
}
