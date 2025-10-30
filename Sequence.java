package pa3;  // Gradescope needs this.
/**
 *  A Sequence of Strings with a current element and a capacity.
 * Nodes are used internally but never exposed.
 * 
 * Invariants:
 * 1) capacity >= 0
 * 2) size() == contents.getLength()
 * 3) in default, currentIndex == -1 and it means "no current"
 * 4) If currentIndex != -1, then 0 <= currentIndex < size()
 * 5) The currentIndex is incremented/decremented everytime 
 *    a string is added or removed. 
 */
public class Sequence
{
	private LinkedList contents;    // the actual list which contains the nodes
	private int currentIndex;       // the current element
    private int capacity;           // the capacity of the Sequuence
    

	
    /**
     * Creates a new sequence with initial capacity 10.
     */
    public Sequence() {
        this.contents = new LinkedList();
        this.capacity = 10;
        this.currentIndex = -1;

    
    }
    
    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity){
        this.contents = new LinkedList();
        this.capacity = initialCapacity;
        this.currentIndex = -1;
    }
    

    /**
     * Adds a string to the sequence in the location before the
     * current element. If the sequence has no current element, the
     * string is added to the beginning of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addBefore(String value)
    {
        // when capacity is full
        if (size() == getCapacity())
        {
            ensureCapacity((size()*2) + 1);
        }
        // if no current element
        if (!isCurrent())
        {
            contents.insertAtHead(value);
            currentIndex = 0;
        }
        else
        {
            contents.insertAtIndex(value, currentIndex);
        }

    }
    
    
    /**
     * Adds a string to the sequence in the location after the current
     * element. If the sequence has no current element, the string is
     * added to the end of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addAfter(String value)
    {
        // when capacity is full
        if (size() == getCapacity())
        {
            ensureCapacity((size()*2) + 1);
        }
        // if no cuurent element
        if (!isCurrent())
        {
            contents.append(value);
            currentIndex = size() - 1;
        }
        // if there is a current element
        else
        {
            contents.insertAtIndex(value, currentIndex + 1);
            currentIndex++;
        }
    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent()
    {
        if (currentIndex >= 0 && currentIndex < size()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity()
    {
        return this.capacity;
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent()
    {
        if (!isCurrent())
        {
            return null;
        }
        else
        {
            return contents.getElementAt(currentIndex);
        }
    }
    
    
    /**
     * Increase the sequence's capacity to be
     * at least minCapacity.  Does nothing
     * if current capacity is already >= minCapacity.
     *
     * @param minCapacity the minimum capacity that the sequence
     * should now have.
     */
    public void ensureCapacity(int minCapacity)
    {
        if (capacity < minCapacity)
        {
            this.capacity = minCapacity;
        }
    }

    
    /**
     * Places the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make (just enough) room for
     * all of the elements to be added.
     * 
     * Postcondition: NO SIDE EFFECTS!  the other sequence should be left
     * unchanged.  The current element of both sequences should remain
     * where they are. (When this method ends, the current element
     * should refer to the same element that it did at the time this method
     * started.)
     *
     * @param another the sequence whose contents should be added.
     */
    public void addAll(Sequence another)
    {
        // ensures the capacity is sufficient
        int total_size = this.size() + another.size();
        if (total_size > this.getCapacity()) {
            ensureCapacity(total_size);
        }

        llcopy(another.contents, 0, this.contents, this.size(), another.size());

    }

    
    /**
     * Move forward in the sequence so that the current element is now
     * the next element in the sequence.
     *
     * If the current element was already the end of the sequence,
     * then advancing causes there to be no current element.
     *
     * If there is no current element to begin with, do nothing.
     */
    public void advance()
    {
        if (!isCurrent())
        {
            return;
        }
        if (currentIndex == size() - 1)
        {
            currentIndex = -1;
        }
        else
        {
            currentIndex++;
        }
    }

    
    /**
     * Make a copy of this sequence.  Subsequence changes to the copy
     * do not affect the current sequence, and vice versa.
     * 
     * Postcondition: NO SIDE EFFECTS!  This sequence's current
     * element should remain unchanged.  The clone's current
     * element will correspond to the same place as in the original.
     *
     * @return the copy of this sequence.
     */
    public Sequence clone()
    {
        Sequence cloneSequence = new Sequence(this.getCapacity());

        // copy the contents
        llcopy(contents, 0, cloneSequence.contents, 0, size());
        
        // ensuring size and currentIndex
        
        cloneSequence.currentIndex = this.currentIndex;
        return cloneSequence;
    }
   
    
    /**
     * Remove the current element from this sequence.  The following
     * element, if there was one, becomes the current element.  If
     * there was no following element (current was at the end of the
     * sequence), the sequence now has no current element.
     *
     * If there is no current element, does nothing.
     */
    public void removeCurrent()
    {
        if (!isCurrent())
        {
            return;
        }

        contents.removeAtIndex(currentIndex);
        if (currentIndex >= size())
        {
            currentIndex = -1;
        }
    }

    
    /**
     * @return the number of elements stored in the sequence.
     */
    public int size()
    {
        return contents.getLength();
    }

    
    /**
     * Sets the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start()
    {
        if (isEmpty())
        {
            currentIndex = -1;
        }
        else
        {
            currentIndex = 0;
        }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize()
    {
        this.capacity = size();
    }
    
    
    /**
     * Produce a string representation of this sequence.  The current
     * location is indicated by a >.  For example, a sequence with "A"
     * followed by "B", where "B" is the current element, and the
     * capacity is 5, would print as:
     * 
     *    {A, >B} (capacity = 5)
     * 
     * The string you create should be formatted like the above example,
     * with a comma following each element, no comma following the
     * last element, and all on a single line.  An empty sequence
     * should give back "{}" followed by its capacity.
     * 
     * @return a string representation of this sequence.
     */
    public String toString() 
    {
        String StringConverter = new String();
        StringConverter = "{";
        for (int i = 0; i < size(); i++) {
            if (i > 0) {
                StringConverter+= ", "; // comma only after first one
            }
            if (i==currentIndex) {
                StringConverter+= ">";
            }
            StringConverter+= contents.getElementAt(i);
        }
        StringConverter+= "} (capacity = " + getCapacity() + ")";
        return StringConverter;
    }
    
    /**
     * Checks whether another sequence is equal to this one.  To be
     * considered equal, the other sequence must have the same size
     * as this sequence, have the same elements, in the same
     * order, and with the same element marked
     * current.  The capacity can differ.
     * 
     * Postcondition: NO SIDE EFFECTS!  this sequence and the
     * other sequence should remain unchanged, including the
     * current element.
     * 
     * @param other the other Sequence with which to compare
     * @return true iff the other sequence is equal to this one.
     */
    public boolean equals(Sequence other) 
    {
    // when two sequences are the exact same.
    if (this == other) {
        return true;
    }
    // checks for 2 empty sequences
    if (isEmpty() && other.isEmpty()) {
        return true;
    }
    // checks the size of two sequences
    if (size() != other.size()) {
        return false;
    }
    // checks if current element is present for both
    if (isCurrent() != other.isCurrent()) {
        return false;
    }
    // checks for same current element
    if (isCurrent() && other.isCurrent() && 
        this.currentIndex != other.currentIndex) 
    {
        return false;
    }
    for (int i=0; i < size(); i++) {
        if (!contents.getElementAt(i).equals(other.getElementAtIndex(i))) {
            return false;
        }
    }
    return true;
    }
    
    
    /**
     * 
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty()
    {
        if (size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     *  empty the sequence.  There should be no current element.
     */
    public void clear()
    {
        contents.clearList();
        currentIndex = -1;
        
    }

    // helper methods

    public String getElementAtIndex(int i)
    {
        return contents.getElementAt(i);
    }

    /**
     * Copies an array from the specified source array, beginning at the specified 
     * position, to the specified position of the destination array.
     * @param source the original array
     * @param srcPos starting position in the original array
     * @param dest array to which elements are to be copied
     * @param destPos starting position of the destination array
     * @param size the number of elements to be copied
     */
    private void llcopy(LinkedList source, int srcPos, LinkedList dest, int destPos, int size) 
    {
        if (size < 0)
        {throw new IndexOutOfBoundsException(); }

        String[] temp = new String[size];
        for (int i = 0; i < size; i++) 
        {
        temp[i] = source.getElementAt(srcPos + i);
        }
        for (int j = 0; j < size; j++) 
        {
        dest.insertAtIndex(temp[j], destPos + j);
        }
    }

}
