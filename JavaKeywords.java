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


//testing the interfaces
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JavaKeywords {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        // Create a Bag object to store the lines.
        BagInterface <String> myBag = new ArrayBag<>();
        String line;
        File file = new File("JavaKeywords.txt");
        Scanner scnr = new Scanner(file);
        //adding keywords to myBag
        while(scnr.hasNextLine()){
            line = scnr.nextLine();
            myBag.add(line);
        }
        scnr.close();
       
        System.out.println("Java Keywords by S. Arce");
        System.out.println("\n" + myBag.getCurrentSize() + " Java keywords loaded.\n");

        //testing the command through java compiler 
        for(int i = 0; i < args.length; i++){
            if(myBag.contains(args[i])){
                System.out.println(args[i] +" is a keyword");
            }
            else if(!myBag.contains(args[i])){
                System.out.println(args[i] + " is not a keyword");
            }
        }



        //TESTING MY OUTPUT

        System.out.println("\nYour testing output here...");

        //removing 5 java keywords from myBag
        //get the amount of remaining words
        //testing remove()
        System.out.println("\nRemove 5-Keywords:");
        int counter = 5;

        while(counter > 0){
            myBag.remove();
            counter--;
        }
        
        System.out.println("    Kewyords remaining: " + myBag.getCurrentSize());
        
        //adding 4 new words to myBag
        //testing add(T new Entry)
        myBag.add("clasess");
        myBag.add("primitive");
        myBag.add("char");
        myBag.add("true");

        //testing toArray()
        Object[] arr = myBag.toArray();

        //using getFrequencyOf() to find duplicates in myBag
        //if: present remove on of them in order to get rid of duplicates
        //else: do nothing
        //testing getCurrentSize(), getFrequencyOf(), remove(anEntry)
        System.out.println("\nAre there any duplicates in the bag? If so remove them: \n");
        counter = 0;
        for(int index = 0; index < myBag.getCurrentSize();index++ ){
            String word = (String) arr[index];
            if(myBag.getFrequencyOf(word) > 1){
                System.out.println("    Duplicate found: " + word + ".");
                myBag.remove(word);
                System.out.println("    " + word + " removed.\n");
                counter++;
            }
        }

        System.out.println("    Total amount: " + counter );
        System.out.println("\n\nRemove ALL items: ");
        System.out.println("\n    Initial amount of keywords ~ " + myBag.getCurrentSize());
       

        int size = myBag.getCurrentSize();

        //empty the bag with clear() if the bag has at-least 50 words
        //testing clear()
        if(size >= 50){
            myBag.clear();
        }
        System.out.println("    Kewyords remaining: " + myBag.getCurrentSize());

        System.out.println("\nADD new words: ");

        //add new words to myBag if it is empty
        //testing isEmpty()
        if(myBag.isEmpty()){
            myBag.add("enhanced");
            myBag.add("compiler");
        }

        System.out.println("\n    Final size: " + myBag.getCurrentSize());
        System.out.println("    New keywords: ");

        //use toArray to print the new and total words in myBag
        Object[] myArray = myBag.toArray();

        for(int i = 0; i < myBag.getCurrentSize(); i++){
            System.out.println("                 *" + myArray[i]);
        }

        System.out.println();   
    }
}