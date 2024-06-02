package test.java;

import org.junit.Test;

public class RemoveEmployeeFrameTest {

    @Test

    public void testNewRemoveEmployeeFrame() {

        new RemoveEmployeeFrame(new ViewEmployeeFrame());
    }


    @Test

    public void testNewRemoveEmployeeFrame2() {


        new RemoveEmployeeFrame(null);
    }
}
