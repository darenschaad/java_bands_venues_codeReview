import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();


  // @Test
  // public void all_emptyAtFirst() {
  //   assertEquals(Task.all().size(), 0);
  // }
  //
  // @Test
  // public void equals_returnsTrueIfDescriptionsAretheSame() {
  //   Task firstTask = new Task("Mow the lawn");
  //   Task secondTask = new Task("Mow the lawn");
  //   assertTrue(firstTask.equals(secondTask));
  // }
  //
  // @Test
  // public void save_savesObjectIntoDatabase() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertTrue(savedTask.equals(myTask));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertEquals(myTask.getId(), savedTask.getId());
  // }
  //
  // @Test
  // public void find_findsTaskInDatabase_true() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertTrue(myTask.equals(savedTask));
  // }
  //
  // @Test
  // public void addCategory_addsCategoryToTask() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.addCategory(myCategory);
  //   Category savedCategory = myTask.getCategories().get(0);
  //   assertTrue(myCategory.equals(savedCategory));
  // }
  //
  // @Test
  // public void getCategories_returnsAllCategories_ArrayList() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.addCategory(myCategory);
  //   List savedCategories = myTask.getCategories();
  //   assertEquals(savedCategories.size(), 1);
  // }
  //
  // @Test
  // public void delete_deletesAllTasksAndListsAssoicationes() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.addCategory(myCategory);
  //   myTask.delete();
  //   assertEquals(myCategory.getTasks().size(), 0);
  // }
  //
  // @Test
  // public void removeCategory_deletesAllRelationsBetweenTaskAndCategory() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.addCategory(myCategory);
  //   myTask.removeCategory(myCategory.getId());
  //   assertEquals(myTask.getCategories().size(), 0);
  // }
  //
  // @Test
  // public void completeTask_marksATaskComplete() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.completeTask();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertEquals(savedTask.getCompletionStatus(), true);
  // }
  //
  // @Test
  // public void deCompleteTask_marksATaskIncomplete() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.completeTask();
  //   myTask.deCompleteTask();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertEquals(savedTask.getCompletionStatus(), false);
  // }
}
