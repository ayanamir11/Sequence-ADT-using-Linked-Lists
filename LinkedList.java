package pa3;

/**
 * This is a singly Linked List whic holds strings in the form of nodes.
 * Nodes are hidden. Indexes and values are used to traverse thorugh the list.
 * Invariants:
 * 1) The list is "length" nodes long and is >= 0.
 * 2) The first node is null only if length == 0.
 * 3) next() of last node is always null.
 */
public class LinkedList
{
    private int length;            // to keep count of the nodes
    private ListNode firstNode;    // the first node as a reference to traverse
                                   // the nodes.

    // default constructor
    public LinkedList()
    {
        length=0;
        firstNode=null;
    }

    //non-default constructor
    public LinkedList(String data)
    {
        ListNode newNode = new ListNode(data);
        firstNode = newNode;
        length = 1;
    }

    public int getLength()
    {
        return length;
    }

    /*
     * inserts at the first node of the Linked List
     */
    public void insertAtHead(String data)
    {
    	ListNode newnode = new ListNode(data);
        if (getLength() == 0)
        {
            firstNode=newnode;
        }
        else
        {
            newnode.next=firstNode;
            firstNode=newnode;
        }
        length++;
    }

	public String toString(){ 
		String toReturn = "(";
		ListNode runner = firstNode;
		while(runner != null){
			toReturn = toReturn + runner;
			runner = runner.next;
			if(runner != null){
				toReturn = toReturn + ", ";
			}
		}
		toReturn = toReturn + ")";
		return toReturn;
	}

    /*
     * adds a node to the end of the Linked List
     */
    public void append(String data)
    {
        ListNode newNode = new ListNode(data);
        ListNode current = firstNode;

        if (firstNode == null)
        {
            firstNode = newNode;
            length++;
        }
        else 
        {
            while(current.next != null) 
            {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = null;
            length++;
        }
    }

    /*
     * inserts at a specific index of a LinkedList
     */
    public void insertAtIndex(String data, int index) 
    {
        if (index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        if (index==0) 
        {
            insertAtHead(data);
        }
        else if (index > length)
        {
            append(data);
        }
        else 
        {
        ListNode newNode = new ListNode(data);
        ListNode current = firstNode;

        for (int i = 0; i < index - 1 && current != null; i++) 
        {
            current = current.next;
        }
        if (current != null) 
        {
            newNode.next = current.next;
            current.next = newNode;
            length++;
        }
        }   
    }

    public void removeFirstNode()
    {
        if(firstNode != null)
        {
            firstNode = firstNode.next;
            length--;
        }
    }

    public void removeLastNode() 
    {
       // if list is empty
       if(firstNode == null)
       {
        return;
       }
       
       // only head node present
       if (firstNode.next == null)
       {
        firstNode = null;
        length--;
        return;
       }
       
        // more than one node
        ListNode current = firstNode;
        ListNode prev = null;
        
        while (current.next != null)
        {
            prev = current;
            current = current.next;
        }
            prev.next = null;
            length--;
        
    }

    public void removeAtIndex(int index)
    {
    // dealing with empty list, negative indices or when index>=length
    if (index < 0 || index >= length || firstNode == null)
    {
        return;
    }

    // if removing head
    if (index == 0)
    {
        firstNode = firstNode.next;
        length--;
        return;
    }

    // if removing any other except head
    ListNode current = firstNode;

    for (int i = 0; i < index - 1 && current != null; i++ )
    {
        current = current.next; 
    }
    if (current != null && current.next != null) {
    current.next = current.next.next;
    length--;
    }
    }

    /*
     * finds the index of the target string and returns it.
     */
    public int findTarget(String target)
    {
        int count = 0;
        ListNode current = firstNode;

        while(current != null) {
            if (current.data.equals(target)) {
                return count;
            }
            current = current.next;
            count++;
        }
        return -1;
    }  

    /*
     * checks if a string is in the Linked List
     */
    public boolean contains(String value)
    {
        ListNode current = firstNode;
        while (current != null && !current.data.equals(value))
        {
            current = current.next;
        }
        if (current != null && current.data.equals(value))
        {
            return true;
        }
        else 
        {
            return false;
        }
    }

    // other getter/helper functions

    public String getFirstElement()
    {
        return getNodeAt(0).data;
    }

    public String getLastelement()
    {
        return getLastNode().data;
    }

    private ListNode getLastNode()
    {
        // in case a list is empty
        if (firstNode == null)
        {
            return null;
        }

        ListNode current = firstNode;
        while (current != null && current.next != null) {
            current = current.next;
        }
        return current;
    }

    private ListNode getNodeAt(int idx)
    {
        if (idx < 0 || idx >= length)
        {
            throw new IndexOutOfBoundsException();
        }

        ListNode currentNode = firstNode;
        for (int i = 0; i < idx; i++)
        {
            currentNode =currentNode.next;
        }
        return currentNode;
    }

    public String getElementAt(int i)
    {return getNodeAt(i).data;}


    public void removeElementAtIndex(int i)
    {removeAtIndex(i);}
    

    public void clearList()
    {
        firstNode = null;
        length = 0;
    }

    public static void main(String[] args) {
        
    }
}


