package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
    protected UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation) {
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    public void setUseInformation(UseInformation useInformation) {
        this.useInformation = useInformation;
    }
    public UseInformation getUseInformation() {
        return useInformation;
    }

    public String use(GameObject target, GameState gameState) {
        if (target.getId().equalsIgnoreCase(this.useInformation.getTarget())) {
            if (this.useInformation.isUsed()) {
                return "You have already used " + this.name;
            }
            this.useInformation.setUsed(true);
            switch (useInformation.getAction()){
                case "open":
                    gameState.getMap().getCurrentRoom().getAll().stream()
                            .filter(obj -> obj.getId().equalsIgnoreCase(useInformation.getResult()))
                            .forEach(obj -> obj.setHidden(false));
                    gameState.getPlayer().getEquipment().remove(this);
                    return useInformation.getMessage();
                case "reveal":
                    if (gameState.getMap().getCurrentRoom().getId().equalsIgnoreCase(useInformation.getTarget())){
                        gameState.getMap().getCurrentRoom().getAll().stream()
                                .filter(GameObject::getHidden)
                                .forEach(obj -> obj.setHidden(false));
                        return useInformation.getMessage();
                    }
                    else{
                        return "You cannot use the " + this.name + " here.";
                    }
                case "combine":
                    return "To combine an item, use the 'combine' command.";
                case "unlock":
                    gameState.getMap().getCurrentRoom().getExits().stream()
                            .filter(exit -> exit.getId().equalsIgnoreCase(useInformation.getTarget()))
                            .forEach(exit -> exit.setLocked(false));
                    gameState.getPlayer().getEquipment().remove(this);
                    return useInformation.getMessage();
            }

            return "Equipment.use() called";
        }
        else{
            return "Invalid use target";
        }

    }

    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */
    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
