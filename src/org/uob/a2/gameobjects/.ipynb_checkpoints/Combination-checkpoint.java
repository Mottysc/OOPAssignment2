package org.uob.a2.gameobjects;

/**
 * Represents a combination of two items that can be used to create a new item.
 *
 * <p>
 * Combinations are used to create new items by combining two existing items.
 * <br>Each combination has the two items used, the resulting item, and a description of the result.
 * </p>
 */

public class Combination {
    private String item1;
    private String item2;
    private String result;
    private String description;

    public Combination(String item1, String item2, String result, String description) {
        this.item1 = item1;
        this.item2 = item2;
        this.result = result;
        this.description = description;
    }

    public String getItem1() {
        return item1;
    }

    public String getItem2() {
        return item2;
    }

    public String getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Combination{" +
                "item1=" + item1 +
                ", item2=" + item2 +
                ", result=" + result +
                ", description='" + description + '\'' +
                '}';
    }
}
