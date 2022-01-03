package week4.battleship;

public class BattleShip extends Ship {
    public BattleShip() {
        this.setLength(8);
        this.setHit(new boolean[this.getLength()]);
    }

    @Override
    public String getShipType() {
        return "battleship";
    }
}