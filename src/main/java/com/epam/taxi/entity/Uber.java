package com.epam.taxi.entity;

import com.epam.taxi.exception.CallInactiveThreadException;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Uber {
    private static final int POOL_SIZE = 5;
    private final Semaphore semaphore = new Semaphore(POOL_SIZE);
    private static Lock instanceLock = new ReentrantLock();
    private static Lock collectionLock = new ReentrantLock();
    private Queue<Car> taxi = new LinkedList<>();
    private static AtomicReference<Uber> uber;

    private Uber() {

    }

    public static AtomicReference<Uber> getInstance() {
        if (uber == null) {
            instanceLock.tryLock();
            AtomicReference<Uber> time;
            try {
                if (uber == null) {
                    time= new AtomicReference<Uber>(new Uber());
                    uber = time;
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return uber;
    }

    public Car getTaxi() {
        Car car = null;
        try {
            semaphore.acquire();
            collectionLock.lock();
            try {
                car = taxi.poll();
            }finally {
                collectionLock.unlock();
            }
        } catch (InterruptedException e) {
            semaphore.release();
            throw new CallInactiveThreadException("Thread operation error, cause ",e);
        }
        return car;
    }

    public void returnTaxi(Car taxi) {
        collectionLock.lock();
        try{
            this.taxi.add(taxi);
        }finally {
            collectionLock.unlock();
            semaphore.release();
        }
    }

    public void fillQueueTaxi(List<Car> taxis) {
        taxi.addAll(taxis);
    }
}
