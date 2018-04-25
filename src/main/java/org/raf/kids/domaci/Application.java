package org.raf.kids.domaci;

import org.raf.kids.domaci.nodes.Worker;
import org.raf.kids.domaci.utils.XMLConfigurer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application implements Runnable {

    Scanner inputScanner = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("~~~ Loading config file ~~~");
        XMLConfigurer xmlConfigurer =  new XMLConfigurer("src/main/resources/configuration.xml");
        ArrayList<Worker> workers;
        ArrayList<Worker> pipelineJob = new ArrayList<>();
        HashMap<Worker, ArrayList<Worker>> workerThreads = new HashMap<>();
        try {
            workers = xmlConfigurer.parseConfiguration();
            System.out.println("~~~ Workers loaded ~~~");
            System.out.println("id \t name");
            for (int index = 0; index < workers.size(); index++) {
                int num = index + 1;
                System.out.println("" + num + ". " +workers.get(index).getName());
            }
            System.out.println("~~~ Creating pipeline job ~~~");
            System.out.println("Enter the worker node id you want to put next in the pipeline job. Enter 0 to finish");
            while (true) {
                System.out.println("Enter worker id: ");
                int entered = inputScanner.nextInt();
                if (entered == 0) {
                    break;
                }
                pipelineJob.add(workers.get(entered-1));
            }
            System.out.println("~~~ Generated pipeline job ~~~");
            System.out.print("start");
            for (Worker worker: pipelineJob) {
                ArrayList<Worker> tempWorkerThreads = new ArrayList<>();
                for(int i = 0; i < worker.getNumberOfExecutingThreads(); i++) {
                    tempWorkerThreads.add(worker);
                }
                workerThreads.put(worker, tempWorkerThreads);
                System.out.print(" -> " + worker.getName());
            }
            System.out.println(" -> end");
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(workers.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
