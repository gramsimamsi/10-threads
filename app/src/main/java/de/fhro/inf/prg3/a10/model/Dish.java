package de.fhro.inf.prg3.a10.model;

/**
 * Dish instance prepared by a cook
 * @author Peter Kurfer
 */

public final class Dish {

    /* Multiplier to calculate the cooking time for the dish */
    private static final double MULTIPLIER = 60d;

    /* Minimum value to add to the calculated cooking time */
    private static final int MINIMUM_COOK_TIME = 600;

    private final int cookingTime;
    private final String mealName;

    public Dish(String mealDescription) {
        this.mealName = mealDescription;
        this.cookingTime = (int)(Math.random() * MULTIPLIER * (double) mealDescription.length() + MINIMUM_COOK_TIME);
    }

    public final int getCookingTime() {
        return cookingTime;
    }

    public final String getMealName() {
        return mealName;
    }
}
