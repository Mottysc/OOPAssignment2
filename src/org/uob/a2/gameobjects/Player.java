package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {
    private String name;
    private ArrayList<Item> inventory;
    private ArrayList<Equipment> equipment;
    private ArrayList<Combination> combinations;
    private int Score;

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.Score = 0;
    }
    public void addEquipment(Equipment equipment) {
        this.equipment.add(equipment);
    }
    public void addItem(Item item) {
        this.inventory.add(item);
    }
    public void addCombination(Combination combination) {
        this.combinations.add(combination);
    }
    public ArrayList<Combination> getCombinations() {
        return combinations;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }
    public Equipment getEquipment(String equipmentName) {
        for (Equipment e : this.equipment) {
            if (e.getName().equalsIgnoreCase(equipmentName)) {
                return e;
            }
        }
        return null;
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public Item getItem(String itemName) {
        for (Item i : this.inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return null;
    }

    public int getScore() {
        return Score;
    }
    public void setScore(int score) {
        Score = score;
    }
    public void addScore(int score) {
        Score += score;
    }

    public String getName() {
        return name;
    }
    public boolean hasItem(String itemName) {
        for (Item i : this.inventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasEquipment(String equipmentName) {
        for (Equipment e : this.equipment) {
            if (e.getName().equalsIgnoreCase(equipmentName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
