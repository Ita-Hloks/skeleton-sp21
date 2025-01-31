package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @org.junit.jupiter.api.Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> Al = new AListNoResizing<>();
        BuggyAList<Integer> Bl = new BuggyAList<>();

        for (int i = 0; i < 3; i++) {
            Al.addLast(i);
            Bl.addLast(i);
        }

        assertEquals(Al.size(), Bl.size());

        for (int i = 0; i < 3; i++) {
            assertEquals(Al.removeLast() , Bl.removeLast());
        }
    }


    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> A = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 70000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                A.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = A.size();
                assertEquals(A.size(), B.size());
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // get Last
                if (A.size() > 0){
                    int last = A.getLast();
                    assertEquals(A.getLast(), B.getLast());
                    System.out.println("last: " + last);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (A.size() > 0){
                    int last1 = A.removeLast();
                    int last2 = B.removeLast();
                    assertEquals(last1, last2);
                    System.out.println("remove last: " + last1);
                }
            }
        }
    }
}

