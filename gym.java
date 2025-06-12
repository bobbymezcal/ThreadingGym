import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

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
        gymMembersRoutines = IntStream.rangeClosed(1, this.totalGymMembers)
        .mapToObj((id) -> {
            Member member = new Member(id);
            return new Thread(() -> {
                try {
                    member.performRoutine();
                } catch (Exception e) {
                    System.out.println(e);
                }
            });
        })
        .collect(Collectors.toList);
        gymMembersRoutines.forEach(Thread::start);
    }

    private Thread createSupervisor(List<Thread> threads) {
        Thread supervisor = new Thread(() -> {
            while (true) {
                List<String> runningThreads = threads.stream()
                .filter(Thread::isAlive)
                .map(thread -> thread.getName())
                .collect(Collectors.toList());
            }
        });
        return supervisor;
    }

}