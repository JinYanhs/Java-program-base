package com.glod.thread.consumerAndProducer;


import org.junit.jupiter.api.Test;

/**
 *  Date: 2020-12-20
 *  function: thread test
 */
public class PractiseThead {
    public static void main(String args[])throws Exception{
        Carbarn cb = new Carbarn();
        CarManager carManager = new CarManager(cb);
        CarOwner carOwner  = new CarOwner(cb);
        Thread tProducer = new Thread(carManager);
        Thread tConsumer = new Thread(carOwner);
        tProducer.start();
        tConsumer.start();

    }
    @Test
    public void producerAndConsumer(){


    }


}
