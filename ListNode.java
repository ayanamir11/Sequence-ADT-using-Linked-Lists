package pa3;

/**
 * The ListNode class is more data-specific than the LinkedList class.  It
 * details what a single node looks like.  This node has one data field,
 * holding a pointer to a String object. 
 *
 * This is the only class where I'll let you use public instance variables.
 * 
 */
public class ListNode
{
    // these are the 2 parts which make up a node. 
    public String data;            // this holds the value/data in the node
    public ListNode next;          // this points to the next node

    // default constructor
    public ListNode(String new_data)
    {
        data = new_data;
        next = null;
    }

    // non - default constructor
    public ListNode(String new_data, ListNode nextNode)
    {
        data = new_data;
        next = nextNode;
    }
    
    // returns the data in the nodes in the form of a string.
    public String toString(){
    	return data;
    }

}
