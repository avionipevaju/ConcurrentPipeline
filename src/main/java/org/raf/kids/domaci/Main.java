package org.raf.kids.domaci;

import org.raf.kids.domaci.utils.XMLConfigurer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        XMLConfigurer xmlConfigurer =  new XMLConfigurer("src/main/resources/configuration.xml");
        System.out.println(xmlConfigurer.parseConfiguration());

    }

}
