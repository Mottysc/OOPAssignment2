package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {
    private ArrayList<Room> rooms;
    private Room currentRoom;

    public Map() {
        this.rooms = new ArrayList<>();
        this.currentRoom = null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void setCurrentRoom(String roomId) {
        for (Room r : this.rooms) {
            if (r.getId().equals(roomId)) {
                this.currentRoom = r;
                break;
            }
        }
    }

    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

