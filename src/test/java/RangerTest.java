import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class RangerTest {
    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "victor", "Am0skwito");
    }

    @After
    public void tearDown() throws Exception {
        try (Connection con = DB.sql2o.open()){
            String initializerangers = "DELETE FROM rangers *;";
            con.createQuery(initializerangers).executeUpdate();
        }
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
    public voi
}