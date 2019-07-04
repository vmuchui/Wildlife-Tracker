import org.junit.Test;

import static org.junit.Assert.*;

public class PlaceTest {
    @Test
    public void Place_instatiatesCorrectly_True() {
        Place newPlace = new Place("river");
        assertTrue(newPlace instanceof Place);
    }
    @Test
    public void getName() {
        Place location = new Place("river");
        assertEquals("river", location.getName());
    }

    @Test
    public void setName() {
        Place place = new Place("river");
        place.setName("doof");
        assertEquals("doof", place.getName());
    }

    @Test
    public void getId() {
        Place place = new Place("river");
        place.setId(2);
        assertEquals(2,place.getId());
    }

    @Test
    public void setId() {
        Place place = new Place("river");
        place.setId(3);
        assertEquals(3,place.getId());
    }
}