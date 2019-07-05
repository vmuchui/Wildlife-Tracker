import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class SightingTest {


   @Rule
   public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void Sighting_instatiatesCorrectly() {
        Sighting sighting = new Sighting(2,2,3);
        assertTrue(sighting instanceof Sighting);
    }
    @Test
    public void getId() {
        Sighting sighting = new Sighting(1,2,3);
        sighting.save();
        int id = sighting.getId();
        assertEquals(id,sighting.getId());
    }

    @Test
    public void setId() {
        Sighting sighting = new Sighting(1,2,3);
        sighting.save();
        sighting.setId(2);
        assertEquals(2, sighting.getId());
    }

    @Test
    public void getPlaceid() {
        Sighting sighting = new Sighting(1,2,3);
        assertEquals(1,sighting.getPlaceid());
    }

    @Test
    public void setPlaceid() {
        Sighting sighting = new Sighting(1,2,3);
        sighting.setPlaceid(3);
        assertEquals(3,sighting.getPlaceid());
    }

    @Test
    public void getAnimalid() {
        Sighting sighting = new Sighting(1,2,3);
        assertEquals(2,sighting.getAnimalid());
    }

    @Test
    public void setAnimalid() {
        Sighting sighting = new Sighting(1,2,3);
        sighting.setAnimalid(3);
        assertEquals(3,sighting.getAnimalid());
    }

    @Test
    public void getRangerid() {
        Sighting sighting = new Sighting(1,2,3);
        assertEquals(3,sighting.getRangerid());
    }

    @Test
    public void setRangerid() {
        Sighting sighting = new Sighting(1,2,3);
        sighting.setRangerid(4);
        assertEquals(4,sighting.getRangerid());
    }

    @Test
    public void find() {
        Sighting sighting = new Sighting(1, 2, 3);
        sighting.save();
        assertEquals(sighting, Sighting.find(sighting.getId()));
    }

    @Test
    public void delete() {
        Sighting sighting = new Sighting(1, 2, 3);
        sighting.save();
        Sighting newOne = new Sighting(2,3,4);
        newOne.save();
        sighting.delete();
        assertFalse(Sighting.all().contains(sighting));
        assertTrue(Sighting.all().contains(newOne));
    }

    @Test
    public void save() {
        Place place = new Place("river");
        Ranger ranger = new Ranger("muchui", "sg-001");
        Animal animal = new Animal("lion",12, "ill",true);
        Sighting sighting = new Sighting(place.getId(),animal.getId(),ranger.getId());
        sighting.save();
        Sighting fetched = Sighting.find(sighting.getId());
        assertEquals(sighting, fetched);
    }

    @Test
    public void deleteAll() {
        Sighting sighting = new Sighting(1, 2, 3);
        sighting.save();
        Sighting newOne = new Sighting(2,3,4);
        newOne.save();
        Sighting.deleteAll();
        assertFalse(Sighting.all().contains(sighting));
        assertFalse(Sighting.all().contains(newOne));
    }

}