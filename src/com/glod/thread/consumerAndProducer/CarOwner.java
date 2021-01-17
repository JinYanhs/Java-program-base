package com.glod.thread.consumerAndProducer;

/**
 * 消费者
 */
public class CarOwner implements Runnable {
    Carbarn cb = new Carbarn();
    public CarOwner(Carbarn cb){
        this.cb = cb;

    }

    /**
     * 车主驾车停入车库
     */
    @Override
    public void run() {
        for(int i = 0; i < 20 ; i++){
            Carport c = cb.carIn();
            try {
                Thread.sleep((int)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
