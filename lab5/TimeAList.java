import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> test;
        List<Double> times = new ArrayList<Double>(0);
        List<Integer> Ns = new ArrayList<Integer>(0);
        List<Integer> opCounts = new ArrayList<Integer>(0);
        for (int i = 1000; i <= 128000; i *= 2) {
            test = new AList<Integer>();
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < i; j++) {
                test.addLast(j);
            }
            double timeInSeconds = sw.elapsedTime();
            int N = i;
            int op = i;
            times.add(timeInSeconds);
            Ns.add(N);
            opCounts.add(op);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
