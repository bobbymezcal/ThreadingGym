import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.HashMap;

public class Gym {
    // instance fields
    private final int totalGymMembers;
    private Map<MachineType, Integer> availableMachines;

    // constructor
    public Gym(int totalGymMembers, Map<MachineType, Integer> availableMachines) {
        this.totalGymMembers = totalGymMembers;
        this.availableMachines = availableMachines;
    }

    public static void main(String[] args) {
        Gym pausGym = new Gym(5, new HashMap<>() {
            {
                put(MachineType.LEGPRESSMACHINE, 5);
                put(MachineType.BARBELL, 5);
                put(MachineType.SQUATMACHINE, 5);
                put(MachineType.LEGEXTENSIONMACHINE, 5);
                put(MachineType.LEGCURLMACHINE, 5);
                put(MachineType.LATPULLDOWNMACHINE, 5);
                put(MachineType.CABLECROSSOVERMACHINE, 5);
            }
        });
        pausGym.openForTheDay();
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
        Thread supervisor = createSupervisor(gymMembersRoutines);
        gymMembersRoutines.forEach(Thread::start);
        supervisor.start();
    }

    private Thread createSupervisor(List<Thread> threads) {
        Thread supervisor = new Thread(() -> {
            while (true) {
                List<String> runningThreads = threads.stream()
                .filter(Thread::isAlive)
                .map(thread -> thread.getName())
                .collect(Collectors.toList());
                if (runningThreads.isEmpty()) {
                    break;
                } else {
                    System.out.println("Current thread: " + Thread.currentThread().getName());
                    System.out.println(runningThreads.size() + " members currently working out:");
                    System.out.println(runningThreads.toString());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Supervisor interrupted: " + e.getMessage());
                }
            }
            System.out.println("Current thread: " + Thread.currentThread().getName());
            System.out.println("All threads have completed.");

        });
        supervisor.setName("Gym Staff");
        return supervisor;
    }

}