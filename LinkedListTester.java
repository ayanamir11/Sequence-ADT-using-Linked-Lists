package pa3;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class LinkedListTester {
    
    @Rule // a test will fail if it takes longer than 1/10 of a second to run
 	public Timeout timeout = Timeout.millis(100);

    @Test // tests insertAtHead by checking length
    public void test_insertAtHead() 
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");

        assertEquals(0, testList.findTarget("Ayan"));
        assertEquals(1, testList.getLength());
    }

    @Test // testing insertAtIndex
    public void test_insertAtIndex()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.insertAtIndex("Amir", 1);

        assertEquals(1,testList.findTarget("Amir"));
    }


    @Test // testing removeFirstNode()
    public void test_removeFirstNode()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");
        testList.removeFirstNode();

        assertEquals(0, testList.findTarget("Amir"));
    }

    @Test // testing removeLastNode()
    public void test_removeLastNode()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");
        testList.append("is");
        testList.append("CS major");
        testList.removeLastNode();

        assertEquals(3,testList.getLength());
    }

    @Test // testing removeAtIndex()
    public void test_removeAtIndex()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");
        testList.append("is");
        testList.append("CS major");

        testList.removeAtIndex(1);
        assertEquals(3, testList.getLength());

    }

    @Test // testing contains()
    public void test_contains()
    {
         LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");
        assertTrue(testList.contains("Ayan"));
        assertTrue(testList.contains("Amir"));
    }
    
    @Test // testing findTarget()
    public void test_findTarget()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");

        assertEquals(1, testList.findTarget("Amir"));
    }

    @Test // testing getFirstElement()
    public void test_getFirstElement()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");

        assertEquals("Ayan", testList.getFirstElement());
    }

    @Test // testing getLastElement()
    public void test_getLastElement()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");

        assertEquals("Amir", testList.getLastelement());
    }

    @Test // testing getElementAt()
    public void test_getElementAt()
    {
        LinkedList testList = new LinkedList();
        testList.insertAtHead("Ayan");
        testList.append("Amir");

        assertEquals("Amir", testList.getElementAt(1));
    }

    

}
