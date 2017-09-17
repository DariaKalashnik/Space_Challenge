public class Rocket implements SpaceShip {

    public int currentRocketWeight;
    public long rocketCost;
    public static int weightLimit;
    public static double percent;

    @Override
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }

    @Override
    public int carry(ItemClass item) {
        currentRocketWeight += item.weight;
        System.out.println(String.format("Current rocket weight is: %d\n", currentRocketWeight));
        return currentRocketWeight;
    }

    @Override
    public boolean canCarry(ItemClass item) {
        int weightOfTheItem = currentRocketWeight + item.weight;
        System.out.println(weightOfTheItem);
        if (weightOfTheItem <= weightLimit)
            return true;
        else {
            System.out.println("Another rocket needed!");
            return false;
        }
    }
}
