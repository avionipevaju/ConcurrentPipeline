package org.raf.kids.domaci;

import org.raf.kids.domaci.vo.Constants;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) throws Exception {

        Application consoleApp = new Application();
        consoleApp.run();

    }

}
