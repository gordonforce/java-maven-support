package com.github.gordonforce.java.archetype;

import org.apache.maven.shared.invoker.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration test for generating a project from the archetype.
 * This test verifies that the archetype can be used to generate a project successfully.
 */
public class ArchetypeGenerationTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldGenerateProjectSuccessfully() throws IOException, MavenInvocationException {
        // Skip this test if we're running in a CI environment
        if (System.getenv("CI") != null) {
            return;
        }

        // First, install the archetype locally
        installArchetype();

        // Then, generate a project from the archetype
        generateProject();

        // Verify that the generated project has the expected structure
        verifyGeneratedProject();
    }

    private void installArchetype() throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("pom.xml"));
        request.setGoals(List.of("clean", "install"));

        Invoker invoker = new DefaultInvoker();
        InvocationResult result = invoker.execute(request);

        assertThat(result.getExitCode()).isEqualTo(0);
    }

    private void generateProject() throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setGoals(List.of("archetype:generate"));
        // Set non-interactive mode through properties

        Properties properties = new Properties();
        properties.setProperty("interactiveMode", "false");
        properties.setProperty("archetypeGroupId", "com.github.gordonforce.java");
        properties.setProperty("archetypeArtifactId", "archetype-java-library");
        properties.setProperty("archetypeVersion", "1.0.0-SNAPSHOT");
        properties.setProperty("groupId", "com.example");
        properties.setProperty("artifactId", "test-library");
        properties.setProperty("version", "1.0.0-SNAPSHOT");
        request.setProperties(properties);

        request.setBaseDirectory(tempDir.toFile());

        Invoker invoker = new DefaultInvoker();
        InvocationResult result = invoker.execute(request);

        assertThat(result.getExitCode()).isEqualTo(0);
    }

    private void verifyGeneratedProject() throws IOException {
        Path projectDir = tempDir.resolve("test-library");

        // Verify that the project directory was created
        assertThat(Files.exists(projectDir)).isTrue();

        // Verify that the pom.xml file was created
        Path pomFile = projectDir.resolve("pom.xml");
        assertThat(Files.exists(pomFile)).isTrue();

        // Verify that the pom.xml file contains the expected content
        String pomContent = Files.readString(pomFile);
        assertThat(pomContent).contains("<groupId>com.example</groupId>");
        assertThat(pomContent).contains("<artifactId>test-library</artifactId>");
        assertThat(pomContent).contains("<version>1.0.0-SNAPSHOT</version>");

        // Verify that the standard Maven directory structure was created
        assertThat(Files.exists(projectDir.resolve("src/main/java"))).isTrue();
        assertThat(Files.exists(projectDir.resolve("src/main/resources"))).isTrue();
        assertThat(Files.exists(projectDir.resolve("src/test/java"))).isTrue();
        assertThat(Files.exists(projectDir.resolve("src/test/resources"))).isTrue();

        // Verify that the .gitignore file was created
        assertThat(Files.exists(projectDir.resolve(".gitignore"))).isTrue();

        // Verify that the logback-test.xml file was created
        assertThat(Files.exists(projectDir.resolve("src/test/resources/logback-test.xml"))).isTrue();

        // Verify that the junit-platform.properties file was created
        assertThat(Files.exists(projectDir.resolve("src/test/resources/junit-platform.properties"))).isTrue();
    }
}
