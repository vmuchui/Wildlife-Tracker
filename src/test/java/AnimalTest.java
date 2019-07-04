import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.*;

import static org.junit.Assert.*;

public class AnimalTest {

    @Before
    public void setUp() throws Exception {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "victor", "Am0skwito");
    }

    @After
    public void tearDown() throws Exception {
        try (Connection con = DB.sql2o.open()){
            String initializeAnimals = "DELETE FROM animals *;";
            con.createQuery(initializeAnimals).executeUpdate();
        }
    }
    @Test
    public void Animal_instatiatesCorrectly_True() {
        Animal testAnimal = new Animal("lion", 12, "healthy", false);
        assertTrue(testAnimal instanceof Animal);
    }
    @Test
    public void getSpecies_returnsString_leo() {
       Animal testAnimal = setUpAnimal();
       assertEquals("lion",testAnimal.getSpecies());
    }

    @Test
    public void setSpecies() {


        Animal testAnimal = setUpAnimal();
        testAnimal.setSpecies("leopard");
        assertEquals("leopard", testAnimal.getSpecies());
    }

    @Test
    public void getAge() {
        Animal testAnimal = setUpAnimal();
        assertEquals(12,testAnimal.getAge());
    }

    @Test
    public void setAge() {
        Animal testAnimal = setUpAnimal();
        testAnimal.setAge(5);
        assertEquals(5,testAnimal.getAge());
    }

    @Test
    public void getHealth() {
        Animal testAnimal = setUpAnimal();
        assertEquals("healthy", testAnimal.getHealth());
    }

    @Test
    public void setHealth() {
        Animal testAnimal = setUpAnimal();
        testAnimal.setHealth("ill");
        assertEquals("ill", testAnimal.getHealth());
    }

    @Test
    public void isEndangered() {
        Animal testAnimal = setUpAnimal();
        assertEquals(false,testAnimal.isEndangered());
    }

    @Test
    public void setEndangered() {
        Animal testAnimal = setUpAnimal();
        testAnimal.setEndangered(true);
        assertEquals(true,testAnimal.isEndangered());
    }

    public Animal setUpAnimal() {
        return new Animal("lion", 12, "healthy", false);
    }

    @Test
    public void getId_returnsIdGivenInDatabase() {
        Animal testAnimal = setUpAnimal();
        testAnimal.save();
        Animal fetch = Animal.find(testAnimal.getId());
        assertEquals(fetch.getId(), testAnimal.getId());
    }

    @Test
    public void setId() {
        Animal testAnimal = setUpAnimal();
        testAnimal.setId(4);
        assertEquals(4,testAnimal.getId());
    }
    @Test
    public void find_returnsNullWhenAnIdIsNotThere_true() {
        Animal newone = setUpAnimal();
        newone.save();
        Animal testAnimal = Animal.find(2343);
        assertTrue(testAnimal==null);
    }
    @Test
    public void save_savestotheDatabase_true() {
        Animal testAnimal = setUpAnimal();
        testAnimal.save();
        Animal getAnimal = Animal.find(testAnimal.getId());
        assertEquals(getAnimal, testAnimal);
    }
    @Test
    public void save_savesOnInstatiation_true() {
        Animal testAnimal = setUpAnimal();
        testAnimal.save();
        Animal fetched = Animal.find(testAnimal.getId());
        assertEquals(fetched,testAnimal);
    }
    @Test
    public void delete_removesTheSpecifiedAnimalObject_false() {
        Animal testAnimal = setUpAnimal();
        testAnimal.save();
        testAnimal.delete();
        Animal nil = Animal.find(testAnimal.getId());
        assertNotEquals(nil, testAnimal);
    }
    @Test
    public void update_updatesCurrentInfo() {
        Animal testAnimal = setUpAnimal();
        testAnimal.save();
        Animal newAnimal = new Animal("amos", 2, "ill", true);
        testAnimal.update(newAnimal);
        Animal fetch = Animal.find(testAnimal.getId());
        assertEquals(fetch, testAnimal);
    }
    @Test
    public void all_returnsAllSavedAnimals_true() {
        Animal testAnimal = setUpAnimal();
        testAnimal.save();
        Animal newTest = new Animal("amos", 12, "ill", true);
        newTest.save();
        assertTrue(Animal.all().contains(testAnimal));
        assertTrue(Animal.all().contains(newTest));
    }
    @Test
    public void deleteAll_deletesAllAnimalInstancesCreated() {
        Animal testAnimal = setUpAnimal();
        testAnimal.save();
        Animal newTest = new Animal("amos", 12, "ill", true);
        newTest.save();
        Animal.deleteAll();
        assertFalse(Animal.all().contains(testAnimal));
        assertFalse(Animal.all().contains(newTest));
    }
}