import java.util.Random;

public class U2 extends Rocket {

    private Random random = new Random();
    private double chance = random.nextDouble();
    private int rocketWeight = 18000;
    private int allWeightWithCargo = rocketWeight + currentRocketWeight;

    public U2() {
        weightLimit = 11000;
        rocketCost = 1200000;
    }

    @Override
    public boolean launch() {
        //Chance of launch explosion = 4% * (cargo carried / cargo limit)
        percent = 0.04;
        double chanceOfExplosion = percent * ((double) allWeightWithCargo / (double) weightLimit);
        if (chanceOfExplosion >= chance) {
            System.out.println("U2 EXPLODED!");
            return false;
        } else
           System.out.println("U2 launched successfully");
        return true;
    }

    @Override
    public boolean land() {
        //Chance of landing crash = 8% * (cargo carried / cargo limit)
        percent = 0.08;
        double chanceOfCrash = percent * ((double) allWeightWithCargo / (double) weightLimit);
        if (chanceOfCrash >= chance) {
            System.out.println("U2 CRASHED!");
            return false;
        } else
            System.out.println("U2 landed successfully");
        return true;
    }
}
