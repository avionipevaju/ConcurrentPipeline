package org.raf.kids.domaci.utils;

import org.raf.kids.domaci.nodes.Input;
import org.raf.kids.domaci.nodes.Output;
import org.raf.kids.domaci.nodes.Worker;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLConfigurer {

    private String configurationPath;

    public XMLConfigurer(String configurationPath) {
        this.configurationPath = configurationPath;
    }

    public ArrayList<Worker> parseConfiguration() throws ParserConfigurationException, IOException, SAXException {
        ArrayList<Worker> workerList = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        File file = new File(configurationPath);
        Document doc = builder.parse(file);

        NodeList workers = doc.getElementsByTagName("Worker");

        for (int index = 0; index < workers.getLength(); index ++){
            NodeList workerNodes = workers.item(index).getChildNodes();
            String workerName = workerNodes.item(0).getTextContent();
            String workerThreads = workerNodes.item(1).getTextContent();
            NodeList parameters = workerNodes.item(4).getChildNodes();
            Worker currentWorker = new Worker(workerName, Integer.parseInt(workerThreads));
            for (int j = 0; j < parameters.getLength(); j++) {
                String key  = parameters.item(j).getChildNodes().item(0).getTextContent();
                String value = parameters.item(j).getChildNodes().item(1).getTextContent();
                currentWorker.addParameter(key, value);
            }

            if(currentWorker.getParameter("Start") != null){
                currentWorker.setStartNode(true);
            }
            if(currentWorker.getParameter("End") != null){
                currentWorker.setEndNode(true);
            }

            NodeList inputNodes = doc.getElementsByTagName("Inputs").item(index).getChildNodes();
            for (int i = 0; i < inputNodes.getLength(); i++) {
                NodeList nodes = inputNodes.item(i).getChildNodes();
                String inputName = nodes.item(0).getTextContent();
                String inputThreadCount = nodes.item(1).getTextContent();
                parameters = nodes.item(2).getChildNodes();
                Input input = new Input(inputName, Integer.parseInt(inputThreadCount));
                for (int j = 0; j < parameters.getLength(); j++) {
                    String key  = parameters.item(j).getChildNodes().item(0).getTextContent();
                    String value = parameters.item(j).getChildNodes().item(1).getTextContent();
                    input.addParameter(key, value);

                }
                currentWorker.addInputNode(input);
            }

            NodeList outputNodes = doc.getElementsByTagName("Outputs").item(index).getChildNodes();
            for (int i = 0; i < outputNodes.getLength(); i++) {
                NodeList nodes = outputNodes.item(i).getChildNodes();
                String outputName = nodes.item(0).getTextContent();
                String outputThreadCount = nodes.item(1).getTextContent();
                parameters = nodes.item(2).getChildNodes();
                Output output = new Output(outputName, Integer.parseInt(outputThreadCount));
                for (int j = 0; j < parameters.getLength(); j++) {
                    String key  = parameters.item(j).getChildNodes().item(0).getTextContent();
                    String value = parameters.item(j).getChildNodes().item(1).getTextContent();
                    output.addParameter(key, value);

                }
                currentWorker.addOutputNode(output);
            }
            workerList.add(currentWorker);
        }
        return workerList;
    }

    public String getConfigurationPath() {
        return configurationPath;
    }

    public void setConfigurationPath(String configurationPath) {
        this.configurationPath = configurationPath;
    }
}
