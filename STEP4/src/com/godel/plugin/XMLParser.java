package com.godel.plugin;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;

public class XMLParser {

    public static String parseDocument(String xmlFile, Dictionary<String, ArrayList<String>> plugins) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document =  builder.parse(xmlFile);
            NodeList mainNode = document.getElementsByTagName("plugins");
            String pluginType = mainNode.item(0).getAttributes().getNamedItem("type").getNodeValue();
            NodeList pluginList = document.getElementsByTagName("plugin");
            for (int i = 0; i < pluginList.getLength(); i++) {
                Node node = pluginList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    ArrayList<String> arr = new ArrayList<>();
                    String archetype = node.getAttributes().getNamedItem("archetype").getNodeValue();
                    String command = node.getAttributes().getNamedItem("command").getNodeValue();
                    arr.add(command);
                    if (node.getAttributes().getNamedItem("mode") != null)  {
                        String mode = node.getAttributes().getNamedItem("mode").getNodeValue();
                        arr.add(mode);
                    }
                    plugins.put(archetype, arr);
                }
            }
            return pluginType;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
