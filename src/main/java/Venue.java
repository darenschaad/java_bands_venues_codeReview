import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String venue_name;
  private int location_id;


  public int getId() {
    return id;
  }

  public String getName() {
    return venue_name;
  }

  public int getLocationId() {
    return location_id;
  }


  public Venue(String venue_name) {
    this.venue_name = venue_name;
  }

  @Override
  public boolean equals(Object otherVenue){
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
             this.getId() == newVenue.getId() &&
             this.getLocationId() == newVenue.getLocationId();
    }
  }


  public static List<Venue> all() {
    String sql = "SELECT * FROM venues ORDER BY venue_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues(venue_name) VALUES (:venue_name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("venue_name", venue_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues where id=:id";
      Venue venue = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }

  public void delete() {
    String sql = "DELETE FROM venues WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    String joinDeleteQuery = "DELETE FROM categories_venues WHERE venue_id = :venueId";
    con.createQuery(joinDeleteQuery)
      .addParameter("venueId", id)
      .executeUpdate();
    }
  }
  public List<Venue> getAllVenues() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues ORDER BY venue_name";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Venue.class);
    }
  }

  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO categories_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("band_id", band.getId())
        .addParameter("venue_id", this.getId())
        .executeUpdate();
    }
  }

  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT Bands.* FROM venues JOIN bands_venues ON venues.id = bands_venues.venue_id JOIN bands on bands_venues.band_id = bands.id WHERE venues.id = :id ORDER BY bands.band_name";
      return con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeAndFetch(Band.class);
    }
  }

  public void removeBand(int bandId) {
    try(Connection con = DB.sql2o.open()){
      String sql ="DELETE FROM bands_venues WHERE band_id = :bandId AND venue_id = :venueId";
      con.createQuery(sql)
        .addParameter("bandId", bandId)
        .addParameter("venueId", this.getId())
        .executeUpdate();
    }
  }
}
