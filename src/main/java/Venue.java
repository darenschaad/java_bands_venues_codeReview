import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String venue_name;
  private String location;


  public int getId() {
    return id;
  }

  public String getName() {
    return description;
  }

  public boolean getLocation() {
    return complete;
  }


  public Venue(String venue_name) {
    this.venue_name = venue_name;
    this.location = location;
  }

  @Override
  public boolean equals(Object otherVenue){
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
             this.getId() == newVenue.getId() &&
             this.getLocation().equals(newVenue.getLocation());
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
      String sql = "INSERT INTO venues(description, complete) VALUES (:description, :complete)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", description)
        .addParameter("complete", complete)
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

  public void update(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE venues SET description = :description WHERE id = :id";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
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
      String sql = "SELECT * FROM venues ORDER BY description";
      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetch(Venue.class);
    }
  }

  public void addCategory(Category category) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO categories_venues (category_id, venue_id) VALUES (:category_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("category_id", category.getId())
        .addParameter("venue_id", this.getId())
        .executeUpdate();
    }
  }

  public ArrayList<Category> getCategories() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT category_id FROM categories_venues WHERE venue_id = :venue_id";
      List<Integer> categoryIds = con.createQuery(sql)
        .addParameter("venue_id", this.getId())
        .executeAndFetch(Integer.class);

      ArrayList<Category> categories = new ArrayList<Category>();

      for (Integer categoryId : categoryIds) {
          String venueQuery = "Select * From categories WHERE id = :categoryId ORDER BY name";
          Category category = con.createQuery(venueQuery)
            .addParameter("categoryId", categoryId)
            .executeAndFetchFirst(Category.class);
          categories.add(category);
      }
      return categories;
    }
  }

  public void removeCategory(int categoryId) {
    try(Connection con = DB.sql2o.open()){
      String sql ="DELETE FROM categories_venues WHERE category_id =  :categoryId AND venue_id = :venueId";      con.createQuery(sql)
        .addParameter("categoryId", categoryId)
        .addParameter("venueId", this.getId())
        .executeUpdate();
    }
  }

  public void deCompleteVenue() {
  try(Connection con = DB.sql2o.open()){
    String sql = "UPDATE venues SET complete = false WHERE id = :id";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void completeVenue() {
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE venues SET complete = true WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void addDue(String dueDate) {
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE venues SET due = :dueDate WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .addParameter("dueDate", dueDate)
        .executeUpdate();
    }
  }
}
