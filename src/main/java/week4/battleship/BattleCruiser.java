package week4.battleship;

public class BattleCruiser extends Ship {
    public BattleCruiser() {
        this.setLength(7);
        this.setHit(new boolean[this.getLength()]);
    }

    @Override
    public String getShipType() {
        return "battlecruiser";
    }
}
