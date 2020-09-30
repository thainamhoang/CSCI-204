package binaryTrees;

import java.util.Calendar;
import java.util.Random;

/** This is a "facade" class to the actual Java Random class.
 *  Its purpose is just to reduce the number of constructors and methods to the
 *  ones that we need, and add a "randomBetween" method.
 *  @author Darrah Chavey
 *
 */
public class MyRandom {

    private Random myRandom;

    /** Constructor, with a seed. Especially useful for testing & debugging */
    public MyRandom( long seed ) {
        myRandom = new Random(seed);
    }

    /** Constructor, with a random seed. Especially useful for real experiments */
    public MyRandom( ) {
        // Use the current clock time for the seed.
        Calendar calendar = Calendar.getInstance();
        long seed = calendar.getTimeInMillis();
        myRandom = new Random( seed );
    }

    /** Returns a "random" number between the lower and upper bounds, inclusive.
     *  Thus "randomBetween(1,3)" will return each of {1, 2, 3} with
     *  approximately the same frequency.
     *  @lowerBound the smallest number which should be returned
     *  @upperBound the largest number which should be returned.
     */
    public int randomBetween( int lowerBound, int upperBound ) {
        int placeWithinTheRange = myRandom.nextInt( upperBound - lowerBound + 1 );
        return (placeWithinTheRange + lowerBound);
    }

}