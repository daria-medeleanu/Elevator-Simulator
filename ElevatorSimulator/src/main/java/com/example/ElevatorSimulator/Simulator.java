package com.example.ElevatorSimulator;

import com.example.ElevatorSimulator.entities.Elevator;

public class Simulator {
    public boolean running;
    public Simulator(){
        initializeThreads();
    }
    public void initializeThreads(){
        Elevator e1 = new Elevator();
        Elevator e2 = new Elevator();
        Elevator e3 = new Elevator();
        Thread t1 = new Thread(e1);
        Thread t2 = new Thread(e2);
        Thread t3 = new Thread(e3);
        t1.start();
        t2.start();
        t3.start();
        while(running){

        }
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();

    }
}
