package org.uob.a2.gameobjects;

/**
 * Represents a generic game object that can be part of the game world.
 * 
 * <p>
 * Game objects have a name, description, unique identifier, and visibility state.
 * This abstract class serves as a base for more specific types of game objects.
 * </p>
 */
public abstract class GameObject {
    private String id;
    private String name;
    private String description;
    private boolean hidden;

    /**
     * Constructs a new game object with the specified ID, name, description, and visibility state.
     *
     * @param id the unique identifier for the game object
     * @param name the name of the game object
     * @param description a description of the game object
     * @param hidden whether the game object is initially hidden from the player
     */
    public GameObject(String id, String name, String description, boolean hidden) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "GameObject {" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", hidden=" + hidden +
               '}';
    }

    public String getName() {
        return name;
    }

    public boolean getHidden() {
        return hidden;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setName(String name) {
        this.name = name;
    }
}
