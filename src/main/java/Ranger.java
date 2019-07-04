import org.sql2o.*;

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



    @Override
    public void delete() {

    }

    public void update(Object o) {

    }

    @Override
    public void save() {
        try (Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO rangers (name, badge) VALUES (:name, :badge);";

            this.id= (int) connection.createQuery(sql).executeUpdate().getKey();
        }
    }
}
