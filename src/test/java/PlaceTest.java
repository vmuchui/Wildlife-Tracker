import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;


public class PlaceTest {
    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();
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


    @Test
    public void delete() {
      Place place = new Place("river");
      place.save();
      Place newplace = new Place("north");
      newplace.save();
      place.delete();
      assertFalse(Place.all().contains(place));
      assertTrue(Place.all().contains(newplace));
    }

    @Test
    public void save() {
        Place place = new Place("river");
        place.save();
        assertTrue(Place.all().contains(place));
    }

    @Test
    public void find() {
        Place place = new Place("river");
        place.save();
        Place newPlace = Place.find(place.getId());
        assertEquals(place,newPlace);
    }
    @Test
    public void getSightings() {
        Place place = new Place("river");
        place.save();
        Sighting sighting = new Sighting(place.getId(),12,2);
        sighting.save();
        assertTrue(place.getSightings().contains(sighting));
    }
}