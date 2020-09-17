package linkedList;

public class Driver {
    public static void main( String[ ] args) {
        LinkedList mainList = new LinkedList( new int[] {2, 3,5,7,13} );
        System.out.println("Some of the first primes: " + mainList.toString());
        LinkedList singleItem = new LinkedList( new int[] {11} );
        mainList.insertListAfterKey( singleItem, 7);
        System.out.println("Added the missing 11: " + mainList.toString());
        LinkedList morePrimes = new LinkedList( new int[] {31,37} );
        mainList.insertListAfterKey( morePrimes, 13);
        System.out.println("Added primes in the 30s: " + mainList.toString());
        LinkedList missingPrimes = new LinkedList( new int[] {17,19,23,29} );
        mainList.insertListAfterKey(missingPrimes, 13);
        System.out.println("Added the missing primes: " +  mainList.toString());
        System.out.println("The following should result in an exception halting the program:");
        mainList.insertListAfterKey( morePrimes, 21 );
    }
}
