package week4.battleship;

public class LightCruiser extends Ship {
    public LightCruiser() {
        this.setLength(5);
        this.setHit(new boolean[this.getLength()]);
    }

    @Override
    public String getShipType() {
        return "light cruiser";
    }
}