import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DataBaseRule extends ExternalResource {
    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "victor", "Am0skwito");
    }
    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()){
            String initializeAnimals = "DELETE FROM animals *;";
            String initializeSightings = "DELETE FROM sightings *;";
            String initialize = "DELETE FROM places *;";
            String initializeRangers = "DELETE FROM rangers *;";
            con.createQuery(initializeRangers).executeUpdate();
            con.createQuery(initialize).executeUpdate();
            con.createQuery(initializeSightings).executeUpdate();
            con.createQuery(initializeAnimals).executeUpdate();
        }
    }
}