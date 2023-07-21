package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> test1 = new AListNoResizing<>();
        BuggyAList<Integer> test2 = new BuggyAList<>();

        int[] test_item = {4,5,6};
        for (int x : test_item){
            test1.addLast(x);
            test2.addLast(x);
        }
        assertEquals(test1.size(), test2.size());
        assertEquals(test1.removeLast(),test2.removeLast() );
        assertEquals(test1.removeLast(),test2.removeLast() );
        assertEquals(test1.removeLast(),test2.removeLast() );
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> R = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                R.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = R.size();
                assertEquals(size, size2);
                //System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                if (L.size() != 0){
                    int L_last = L.getLast();
                    int R_last = R.getLast();
                    assertEquals(L_last, R_last);
                }
            } else if (operationNumber == 3) {
                if (L.size() != 0){
                    int L_last = L.removeLast();
                    int R_last = R.removeLast();
                    assertEquals(L_last, R_last);
                }
            }
        }
    }
}
