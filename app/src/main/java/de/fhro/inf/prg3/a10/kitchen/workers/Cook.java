package de.fhro.inf.prg3.a10.kitchen.workers;

import java.util.Deque;

import de.fhro.inf.prg3.a10.ProgressReporter;
import de.fhro.inf.prg3.a10.kitchen.KitchenHatch;
import de.fhro.inf.prg3.a10.model.Dish;
import de.fhro.inf.prg3.a10.model.Order;

/**
 * Created by Lukas on 30.01.2018.
 */

public class Cook implements Runnable {
    private String name;
    private ProgressReporter progressReporter;
    private KitchenHatch kitchenHatch;

    public Cook(String name, KitchenHatch kitchenHatch, ProgressReporter progressReporter){
        this.name = name;
        this.kitchenHatch = kitchenHatch;
        this.progressReporter = progressReporter;
    }

    @Override
    public void run() {
        while(kitchenHatch.getOrderCount() > 0){
            Dish dish = new Dish(kitchenHatch.dequeueOrder().getMealName());
            try {
                Thread.sleep(dish.getCookingTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kitchenHatch.enqueueDish(dish);
            progressReporter.updateProgress();
        }
        //you can go home now
        progressReporter.notifyCookLeaving();
    }
}
