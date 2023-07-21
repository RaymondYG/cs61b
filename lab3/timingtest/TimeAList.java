package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.List;

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
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> target = new AList<>();
        int[] store_point = {1000, 2000, 4000, 8000,16000, 32000, 64000, 128000};

        int pointer = 0;
        int iter_round = 0;
        for (int x : store_point){
            ns.addLast(x);
        }
        Stopwatch sw = new Stopwatch();
        while (iter_round != store_point[store_point.length-1] + 1){
            if (iter_round == store_point[pointer]){

                times.addLast(sw.elapsedTime());
                pointer += 1;
            }
            target.addLast(1);
            iter_round += 1;
        }
        printTimingTable(ns, times, ns);

        //
        //int fib41 = fib(41);
        //double timeInSeconds = ;
    }
}
