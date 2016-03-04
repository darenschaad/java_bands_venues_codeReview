import java.util.HashMap;
import java.util.Date;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";


    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("categories", Category.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tasks", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      model.put("tasks", Task.all());
      model.put("template", "templates/tasks.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tasks/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Task task = Task.find(id);

      model.put("task", task);
      model.put("categories", task.getCategories());
      model.put("allcategories", Category.all());
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks/:id/categoryadd", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params(":id"));
      Task task = Task.find(id);
      int category_id = Integer.parseInt(request.queryParams("categoryassign"));
      Category assignedCategory = Category.find(category_id);
      task.addCategory(assignedCategory);
      model.put("task", task);
      model.put("categories", task.getCategories());
      model.put("allcategories", Category.all());
      model.put("template", "templates/task.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks/:taskId/remove/:categoryId", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.params(":taskId"));
      Category category = Category.find(Integer.parseInt(request.params(":categoryId")));
      category.removeTask(taskId);
      response.redirect("/tasks/" + taskId);
      return null;
    });

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String categoryName = request.queryParams("categoryName");
      Category newCategory = new Category(categoryName);
      newCategory.save();
      List<Category> categoryList = newCategory.all();
      model.put("categories", categoryList);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Category category = Category.find(Integer.parseInt(request.queryParams("categoryId")));
      String taskName = request.queryParams("taskName");
      String dueDate = request.queryParams("dueDate");
      Task newTask = new Task(taskName);
      newTask.save();
      if (dueDate != "") {
        newTask.addDue(dueDate);
      }
      category.addTask(newTask);
      List<Task> tasks = category.getTasks();
      model.put("category", category);
      model.put("tasks", tasks);
      model.put("template", "templates/addTask.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Category category = Category.find(Integer.parseInt(request.params(":id")));
      List<Task> tasks = category.getTasks();
      model.put("category", category);
      model.put("tasks", tasks);
      model.put("template", "templates/addTask.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:categoryId/remove/:taskId", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int categoryId = Integer.parseInt(request.params(":categoryId"));
      Task task = Task.find(Integer.parseInt(request.params(":taskId")));
      task.removeCategory(categoryId);
      response.redirect("/" + categoryId);
      return null;
    });

    get("/tasks/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.params(":id"));
      Task task = Task.find(taskId);
      model.put("task", task);
      model.put("template", "templates/delete.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tasks/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.params(":id"));
      Task task = Task.find(taskId);
      task.delete();
      response.redirect("/");
      return null;
    });

    get("/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int categoryId = Integer.parseInt(request.params(":id"));
      Category category = Category.find(categoryId);
      model.put("category", category);
      model.put("template", "templates/delete.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int categoryId = Integer.parseInt(request.params(":id"));
      Category category = Category.find(categoryId);
      category.deleteCategory();
      response.redirect("/");
      return null;
    });

    post("/tasks/:id/complete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.params(":id"));
      Task task = Task.find(taskId);
      task.completeTask();
      response.redirect("/tasks");
      return null;
    });

    post("/tasks/:id/incomplete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int taskId = Integer.parseInt(request.params(":id"));
      Task task = Task.find(taskId);
      task.deCompleteTask();
      response.redirect("/tasks");
      return null;
    });

  }

}
