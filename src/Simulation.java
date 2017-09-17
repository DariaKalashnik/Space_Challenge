import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private Charset charset = Charset.forName("UTF-8");
    private List<ItemClass> arrayPhase1 = new ArrayList<>();
    private List<ItemClass> arrayPhase2 = new ArrayList<>();
    ArrayList<Rocket> arrayU1 = new ArrayList<>();
    ArrayList<Rocket> arrayU2 = new ArrayList<>();
    private BufferedReader bufferPhases;
    private ItemClass items;
    private Rocket rocket1, rocket2;
    private int numOfRockets = 0;
    private long budgetNeeded = 0;
    private int numberOfRocketsToSend = 0;
    private String numberOfRocketsMessage = "__Number of rockets is: %d__\n";
    private String budgetMessage = "Total budget needed = %d\n";

    public void loadItems() throws FileNotFoundException {
        try {

            FileInputStream phase1 = new FileInputStream("phase1.txt");
            FileInputStream phase2 = new FileInputStream("phase2.txt");
            SequenceInputStream sequence = new SequenceInputStream(phase1, phase2);
            bufferPhases = new BufferedReader(new InputStreamReader(sequence, charset));

            String line;
            String[] parts;
            String name;
            int weight;

            while ((line = bufferPhases.readLine()) != null && !line.isEmpty()) {
                parts = line.split("(?<=\\D)(?=\\d)");
                name = parts[0].trim();
                weight = Integer.parseInt(parts[1].trim());
                items = new ItemClass(name, weight);
                if (phase1.getChannel().isOpen()) {
                    arrayPhase1.add(items);
                } else if (phase2.getChannel().isOpen())
                    arrayPhase2.add(items);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Number format exception");
        } finally {
            if (bufferPhases != null) {
                try {
                    bufferPhases.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(arrayPhase1);
    }

    private void loadU1(List<ItemClass> array, int numberOfRockets) {
        rocket1 = new U1();
        for (ItemClass mItem : array) {
            while (!rocket1.canCarry(mItem)) {
                arrayU1.add(rocket1);
                rocket1 = new U1();
                numberOfRockets++;
                System.out.println("new U1 is created: " + numberOfRockets);
            }
            rocket1.carry(mItem);
        }

        System.out.println(String.format(numberOfRocketsMessage, numberOfRockets));
    }

    private void loadU2(List<ItemClass> array, int numberOfRockets){
        rocket2 = new U2();
        for (ItemClass mItem : array) {
            if (!rocket2.canCarry(mItem)) {
                arrayU2.add(rocket2);
                rocket2 = new U2();
                numberOfRockets++;
                System.out.println("new U2 is created: " + numberOfRockets);
            }

            rocket2.carry(mItem);
        }
        System.out.println(String.format(numberOfRocketsMessage, numberOfRockets));
    }

    public void loadU1Phase1() {
        loadU1(arrayPhase1, numOfRockets);
    }

    public void loadU1Phase2() {
        loadU1(arrayPhase2, numOfRockets);
    }


    public void loadU2Phase1() {
        loadU2(arrayPhase1, numOfRockets);
    }

    public void loadU2Phase2() {
        loadU2(arrayPhase2, numOfRockets);
    }

    public void runSimulation(ArrayList<Rocket> array, long budgetNeeded) {
        for (Rocket rocket : array) {
            if(!rocket.launch()) {
                rocket.launch();
                numberOfRocketsToSend++;
                System.out.println("*Rocket is sent again, because of explosion*");
            }

            if(!rocket.land()) {
                rocket.land();
                numberOfRocketsToSend++;
                System.out.println("*Rocket is sent again, because of crashing*");
            }

            budgetNeeded += rocket.rocketCost;
        }

        System.out.println("We need - " + numberOfRocketsToSend + " rocket(s)");
        System.out.println(String.format(budgetMessage, budgetNeeded));
    }

    public void U1simulation(){
        runSimulation(arrayU1, budgetNeeded);
    }

    public void U2simulation(){
        runSimulation(arrayU2, budgetNeeded);
    }
}
