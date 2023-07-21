package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> M_list = new AList<>();
        int M = 10000;
        int[] store_point = {1000, 2000, 4000, 8000,16000, 32000, 64000, 128000};
        for (int n : store_point){
            M_list.addLast(M);
            ns.addLast(n);
            SLList<Integer> target = new SLList<>();
            for (int i = 0; i < n; i++){
                target.addFirst(1);
            }
            Stopwatch sw = new Stopwatch();
            for (int m = 0; m < M; m++){
                target.getLast();
            }
            times.addLast(sw.elapsedTime());
        }
        printTimingTable(ns, times, M_list);
    }

}
