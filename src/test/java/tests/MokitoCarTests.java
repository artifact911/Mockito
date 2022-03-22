package tests;

import com.car.Car;
import com.car.parts.Engine;
import com.car.parts.FuelTank;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MokitoCarTests {

    private Car car;
    private Engine engine;
    private FuelTank fuelTank;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        engine = Mockito.mock(Engine.class);
        fuelTank = Mockito.mock(FuelTank.class);

        car = new Car(engine, fuelTank);
    }

    @Test
    public void isRunning() {
        when(engine.isRunning()).thenReturn(true);
        assertEquals(true, car.isRunning());

        when(engine.isRunning()).thenReturn(false);
        assertEquals(false, car.isRunning());
    }

    @Test
    //this test will be failed if you don't uncomment the line inside
    //check why it happens
    public void start() {
        when(engine.isRunning()).thenReturn(false, true);
        when(fuelTank.getFuel()).thenReturn(100);
        car.start();
    }

    @Test
    public void start_NoFuel() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("no fuel");
        when(engine.isRunning()).thenReturn(false);
        when(fuelTank.getFuel()).thenReturn(0);
        car.start();
    }

    @Test
    public void start_IsRunning() {
        //write here the implementation of the start_isRunning test:
        //the expected steps:
        // 1. declare IllegalStateException.class as it is written above (check out the start_NoFuel test)
        // 2. expect right message
        // 3. mock it: the method isRunning should return true
        // 4. use car.start()
        // the test should be passed.
    }

    @Test
    public void start_DidNotStart() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Started engine but isn't running");
        when(engine.isRunning()).thenReturn(false, false);
        when(fuelTank.getFuel()).thenReturn(100);
        car.start();
    }
}
