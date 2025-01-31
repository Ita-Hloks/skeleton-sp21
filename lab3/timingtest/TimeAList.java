package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
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
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int textLength = 1000;
        int total_change = 8; // This just for you change the chance conveniently.

        for (int chance = 0; chance < total_change; chance++) {
            if (chance > 0) {
                textLength *= 2;
            }
            Stopwatch sw = new Stopwatch();
            AList<Integer> textN = new AList<>();

            // addLast Function Text
            for (int j = 0; j < textLength; j++) {
                textN.addLast(j);
            }

            double timeInSeconds = sw.elapsedTime();
            Ns.addLast(textLength);
            times.addLast(timeInSeconds);
            opCounts.addLast(textLength); // lazy
            // Just to make u fell more relax during running the program, COMMENT OUT this line of code BEFORE committing
//            System.out.println("Loading... there are still " + (total_change - chance) + " numbers waiting calculate...");
        }
        printTimingTable(Ns, times, opCounts);
    }
}
