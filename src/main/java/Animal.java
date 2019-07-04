import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animal implements Wildlife{

    private String name;
    private int age;
    private String health;
    private boolean endangered;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Animal(String name, int age, String health, boolean endangered) {
        this.name = name;
        this.age = age;
        this.health = health;
        this.endangered = endangered;
    }

    public String getSpecies() {
        return name;
    }

    public void setSpecies(String species) {
        this.name = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public boolean isEndangered() {
        return endangered;
    }

    public void setEndangered(boolean endangered) {
        this.endangered = endangered;
    }

    public static Animal find(int id) {
        try (Connection con = DB.sql2o.open()){
           String sql = "SELECT * FROM animals WHERE id = :id";
          return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Animal.class);
        }catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void update(Animal animal) {
        try (Connection con = DB.sql2o.open()){
            String sql = "UPDATE animals SET (name, age, health, endangered) = (:name, :age, :health, :endangered) WHERE id= :id;";
            con.createQuery(sql).addParameter("name", animal.name)
                    .addParameter("age", animal.age)
                    .addParameter("health", animal.health)
                    .addParameter("endangered", animal.endangered)
                    .addParameter("id",this.id)
            .executeUpdate();
        }catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    public static List<Animal> all() {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals;";
           return con.createQuery(sql).executeAndFetch(Animal.class);
        }
//    return null;
    }
    public static void deleteAll() {
        String sql = "DELETE FROM animals *;";
        Connection connection = DB.sql2o.open();
        connection.createQuery(sql).executeUpdate();
    }

    @Override
    public void delete() {
        try (Connection con = DB.sql2o.open()){
           String sql = "DELETE FROM animals * WHERE id = :id";
           con.createQuery(sql).addParameter("id", this.id).executeUpdate();
        }
    }



    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, age, health, endangered) VALUES (:name, :age, :health, :endangered);";
            this.id =(int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .addParameter("endangered", this.endangered)
                    .executeUpdate()
                    .getKey();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getAge() == animal.getAge() &&
                isEndangered() == animal.isEndangered() &&
                getId() == animal.getId() &&
                getSpecies().equals(animal.getSpecies()) &&
                getHealth().equals(animal.getHealth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecies(), getAge(), getHealth(), isEndangered(), getId());
    }

}