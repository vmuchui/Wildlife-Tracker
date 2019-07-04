import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Ranger implements Wildlife{


    private String name;
    private String badge;
    private int id;

    public Ranger(String name, String badge) {
        this.name = name;
        this.badge = badge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static List<Ranger> all() {
        try (Connection conn = DB.sql2o.open()){
            String sql = "SELECT * FROM rangers;";
            return conn.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public static Ranger find(int id) {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM rangers WHERE id = :id;";
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Ranger.class);
        }catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public void update(Ranger ranger) {
        this.name = ranger.name;
        this.badge = ranger.badge;
        try (Connection con = DB.sql2o.open()){
            String sql = "UPDATE rangers SET (name, badge) = (:name, :badge) WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("name", ranger.name)
                    .addParameter("badge", ranger.badge)
                    .addParameter("id", this.id).executeUpdate();
        }
    }

    @Override
    public void delete() {
        try (Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM rangers * WHERE id = :id;";
            con.createQuery(sql).addParameter("id",this.id).executeUpdate();
        }

    }



    @Override
    public void save() {
        try (Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO rangers (name, badge) VALUES (:name, :badge);";

            this.id= (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("badge", this.badge).executeUpdate().getKey();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return getId() == ranger.getId() &&
                getName().equals(ranger.getName()) &&
                getBadge().equals(ranger.getBadge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBadge(), getId());
    }
}
