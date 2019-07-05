import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class PlaceTest {
    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "victor", "Am0skwito");
    }

    @After
    public void tearDown() throws Exception {
        try (Connection con = DB.sql2o.open()){
            String initialize = "DELETE FROM places *;";
            con.createQuery(initialize).executeUpdate();
        }
    }
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
}