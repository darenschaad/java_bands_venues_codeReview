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

  // @Test
  // public void find_findCategoryInDatabase_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Category savedCategory = Category.find(myCategory.getId());
  //   assertTrue(myCategory.equals(savedCategory));
  // }
  //
  // @Test
  // public void addTask_addsTaskToCategory() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myCategory.addTask(myTask);
  //   Task savedTask = myCategory.getTasks().get(0);
  //   assertTrue(myTask.equals(savedTask));
  // }
  //
  // @Test
  // public void getTasks_returnsAllTasks_ArrayList() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myCategory.addTask(myTask);
  //   List savedTasks = myCategory.getTasks();
  //   assertEquals(savedTasks.size(), 1);
  // }
  //
  // @Test
  // public void deleteCategory_deletesAllTasksAndListsAssoicationes() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myCategory.addTask(myTask);
  //   myCategory.deleteCategory();
  //   assertEquals(myTask.getCategories().size(), 0);
  // }
  //
  // @Test
  // public void removeTask_deletesAllRelationsBetweenTaskAndCategory() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myCategory.addTask(myTask);
  //   myCategory.removeTask(myTask.getId());
  //   assertEquals(myCategory.getTasks().size(), 0);
  // }

}
