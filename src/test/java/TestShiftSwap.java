import genetic.tsp.RouteShiftReproducer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestShiftSwap {

    @Test()
    public void testShiftSwap() {

        int[] array = new int[] {0,1,2,3,4,5,6,7,8,9};
        RouteShiftReproducer.shiftSwap(array, 7, 2);
        assertThat(array, is(new int[] {1,2,7,3,4,5,6,8,9,0}));

        array = new int[] {0,1,2,3,4,5,6,7,8,9};
        RouteShiftReproducer.shiftSwap(array, 2, 7);
        assertThat(array, is(new int[] {0,1,3,4,5,6,7,2,8,9}));

        array = new int[] {0,1,2,3,4,5,6,7,8,9};
        RouteShiftReproducer.shiftSwap(array, 2, 2);
        assertThat(array, is(array));
    }
}
