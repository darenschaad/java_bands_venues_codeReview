import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Venue firstVenue = new Venue("Rose Garden");
    Venue secondVenue = new Venue("Rose Garden");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesVenueObjectIntoDatabase() {
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertTrue(savedVenue.equals(myVenue));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(myVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_findsVenueInDatabase_true() {
    Venue myVenue = new Venue("Mow the lawn");
    myVenue.save();
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void addBand_addsBandToVenue() {
    Band myBand = new Band("House");
    myBand.save();
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void getBands_returnsAllBands_ArrayList() {
    Band myBand = new Band("Household chores");
    myBand.save();
    Venue myVenue = new Venue("Mow the lawn");
    myVenue.save();
    myVenue.addBand(myBand);
    List savedBands = myVenue.getBands();
    assertEquals(savedBands.size(), 1);
  }

  // @Test
  // public void delete_deletesAllVenuesAndListsAssoicationes() {
  //   Band myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Venue myVenue = new Venue("Mow the lawn");
  //   myVenue.save();
  //   myVenue.addCategory(myCategory);
  //   myVenue.delete();
  //   assertEquals(myCategory.getVenues().size(), 0);
  // }
  //
  // @Test
  // public void removeCategory_deletesAllRelationsBetweenVenueAndCategory() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Venue myVenue = new Venue("Mow the lawn");
  //   myVenue.save();
  //   myVenue.addCategory(myCategory);
  //   myVenue.removeCategory(myCategory.getId());
  //   assertEquals(myVenue.getCategories().size(), 0);
  // }
  //
  // @Test
  // public void completeVenue_marksAVenueComplete() {
  //   Venue myVenue = new Venue("Mow the lawn");
  //   myVenue.save();
  //   myVenue.completeVenue();
  //   Venue savedVenue = Venue.find(myVenue.getId());
  //   assertEquals(savedVenue.getCompletionStatus(), true);
  // }
  //
  // @Test
  // public void deCompleteVenue_marksAVenueIncomplete() {
  //   Venue myVenue = new Venue("Mow the lawn");
  //   myVenue.save();
  //   myVenue.completeVenue();
  //   myVenue.deCompleteVenue();
  //   Venue savedVenue = Venue.find(myVenue.getId());
  //   assertEquals(savedVenue.getCompletionStatus(), false);
  // }
}
