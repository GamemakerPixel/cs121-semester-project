package week6.testactivity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieTest {
    @Test
    public void setTemperatureBakesPie(){
        Pie pie = new Pie("Cherry", 10, 200.0);

        assertFalse(pie.getBaked());
        pie.setTemperature(Pie.BAKE_TEMPERATURE);
        assertTrue(pie.getBaked());

    }

    @Test
    public void defaultContructorWorks(){
        Pie pie = new Pie("Peach", 25, 130.0);

        assertNotNull(pie);
        assertEquals("Peach", pie.getFlavor());
        assertEquals(25, pie.getSlices());
        assertEquals(130.0, pie.getTemperature());
        assertFalse(pie.getBaked());
    }
}