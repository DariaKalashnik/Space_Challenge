import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Simulation simulation = new Simulation();
        simulation.loadItems();

        System.out.println("\nLoading U1 for Phase 1:\n");
        simulation.loadU1Phase1();

        System.out.println("\nLoading U1 for Phase 2:\n");
        simulation.loadU1Phase2();

        System.out.println("\nLoading U2 for Phase 1:\n");
        simulation.loadU2Phase1();

        System.out.println("\nLoading U2 for Phase 2:\n");
        simulation.loadU2Phase2();

        simulation.U1simulation();
        simulation.U2simulation();

    }
}
