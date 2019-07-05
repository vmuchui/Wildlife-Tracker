import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Place implements Wildlife{
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Place(String name) {
        this.name = name;
    }

    public static List<Place> all() {
        try (Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM places;";
            return conn.createQuery(sql).executeAndFetch(Place.class);
        }
    }

    public List<Sighting> getSightings() {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings WHERE placeid = :placeid;";
            return con.createQuery(sql).addParameter("placeid",this.id).executeAndFetch(Sighting.class);
        }
    }

    public static Place find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM places WHERE id=:id;";
           return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Place.class);
        }
    }

    @Override
    public void delete() {
        try (Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM places * WHERE id = :id;";
            con.createQuery(sql).addParameter("id",this.id).executeUpdate();
        }
    }

    @Override
    public void save() {
        try (Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO places (name) VALUES(:name);";
            this.id = (int) connection.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .executeUpdate().getKey();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;
        Place place = (Place) o;
        return getId() == place.getId() &&
                getName().equals(place.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}


