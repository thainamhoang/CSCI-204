package heatingRod;

import java.util.Scanner;
import java.util.Arrays;

public class HeatingRod {
    final static int ROOM_TEMPERATURE = 20;

    private static int rodLength = 0;
    private static float leftTemperature = 0;
    private static float rightTemperature = 0;
    private static boolean isEquilibrium = true;


    public static void setRodLength (int rodLength) {
        HeatingRod.rodLength = rodLength;
    }

    public static void setLeftTemperature (float leftTemperature) {
        HeatingRod.leftTemperature = leftTemperature;
    }

    public static void setRightTemperature (float rightTemperature) {
        HeatingRod.rightTemperature = rightTemperature;
    }

    public static void setIsEquilibrium (boolean isEquilibrium) {
        HeatingRod.isEquilibrium = isEquilibrium;
    }

    public static int getRodLength() {
        return rodLength;
    }

    public static float getLeftTemperature() {
        return leftTemperature;
    }

    public static float getRightTemperature() {
        return rightTemperature;
    }

    public static boolean isIsEquilibrium() {
        return isEquilibrium;
    }

    public static int getInput () {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What is the size of the array? ");
        setRodLength(scanner.nextInt());

        System.out.print("Temperature at the left: ");
        setLeftTemperature((float) scanner.nextInt());

        System.out.print("Temperature at the right: ");
        setRightTemperature((float) scanner.nextInt());

        System.out.print("How many iterations should we run? ");
        return scanner.nextInt();
    }

    public static float[] initializeRod () {
        float[] initialRod = new float[getRodLength()];

        initialRod[0] = getLeftTemperature();
        initialRod[getRodLength() - 1] = getRightTemperature();

        Arrays.fill(initialRod, 1, getRodLength() - 1, ROOM_TEMPERATURE);

        return initialRod;
    }

    public static float[] updateTemps (float[] currentRod) {
        float[] tempRod = currentRod.clone();
        for (int i = 1; i < getRodLength() - 1; i++) {
            tempRod[i] = (currentRod[i - 1] + currentRod[i] + currentRod[i + 1]) / 3;
        }
        return tempRod;
    }

    public static boolean checkStable (float[] currentRod) {
        boolean isStable = true;
        for (int i = 1; i < getRodLength() - 1; i++) {
            if (((currentRod[i - 1] + currentRod[i] + currentRod[i + 1]) / 3) != currentRod[i]) {
                isStable = false;
            }
        }
        return isStable;
    }

    public static float[] simulateHeating (float[] currentRod, int iteration) {
        float[] tempRod = currentRod.clone();
        setIsEquilibrium(true);
        for (int i = 0; i < iteration; i++) {
            setIsEquilibrium(checkStable(tempRod));
            if (isIsEquilibrium()) {
                break;
            }
            tempRod = updateTemps(tempRod);
        }
        return tempRod;
    }

    public static String toString (float[] currentRod) {

        /* I choose to use StringBuilder instead of String or StringBuffer because:
         * 1. String is immutable, so if I perform a concatenation it will create another String,
         * results in increasing file size and affecting the runtime.
         * 2. StringBuffer is synchronous, meaning that it can be called by a single thread at a time,
         * while StringBuilder is non-synchronous and can be called in multiple threads at a time.
         * This is why StringBuilder performs better than StringBuffer.
         */

        StringBuilder result = new StringBuilder ();
        for (float temperature: currentRod) {
            String str = String.format("%.01f", temperature);
            result.append(str);
            result.append(", ");
        }
        return (result.substring(0, result.length() - 2));
    }

    public static void main(String[] args) {
        int iteration = getInput();
        float[] initialRod = initializeRod();
        float[] finalTemps = simulateHeating(initialRod, iteration);
        String result = toString(finalTemps);
        System.out.println("Final temperatures, to one decimal place:");
        System.out.print(result);
    }
}
