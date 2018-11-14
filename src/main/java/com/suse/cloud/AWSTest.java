package com.suse.cloud;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.suse.cloud.service.AWSService;
import com.suse.cloud.service.impl.AWSServiceImpl;

/**
 * Just to test the AWS Service client in multithreaded env.
 */
public class AWSTest {

    private static final Logger LOGGER = Logger.getLogger(AWSTest.class);
    private static AWSService service = null;

    public static void main(String[] args) {
        BasicConfigurator.configure();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0; i<10; i++){
            AWSTest.Worker r = new AWSTest.Worker();
            executorService.submit(r);
        }


    }

    static class Worker implements Runnable{

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        public void run() {
            service = new AWSServiceImpl();
            try {
                service.listBuckets();
                service.getObjectFromS3("<bucketname>", "<key>");
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }
}
