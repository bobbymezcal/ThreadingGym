import java.util.Map;

public class Gym {
    // instance fields
    private final int totalGymMembers;
    private Map<MachineType, Integer> availableMachines;

    // constructor
    public Gym(int totalGymMembers, Map<MachineType, Integer> availableMachines) {
        this.totalGymMembers = totalGymMembers;
        this.availableMachines = availableMachines;
    }

}