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
        if (target.getId().equals(this.useInformation.getTarget())) {
            if (this.useInformation.isUsed()) {
                return "You have already used " + this.name;
            }
            this.useInformation.setUsed(true);
            if (useInformation.getAction().equals("open")){
                gameState.getMap().getCurrentRoom().getAll().stream().filter(obj -> obj.getId().equals(target.getId())).forEach(obj -> obj.setHidden(false));
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
