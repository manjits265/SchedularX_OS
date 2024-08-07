import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Process {
    int pid;
    int burstTime;
    int arrivalTime;

    Process(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class SJF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter process ID for process " + (i + 1) + ": ");
            int pid = scanner.nextInt();
            System.out.print("Enter burst time for process " + pid + ": ");
            int burstTime = scanner.nextInt();
            System.out.print("Enter arrival time for process " + pid + ": ");
            int arrivalTime = scanner.nextInt();

            processes.add(new Process(pid, burstTime, arrivalTime));
        }
        processes.sort((p1, p2) -> Integer.compare(p1.burstTime, p2.burstTime));

        int[] wt = new int[n];
        int[] tat = new int[n];

        wt[0] = 0;

        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + processes.get(i - 1).burstTime;
        }

        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + processes.get(i).burstTime;
        }

        int totalWT = 0;
        int totalTAT = 0;

        System.out.println("\nProcess\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            totalWT += wt[i];
            totalTAT += tat[i];
            System.out.println("P" + processes.get(i).pid + "\t\t"
                    + processes.get(i).burstTime + "\t\t"
                    + processes.get(i).arrivalTime + "\t\t"
                    + wt[i] + "\t\t" + tat[i]);
        }
        System.out.println("\nAverage Waiting Time: " + (float) totalWT / n);
        System.out.println("Average Turnaround Time: " + (float) totalTAT / n);

        scanner.close();
    }
}
