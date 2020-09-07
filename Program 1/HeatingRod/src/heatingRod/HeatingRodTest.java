package heatingRod;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HeatingRodTest {
    @Test
    public void testInitializeRod() {
        //First Test
        HeatingRod.setRodLength(6);
        HeatingRod.setLeftTemperature(80);
        HeatingRod.setRightTemperature(50);
        float[] rodStartState = HeatingRod.initializeRod();
        assertEquals(rodStartState[0], 80.0, 0.05);
        assertEquals(rodStartState[5], 50.0, 0.05);
        for (int i=1; i<=4; i++) {
            assertEquals(rodStartState[i], 20.0, 0.05);
        }

        //Second Test
        HeatingRod.setRodLength(3);
        HeatingRod.setLeftTemperature(40);
        HeatingRod.setRightTemperature(70);
        rodStartState = HeatingRod.initializeRod();
        assertEquals(rodStartState[0], 40.0, 0.05);
        assertEquals(rodStartState[1], 20.0, 0.05);
        assertEquals(rodStartState[2], 70.0, 0.05);

        //Third Test
        HeatingRod.setRodLength(10);
        HeatingRod.setLeftTemperature(20);
        HeatingRod.setRightTemperature(20);
        rodStartState = HeatingRod.initializeRod();
        for (int i=0; i<10; i++) {
            assertEquals(rodStartState[i], 20.0, 0.05);
        }
    }

    @Test
    public void testUpdateTemps() {
        //First Test
        HeatingRod.setRodLength(6);
        HeatingRod.setLeftTemperature(80);
        HeatingRod.setRightTemperature(50);
        float[] rodStartState = HeatingRod.initializeRod();
        float[] rodEndState = HeatingRod.updateTemps( rodStartState );
        assertEquals(rodEndState[0], 80.0, 0.05);
        assertEquals(rodEndState[1], 40.0, 0.05);
        assertEquals(rodEndState[2], 20.0, 0.05);
        assertEquals(rodEndState[3], 20.0, 0.05);
        assertEquals(rodEndState[4], 30.0, 0.05);
        assertEquals(rodEndState[5], 50.0, 0.05);

        //Second Test
        HeatingRod.setRodLength(3);
        HeatingRod.setLeftTemperature(81);
        HeatingRod.setRightTemperature(7);
        rodStartState = HeatingRod.initializeRod();
        rodEndState = HeatingRod.updateTemps( rodStartState );
        assertEquals(rodEndState[0], 81.0, 0.05);
        assertEquals(rodEndState[1], 36.0, 0.05);
        assertEquals(rodEndState[2], 7.0, 0.05);

        //Third Test
        HeatingRod.setRodLength(10);
        HeatingRod.setLeftTemperature(67);
        HeatingRod.setRightTemperature(11);
        rodStartState = HeatingRod.initializeRod();
        rodEndState = HeatingRod.updateTemps( rodStartState );
        assertEquals(rodEndState[0], 67.0, 0.05);
        assertEquals(rodEndState[1], 35.7, 0.05);
        assertEquals(rodEndState[8], 17.0, 0.05);
        assertEquals(rodEndState[9], 11.0, 0.05);
        for (int i=2; i<8; i++) {
            assertEquals(rodStartState[i], 20.0, 0.05);
        }
    }

    @Test
    public void simulateHeating() {
        //First Test
        HeatingRod.setRodLength(6);
        HeatingRod.setLeftTemperature(80);
        HeatingRod.setRightTemperature(50);
        float[] rodStartState = HeatingRod.initializeRod();
        float[] rodEndState = HeatingRod.simulateHeating( rodStartState, 2 );
        assertEquals(rodEndState[0], 80.0, 0.05);
        assertEquals(rodEndState[1], 46.7, 0.05);
        assertEquals(rodEndState[2], 26.7, 0.05);
        assertEquals(rodEndState[3], 23.3, 0.05);
        assertEquals(rodEndState[4], 33.3, 0.05);
        assertEquals(rodEndState[5], 50.0, 0.05);

        //Second Test
        HeatingRod.setRodLength(10);
        HeatingRod.setLeftTemperature(121);
        HeatingRod.setRightTemperature(-7);
        rodStartState = HeatingRod.initializeRod();
        rodEndState = HeatingRod.simulateHeating( rodStartState, 20 );
        assertEquals(rodEndState[0], 121.0, 0.05);
        assertEquals(rodEndState[1], 98.9, 0.05);
        assertEquals(rodEndState[2], 78.1, 0.05);
        assertEquals(rodEndState[3], 59.4, 0.05);
        assertEquals(rodEndState[4], 43.5, 0.05);
        assertEquals(rodEndState[5], 30.1, 0.05);
        assertEquals(rodEndState[6], 19.0, 0.05);
        assertEquals(rodEndState[7], 9.5, 0.05);
        assertEquals(rodEndState[8], 1.0, 0.05);
        assertEquals(rodEndState[9], -7.0, 0.05);

        //Third Test
        HeatingRod.setRodLength(3);
        HeatingRod.setLeftTemperature(0);
        HeatingRod.setRightTemperature(0);
        rodStartState = HeatingRod.initializeRod();
        rodEndState = HeatingRod.simulateHeating( rodStartState, 6 );
        assertEquals(rodEndState[0], 0, 0.05);
        assertEquals(rodEndState[1], 0, 0.05);
        assertEquals(rodEndState[2], 0, 0.05);
    }

    @Test
    public void testToStringFloatArray() {
        //First Test
        HeatingRod.setRodLength(6);
        HeatingRod.setLeftTemperature(80);
        HeatingRod.setRightTemperature(50);
        float[] rodStartState = HeatingRod.initializeRod();
        String formatted = HeatingRod.toString( rodStartState );
        assertTrue( formatted.equals("80.0, 20.0, 20.0, 20.0, 20.0, 50.0") );

        //Second Test
        HeatingRod.setRodLength(3);
        HeatingRod.setLeftTemperature(40);
        HeatingRod.setRightTemperature(30);
        rodStartState = HeatingRod.initializeRod();
        float[] rodEndState = HeatingRod.simulateHeating( rodStartState, 1 );
        formatted = HeatingRod.toString( rodEndState );
        assertTrue( formatted.equals("40.0, 30.0, 30.0") );
    }

}