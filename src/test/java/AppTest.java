import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Bands and Venues");
  }


  @Test
  public void bandIsDisplayedTest() {
    Band myBand = new Band("Tanlines");
    myBand.save();
    goTo("http://localhost:4567");
    assertThat(pageSource()).contains("Tanlines");
  }

  @Test
  public void allVenuesDisplayOnBandPage() {
    Band myBand = new Band("Audioslave");
    myBand.save();
    Venue firstVenue = new Venue("Rose Garden");
    firstVenue.save();
    myBand.addVenue(firstVenue);
    Venue secondVenue = new Venue("The Meadows");
    secondVenue.save();
    myBand.addVenue(secondVenue);
    String categoryPath = String.format("http://localhost:4567/band/%d", myBand.getId());
    goTo(categoryPath);
    assertThat(pageSource()).contains("Rose Garden");
    assertThat(pageSource()).contains("The Meadows");
  }

  // @Test
  // public void bandIsAdded() {
  //   Band myCategory = new Category("Household chores");
  //   myCategory.save();
  //   int id = myCategory.getId();
  //   myCategory.deleteCategory();
  //   goTo("http://localhost:4567");
  //   assertThat(pageSource()).doesNotContain("Household chores");
  // }

  // @Test
  // public void taskIsDeleted() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   int categoryId = myCategory.getId();
  //   Venue myTask = new Task("sweep");
  //   myTask.save();
  //   int id = myTask.getId();
  //   myTask.delete();
  //   String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
  //   goTo(categoryPath);
  //   assertThat(pageSource()).doesNotContain("sweep");
  // }
  //
  // @Test public void categoryIsRemoved() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myCategory.addTask(myTask);
  //   String taskPath = String.format("http://localhost:4567/tasks/%d", myTask.getId());
  //   goTo(taskPath);
  //   String buttonId = String.format("%d", myCategory.getId());
  //   submit(".remove", withId(buttonId));
  //   assertThat(pageSource()).doesNotContain("<h4>Household chores</h4>");
  // }
  //
  // @Test public void taskIsRemoved() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.addCategory(myCategory);
  //   String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
  //   goTo(categoryPath);
  //   String buttonId = String.format("remove%d", myTask.getId());
  //   submit(".remove", withId(buttonId));
  //   assertThat(pageSource()).doesNotContain("Mow the lawn");
  // }
  //
  // @Test public void deleteTask() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   myTask.addCategory(myCategory);
  //   String categoryPath = String.format("http://localhost:4567/%d", myCategory.getId());
  //   goTo(categoryPath);
  //   String buttonId = String.format("delete%d", myTask.getId());
  //   click("a", withId(buttonId));
  //   submit(".btn", withId(buttonId));
  //   goTo(categoryPath);
  //   assertThat(pageSource()).doesNotContain("Mow the lawn");
  // }
}
