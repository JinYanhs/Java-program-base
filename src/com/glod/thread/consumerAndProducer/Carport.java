package com.glod.thread.consumerAndProducer;

/**
 * @description 车位
 */
public class Carport {
    private int freeCarport;

    public Carport(int freeCarport){
        this.freeCarport = freeCarport;
    }

    public String toString(){
       // return "空闲车位数量" + freeCarport;
        return "辆" + freeCarport + 1;
    }
}
