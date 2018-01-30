package de.fhro.inf.prg3.a10.kitchen.workers;

import java.util.concurrent.ThreadLocalRandom;

import de.fhro.inf.prg3.a10.ProgressReporter;
import de.fhro.inf.prg3.a10.kitchen.KitchenHatch;
import de.fhro.inf.prg3.a10.model.Dish;

/**
 * Created by Lukas on 30.01.2018.
 */

public class Waiter implements Runnable {

    private String name;
    private ProgressReporter progressReporter;
    private KitchenHatch kitchenHatch;

    public Waiter(String name, KitchenHatch kitchenHatch, ProgressReporter progressReporter) {
        this.name = name;
        this.kitchenHatch = kitchenHatch;
        this.progressReporter = progressReporter;
    }

    @Override
    public void run() {

        while(kitchenHatch.getOrderCount() > 0 || kitchenHatch.getDishesCount() > 0){
            Dish dishToDeliver = kitchenHatch.dequeueDish();

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            progressReporter.updateProgress();
        }

        progressReporter.notifyWaiterLeaving();
    }
}
