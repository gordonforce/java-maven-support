package com.github.gordonforce.java.archetype;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the archetype descriptor (archetype-metadata.xml).
 * These tests verify that the descriptor is valid and contains the expected elements.
 */
public class ArchetypeDescriptorTest {

    private static final Path ARCHETYPE_DESCRIPTOR = Paths.get("src/main/resources/META-INF/maven/archetype-metadata.xml");

    @Test
    void shouldHaveValidArchetypeDescriptor() {
        // Verify that the archetype descriptor exists
        assertThat(Files.exists(ARCHETYPE_DESCRIPTOR)).isTrue();
    }

    @Test
    void shouldDefineCorrectFileSets() throws ParserConfigurationException, IOException, SAXException {
        // Parse the archetype descriptor XML
        Document document = parseXmlDocument();

        // Verify that the descriptor defines the expected filesets
        NodeList fileSetNodes = document.getElementsByTagName("fileSet");
        assertThat(fileSetNodes.getLength()).isGreaterThanOrEqualTo(4);

        // Check for source files fileset
        boolean hasSourceFilesFileSet = false;
        boolean hasTestSourceFilesFileSet = false;
        boolean hasResourcesFileSet = false;
        boolean hasTestResourcesFileSet = false;

        for (int i = 0; i < fileSetNodes.getLength(); i++) {
            Element fileSetElement = (Element) fileSetNodes.item(i);
            NodeList directoryNodes = fileSetElement.getElementsByTagName("directory");
            if (directoryNodes.getLength() > 0) {
                String directory = directoryNodes.item(0).getTextContent();
                if ("src/main/java".equals(directory)) {
                    hasSourceFilesFileSet = true;
                } else if ("src/test/java".equals(directory)) {
                    hasTestSourceFilesFileSet = true;
                } else if ("src/main/resources".equals(directory)) {
                    hasResourcesFileSet = true;
                } else if ("src/test/resources".equals(directory)) {
                    hasTestResourcesFileSet = true;
                }
            }
        }

        assertThat(hasSourceFilesFileSet).isTrue();
        assertThat(hasTestSourceFilesFileSet).isTrue();
        assertThat(hasResourcesFileSet).isTrue();
        assertThat(hasTestResourcesFileSet).isTrue();
    }

    @Test
    void shouldConfigurePackagedFileSets() throws ParserConfigurationException, IOException, SAXException {
        // Parse the archetype descriptor XML
        Document document = parseXmlDocument();

        // Verify that the source and test source filesets are configured as packaged
        NodeList fileSetNodes = document.getElementsByTagName("fileSet");

        boolean sourceFilesArePackaged = false;
        boolean testSourceFilesArePackaged = false;

        for (int i = 0; i < fileSetNodes.getLength(); i++) {
            Element fileSetElement = (Element) fileSetNodes.item(i);
            NodeList directoryNodes = fileSetElement.getElementsByTagName("directory");
            if (directoryNodes.getLength() > 0) {
                String directory = directoryNodes.item(0).getTextContent();
                String packaged = fileSetElement.getAttribute("packaged");

                if ("src/main/java".equals(directory) && "true".equals(packaged)) {
                    sourceFilesArePackaged = true;
                } else if ("src/test/java".equals(directory) && "true".equals(packaged)) {
                    testSourceFilesArePackaged = true;
                }
            }
        }

        assertThat(sourceFilesArePackaged).isTrue();
        assertThat(testSourceFilesArePackaged).isTrue();
    }

    /**
     * Parses the archetype descriptor XML file with security features enabled.
     *
     * @return the parsed XML document
     * @throws ParserConfigurationException if a parser configuration error occurs
     * @throws IOException if an I/O error occurs
     * @throws SAXException if a parsing error occurs
     */
    private Document parseXmlDocument() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Disable external entities to prevent XXE attacks
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(ARCHETYPE_DESCRIPTOR.toString()));
    }
}
