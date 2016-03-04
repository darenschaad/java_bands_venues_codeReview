import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Band {
  private int id;
  private String band_name;
  private int members;

  public int getId() {
    return id;
  }

  public String getName() {
    return band_name;
  }

  public int getMembers() {
    return members;
  }

  public Band(String band_name) {
    this.band_name = band_name;
  }

  public static List<Band> all() {
    String sql = "SELECT * FROM bands ORDER BY band_name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand){
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return  this.getId() == newBand.getId() &&
              this.getName().equals(newBand.getName());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands(band_name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.band_name)
        .executeUpdate()
        .getKey();
    }
  }

  // public void deleteBand() {
  //   String sql = "DELETE FROM bands WHERE id=:id";
  //   try(Connection con = DB.sql2o.open()) {
  //     con.createQuery(sql)
  //     .addParameter("id", id)
  //     .executeUpdate();
  //     String joinDeleteQuery = "DELETE FROM bands_venues WHERE band_id = :bandId";
  //     con.createQuery(joinDeleteQuery)
  //       .addParameter("bandId", id)
  //       .executeUpdate();
  //   }
  // }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands where id=:id";
      Band band = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  public void addVenue(Venue venue) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("band_id", this.getId())
        .addParameter("venue_id", venue.getId())
        .executeUpdate();
    }
  }

  public List<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT venues.* FROM bands JOIN bands_venues ON bands.id = bands_venues.band_id JOIN venues on bands_venues.venue_id = venues.id WHERE bands.id = :id ORDER BY venues.venue_name";
      return con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeAndFetch(Venue.class);
    }
  }

  // public void removeVenue(int taskId) {
  //   try(Connection con = DB.sql2o.open()){
  //     String sql ="DELETE FROM bands_venues WHERE band_id =  :bandId AND venue_id = :venueId";      con.createQuery(sql)
  //       .addParameter("bandId", this.getId())
  //       .addParameter("venueId", venueId)
  //       .executeUpdate();
  //   }
  // }
}
