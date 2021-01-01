package com.glod.thread;

/**
 * @description 生产者
 */
public class CarManager implements Runnable{

    Carbarn cb = new Carbarn();
    public CarManager(Carbarn cb){
        this.cb = cb;
    }

    /**
     * 车管指挥车离开车库
     */
    @Override
    public void run() {
        for(int i = 0;i < 20; i++){
            Carport c = new Carport(i);
            cb.carOut(c);
            try {
                Thread.sleep((int)(Math.random()*500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
