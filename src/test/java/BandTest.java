import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Band firstBand = new Band("House");
    Band secondBand = new Band("House");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Band myBand = new Band("House");
    myBand.save();
    assertTrue(Band.all().get(0).equals(myBand));
  }

  @Test
  public void find_findBandInDatabase_true() {
    Band myBand = new Band("Household chores");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void addVenue_addsVenueToCategory() {
    Band myBand = new Band("House");
    myBand.save();
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    myBand.addVenue(myVenue);
    assertEquals(myVenue, myBand.getVenues().get(0));
  }

  @Test
  public void getVenues_returnsAllVenues_List() {
    Band myBand = new Band("House");
    myBand.save();
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    myBand.addVenue(myVenue);
    List savedVenues = myBand.getVenues();
    assertEquals(savedVenues.size(), 1);
  }

  @Test
  public void deleteBand_deletesAllVenueAssoications() {
    Band myBand = new Band("House");
    myBand.save();
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    myBand.addVenue(myVenue);
    myBand.deleteBand();
    assertEquals(myBand.getVenues().size(), 0);
  }

  // @Test
  // public void removeTask_deletesAllRelationsBetweenTaskAndBand() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myCategory.addTask(myTask);
  //   myCategory.removeTask(myTask.getId());
  //   assertEquals(myCategory.getTasks().size(), 0);
  // }

}
