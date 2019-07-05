import org.sql2o.Connection;

import java.util.Objects;

public class Sighting implements Wildlife{
private int id;
private int placeid;
private int animalid;
private int rangerid;

    public Sighting(int placeid, int animalid, int rangerid) {
        this.placeid = placeid;
        this.animalid = animalid;
        this.rangerid = rangerid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceid() {
        return placeid;
    }

    public void setPlaceid(int placeid) {
        this.placeid = placeid;
    }

    public int getAnimalid() {
        return animalid;
    }

    public void setAnimalid(int animalid) {
        this.animalid = animalid;
    }
    public int getRangerid() {
        return rangerid;
    }

    public void setRangerid(int rangerid) {
        this.rangerid = rangerid;
    }

    public static Sighting find(int id) {
        try (Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM sightings WHERE id =:id;";
            return con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Sighting.class);
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void save() {
        try (Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animalid, placeid, rangerid) VALUES(:animalid, :placeid, :rangerid)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalid",this.animalid)
                    .addParameter("placeid", this.placeid)
                    .addParameter("rangerid",this.rangerid)
                    .executeUpdate().getKey();
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return getId() == sighting.getId() &&
                getPlaceid() == sighting.getPlaceid() &&
                getAnimalid() == sighting.getAnimalid() &&
                getRangerid() == sighting.getRangerid();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlaceid(), getAnimalid(), getRangerid());
    }




}
