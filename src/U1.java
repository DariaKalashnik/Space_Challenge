import java.util.Random;

public class U1 extends Rocket {

    private Random random = new Random();
    private double chance = random.nextDouble();
    private int rocketWeight = 10000;
    private int allWeightWithCargo = rocketWeight + currentRocketWeight;

    public U1() {
        weightLimit = 8000;
        rocketCost = 1000000;
    }

    @Override
    public boolean launch() {
        //Chance of launch explosion = 5% * (cargo carried / cargo limit)
        percent = 0.05;
        double chanceOfExplosion = percent * ((double) allWeightWithCargo / (double) weightLimit);
        if (chanceOfExplosion >= chance) {
            System.out.println("U1 EXPLODED!");
            return false;
        } else
            System.out.println("U1 launched successfully");
        return true;
    }

    @Override
    public boolean land() {
        //Chance of landing crash = 1% * (cargo carried / cargo limit)
        percent = 0.01;
        double chanceOfCrash = percent * ((double) allWeightWithCargo / (double) weightLimit);
        if (chanceOfCrash >= chance) {
            System.out.println("U1 CRASHED!");
            return false;
        } else
            System.out.println("U1 landed successfully");
        return true;
    }
}
