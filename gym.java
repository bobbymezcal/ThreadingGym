import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;

public class Gym {
    // instance fields
    private final int totalGymMembers;
    private Map<MachineType, Integer> availableMachines;

    // constructor
    public Gym(int totalGymMembers, Map<MachineType, Integer> availableMachines) {
        this.totalGymMembers = totalGymMembers;
        this.availableMachines = availableMachines;
    }

    public void openForTheDay() {
        List<Thread> gymMembersRoutines;
        gymMembersRoutines = IntStream.rangeClosed(1, this.totalGymMembers).mapToObj();

    }

}