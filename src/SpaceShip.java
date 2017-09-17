public interface SpaceShip {

    boolean launch();

    boolean land();

    boolean canCarry(ItemClass item);

    int carry(ItemClass item);
}
