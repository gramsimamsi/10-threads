package de.fhro.inf.prg3.a10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Deque;
import java.util.LinkedList;

import de.fhro.inf.prg3.a10.kitchen.KitchenHachtImpl;
import de.fhro.inf.prg3.a10.kitchen.KitchenHatch;
import de.fhro.inf.prg3.a10.kitchen.workers.Cook;
import de.fhro.inf.prg3.a10.kitchen.workers.Waiter;
import de.fhro.inf.prg3.a10.model.Order;
import de.fhro.inf.prg3.a10.util.NameGenerator;

public class MainActivity extends AppCompatActivity {

    private static final int ORDER_COUNT = 150;
    private static final int KITCHEN_HATCH_SIZE = 100;
    private static final int COOKS_COUNT = 2;
    private static final int WAITERS_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NameGenerator nameGenerator = new NameGenerator(this);

        /* Setup the kitchen hatch */
        final Deque<Order> orders = new LinkedList<>();
        for(int i = 0; i < ORDER_COUNT; i++){
            orders.push(new Order(nameGenerator.getDishName()));
        }

        /* DONE: initialize the kitchen hatch
         * use the constant above to control how many meals may be placed in the hatch */
        final KitchenHatch kitchenHatch = new KitchenHachtImpl(KITCHEN_HATCH_SIZE, orders);

        /* setup progress reporter */
        final ProgressReporter progressReporter = new ProgressReporter.ProgressReporterBuilder()
                .setKitchenHatch(kitchenHatch)
                /* retrieve the view elements to enable the reporter
                 * to update the progress bars and busy indicators */
                .setKitchenHatchProgress(findViewById(R.id.kitchen_hatch_progress))
                .setOrderQueueProgress(findViewById(R.id.order_queue_progress))
                .setKitchenBusyIndicator(findViewById(R.id.kitchen_busy_indicator))
                .setWaiterBusyIndicator(findViewById(R.id.waiter_busy_indicator))
                /* set the count of cook and waiters
                 * to enable the reporter to tack if all cooks and waiters are gone */
                .setCookCount(COOKS_COUNT)
                .setWaiterCount(WAITERS_COUNT)
                /* complete the builder */
                .createProgressReporter();


        /* TODO create the cooks and waiters, pass the kitchen hatch and the reporter instance and start them */
        for(int i = 0; i < COOKS_COUNT ; i++){
            new Thread(new Cook(nameGenerator.generateName(), kitchenHatch, progressReporter)).start();
        }
        for(int i = 0; i < WAITERS_COUNT; i++){
            new Thread(new Waiter(nameGenerator.generateName(), kitchenHatch, progressReporter)).start();
        }

    }
}
