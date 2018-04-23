package org.raf.kids.domaci;

import org.raf.kids.domaci.nodes.Worker;
import org.raf.kids.domaci.utils.XMLConfigurer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        XMLConfigurer xmlConfigurer =  new XMLConfigurer("src/main/resources/configuration.xml");
        ArrayList<Worker> workers = xmlConfigurer.parseConfiguration();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(workers.get(0));
        executorService.submit(workers.get(1));
        executorService.invokeAll(workers);

    }

}
