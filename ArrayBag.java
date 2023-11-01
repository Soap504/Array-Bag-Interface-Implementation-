//
// Name: Arce, Sophia
// Project: 1
// Due: 9/25/2023 (9:30)
// Course: cs-2400-02-F23
//
// Description:
//  Implement the interface BagInterface and the class ArrayBag.
//  Implement the class JavaKeywords, which uses the previously implemented interface and class:
//  Read Java keywords from the file javakeywords.txt and store them in a bag. Note that the file path should not be provided as input.
//  Check all command-line parameters and determine if they are Java keywords using the information stored in the bag.
//  Demonstrate the testing of all interfaces that are not used in part 2.b. This testing should be hardcoded and not require user input.
//  the code should not be organized into packages.
//

/**
    A class of bags whose entries are stored in a fixed-size array.
*/
public final class ArrayBag<T> implements BagInterface<T>
 {
    
    private final T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 70;
    private boolean integrityOK;
    private static final int MAX_CAPACITY = 100;


    /** Creates an empty bag whose initial capacity is 70. */
    public ArrayBag(){
        this(DEFAULT_CAPACITY);
    }// end default constructor



    /** Creates an empty bag having a given initial capacity.         
     * @param desiredCapacity  The integer capacity desired. */
    public ArrayBag(int desiredCapacity){
   integrityOK = false;
   if (desiredCapacity <= MAX_CAPACITY)
   {
      // The cast is safe because the new array contains null entries 
      @SuppressWarnings("unchecked")
      T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
      bag = tempBag;
      numberOfEntries = 0;
      integrityOK = true;                             // Last action
   }
   else 
      throw new IllegalStateException("Attempt to create a bag whose " + "capacity exceeds allowed maximum.");
    } // end constructor



    /** Adds a new entry to this bag.
    @param newEntry  The object to be added as a new entry.
    @return  True if the addition is successful, or false if not. */
    public boolean add(T newEntry){
        checkIntegrity();
            boolean result = true;
        if(isArrayFull()){
            result =false;
        }
        else{//assertion: result is true here
            bag[numberOfEntries]= newEntry;
            numberOfEntries++;
        }//end if
        return result;
    }//end add



    //returns true if the bag is full, or false if not
    public boolean isArrayFull(){
        return numberOfEntries >= bag.length;
    }//end isArrayFull



    /** Retrieves all entries that are in this bag. 
    @return  A newly allocated array of all the entries in the bag. */
    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries]; // Unchecked cast
        for (int index = 0; index < numberOfEntries; index++)
            {
                result[index] = bag[index];
            } // end for
        return result;
    } // end toArray



    public boolean isEmpty(){
        return numberOfEntries == 0;
    }//end isEmpty


    
    public int getCurrentSize(){
        return numberOfEntries;
    }//end get current size



    public int getFrequencyOf(T anEntry){
        checkIntegrity();
        int counter = 0;
        for(int index = 0; index < numberOfEntries; index++){
            if(anEntry.equals(bag[index])){
                counter++;
            }//end if
            
        }//end for
        return counter;
    }//end getFrequencyOf



    public boolean contains(T anEntry){
        checkIntegrity();
        boolean found = false;
        int index = 0;
        while(!found && (index < numberOfEntries)){
            if(anEntry.equals(bag[index])){
                found = true;
            }
            else{
                index++;
            }
        }//end while loop
        return found;
    }//end contains



    /** Removes all entries from this bag. */
    public void clear(){
        while(!isEmpty()){
            remove();
        }
    }//end clear



    public T remove(){
        checkIntegrity();
        T result = null;
        if (numberOfEntries > 0){
            result = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }//end if
        return result;
    }//end remove



    /** Removes one occurrence of a given entry from this bag.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
    public boolean remove(T anEntry){
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }



    // Throws an exception if this object is not initialized.
    private void checkIntegrity(){
        if (!integrityOK){
            throw new IllegalStateException("ArrayBag object is corrupt.");
        }//end checkIntegrity
    }

    

    private int getIndexOf(T anEntry){
        int where = -1; 
        boolean found = false;
        int index = 0;
        while(!found && (index < numberOfEntries)){
            if (anEntry.equals(bag[index])){
                found = true;
                where = index;
            }
            else
                index++;
        }// end while
   // Assertion: If where > −1, anEntry is in the array bag, and it
   // equals bag[where]; otherwise, anEntry is not in the array
    return where;
    }//end indexOf



    // Removes and returns the entry at a given index within the array bag.
    // If no such entry exists, returns null.
    // Preconditions: 0 <= givenIndex < numberOfEntries;
    //                checkIntegrity has been called.
    private T removeEntry(int givenIndex){
        T result = null;
        if (!isEmpty() && (givenIndex >= 0)){
            result = bag[givenIndex];
    //Entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // replace it with last entry
            bag[numberOfEntries - 1] = null;
    //remove last entry
            numberOfEntries--;
        }//end if
        return result;
    }//end removeEntry     
 }