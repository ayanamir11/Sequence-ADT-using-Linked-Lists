package pa3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class SequenceTest 
{
    @Rule // a test will fail if it takes longer than 1/10 of a second to run
 	public Timeout timeout = Timeout.millis(100);

    @Test //capacity should be 10
    public void testConstructor() {
        Sequence testSeq = new Sequence();
        assertEquals(10, testSeq.getCapacity());
    }

    @Test // custom initial capacity
    public void testConstructor_custom() {
        Sequence testSeq = new Sequence(30);
        assertEquals(30, testSeq.getCapacity());
    }

    @Test // test size when empty
    public void testSize_caseA() {
        Sequence testSeq = new Sequence(30);
        assertEquals(0, testSeq.size());
    }

    @Test // test size when not empty
    public void testSize_caseB() {
        Sequence testSeq = new Sequence(30);
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        testSeq.addAfter("CS Major");
        assertEquals(3, testSeq.size());
    }

    @Test // test isEmpty() method for an empty sequence
    public void testisEmpty() {
        Sequence testSeq = new Sequence(30);
        assertTrue(testSeq.isEmpty());
    }

    @Test // test isEmpty() method for a non-empty sequence
    public void testisEmpty_nonEmpty1() {
        Sequence testSeq = new Sequence(30);
        testSeq.addBefore("Ayan");
        assertFalse(testSeq.isEmpty());
    }

    @Test //checking isCurrent() with no current element
    public void testIsCurrent_caseA() {
        Sequence testSeq = new Sequence(30);
        assertFalse(testSeq.isCurrent());
    }

    @Test //checking isCurrent() with current element
    public void testIsCurrent_caseB() {
        Sequence testSeq = new Sequence(30);
        testSeq.addBefore("Ayan");
        assertTrue(testSeq.isCurrent());
    }

    @Test // checking getCurrent() with no current element
    public void testGetCurrent_caseA() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.removeCurrent();
        assertEquals(null,testSeq.getCurrent());
    }

    @Test // checking getCurrent() with current element
    public void testGetCurrent_caseB() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        assertEquals("Amir",testSeq.getCurrent());
    }

    @Test // addAfter in an empty sequence
    public void testAddAfter_emptyseq() {
        Sequence testSeq = new Sequence();
        testSeq.addAfter("Amir");
        assertEquals("Amir", testSeq.getCurrent());
    }

    @Test // addBefore in an empty sequence
    public void testAddBefore_emptyseq() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        assertEquals("Ayan", testSeq.getCurrent());

    }

    @Test // checking advance()
    public void testAdvance() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        testSeq.start();   // sets current element to Ayan
        testSeq.advance(); // changes current element to Amir
        assertEquals("Amir",testSeq.getCurrent());
    }

    
    @Test // trimToSize() check
    public void testTrimToSize() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        testSeq.trimToSize();
        assertEquals(2,testSeq.getCapacity());
    }

    @Test // checking ensureCapacity()
    public void testEnsureCapacity() {
        Sequence testSeq = new Sequence();
        testSeq.ensureCapacity(20);
        assertEquals(20, testSeq.getCapacity());
    }

    @Test // isEqual() check with different current elements
    public void testIsEqual_diffCA() {
        Sequence testSeq1 = new Sequence();
        Sequence testSeq2 = new Sequence();

        testSeq1.addBefore("Ayan");
        testSeq1.addAfter("Amir");
        testSeq1.advance();
        testSeq2.addBefore("Ayan");
        testSeq2.addAfter("Amir");

        assertFalse(testSeq1.equals(testSeq2));

    }

    @Test // removeCurrent() with no current element
    public void testRemoveCurrent_caseA() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        testSeq.addAfter("CS Major");
        testSeq.advance(); // sets the current element to null
        testSeq.removeCurrent(); // doesnt change the current element
        assertEquals(null, testSeq.getCurrent());
    }

     @Test // removeCurrent() with a current element
    public void testRemoveCurrent_caseB() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        testSeq.addAfter("CS Major");
        testSeq.start();          // sets the current element to Ayan
        testSeq.removeCurrent();  // changes the current element to Amir
        assertEquals("Amir", testSeq.getCurrent());
    }

    @Test // isEqual() check with same current elements
    public void testIsEqual_sameCE() {
        Sequence testSeq1 = new Sequence();
        Sequence testSeq2 = new Sequence();

        testSeq1.addBefore("Ayan");
        testSeq1.addAfter("Amir");
        testSeq1.advance();
        testSeq2.addBefore("Ayan");
        testSeq2.addAfter("Amir");
        testSeq2.advance();

        assertTrue(testSeq1.equals(testSeq2));
    }

    @Test // isEqual() check with two empty sequences
    public void testIsEqual_emptyseq() {
        Sequence testSeq1 = new Sequence();
        Sequence testSeq2 = new Sequence();

        assertTrue(testSeq1.equals(testSeq2));
    }

    @Test // isEqual() check with two empty sequences w/ diff capacities
    public void testIsEqual_emptyseq_diffCapacities() {
        Sequence testSeq1 = new Sequence(4);
        Sequence testSeq2 = new Sequence();

        assertTrue(testSeq1.equals(testSeq2));
    }

    @Test // isEqual() to check if a sequence is equal to itself.
    public void testIsEqual_reflexivity() {
        Sequence testSeq = new Sequence();
        assertTrue(testSeq.equals(testSeq));
    }

    @Test // toString() check
    public void testTostring() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        assertEquals("{Ayan, >Amir} (capacity = 10)",testSeq.toString());
    }

    @Test // clone() check
    public void testClone() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        Sequence copy = testSeq.clone();
        assertTrue(testSeq.equals(copy));;
    }

    @Test // clear() check by size
    public void testClear_size() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        testSeq.clear();
        assertEquals(0, testSeq.size());
    }

    @Test // clear() check by current element
    public void testClear_ce() {
        Sequence testSeq = new Sequence();
        testSeq.addBefore("Ayan");
        testSeq.addAfter("Amir");
        testSeq.clear();
        assertFalse(testSeq.isCurrent());
    }

    @Test //checking addAll()
    public void testAddAll() {
        Sequence testSeq1 = new Sequence();
        testSeq1.addBefore("Ayan");
        testSeq1.addBefore("Amir");
        testSeq1.addBefore("is a");

        Sequence testSeq2 = new Sequence();
        testSeq2.addBefore("CS Major");
        testSeq2.addBefore("at");
        testSeq2.addBefore("Union College");

        testSeq1.addAll(testSeq2);
        // sequence added successfully.
        assertEquals(6,testSeq1.size());

        // current element of other element doesnt change
        assertEquals("Union College",testSeq2.getCurrent());

        // current element of the first element doesnt change
        assertEquals("is a",testSeq1.getCurrent());
    }
    
}
 