package org.raf.kids.domaci;

import org.raf.kids.domaci.nodes.Worker;
import org.raf.kids.domaci.utils.XMLConfigurer;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        XMLConfigurer xmlConfigurer =  new XMLConfigurer("src/main/resources/configuration.xml");
        ArrayList<Worker> workers = xmlConfigurer.parseConfiguration();
        ArrayList<Worker> workerThreads = new ArrayList<>();
        for (Worker worker: workers) {
            for(int i = 0; i < worker.getNumberOfExecutingThreads(); i++) {
                workerThreads.add(worker);
            }
        }
       // System.out.println(workerThreads);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.invokeAll(workerThreads);
        executorService.shutdown();
        /*executorService.submit(workers.get(1));
        executorService.invokeAll(workers);*/

    }

}
