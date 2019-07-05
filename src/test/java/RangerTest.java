import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RangerTest {
    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();


    public Ranger setUpRanger() {
        return new Ranger("muchui", "sg-001");
    }

    @Test
    public  void Ranger_instatiatesCorrectly() {
        Ranger testRanger = new Ranger("Beryl", "liu-001");
        assertTrue(testRanger instanceof Ranger);
    }
    @Test
    public void getName() {
       Ranger testRanger = new Ranger("muchui", "sg-001");
       assertTrue(testRanger.getName().equals("muchui"));
    }

    @Test
    public void setName() {
        Ranger testRanger = new Ranger("Chelsea", "liu-002");
        testRanger.setName("Fefe");
        assertEquals("Fefe", testRanger.getName());
    }

    @Test
    public void getBadge() {
        Ranger testRanger = new Ranger("muchui", "sg-002");
        assertEquals("sg-002", testRanger.getBadge());
    }

    @Test
    public void setBadge() {
        Ranger testRanger = new Ranger("Hamida", "mj-001");
        testRanger.setBadge("sg-001");
        assertEquals("sg-001", testRanger.getBadge());
    }

    @Test
    public void getId_idIsGivenOnceSaved() {
        Ranger testRanger = new Ranger("amos", "sg-001");
        testRanger.save();
        int rangerId = testRanger.getId();
        assertEquals(rangerId, testRanger.getId());
    }

    @Test
    public void setId() {
        Ranger testRanger = new Ranger("amos", "sg-001");
        testRanger.setId(2);
        assertEquals(2, testRanger.getId());
    }
    @Test
    public void save_savestoRangerTable() {
        Ranger testRanger = setUpRanger();
        testRanger.save();
        Ranger ranger = Ranger.find(testRanger.getId());
        assertEquals(ranger, testRanger);
    }
    @Test
    public void all_returnallInstancesOfRangers() {
        Ranger testRanger = setUpRanger();
        testRanger.save();
        Ranger another = new Ranger("you", "sg-002");
        another.save();
        assertTrue(Ranger.all().contains(testRanger));
        assertTrue(Ranger.all().contains(another));
    }
    @Test
    public void update_updatesDetails() {
        Ranger testRanger = setUpRanger();
        testRanger.save();
        Ranger ranger = new Ranger("you", "sg-002");
        testRanger.update(ranger);
        Ranger ranger1 = Ranger.find(testRanger.getId());
        assertEquals(ranger1,testRanger);
    }
}