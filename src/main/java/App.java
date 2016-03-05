import java.util.HashMap;
import java.util.Date;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.lang.*;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";


    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("template", "templates/bands.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/bands", (request, response) -> {
      String bandName = request.queryParams("bandName");
      if (bandName.trim().length() > 0) {
        Band band = new Band(bandName);
        band.save();
      }
      response.redirect("/bands");
      return null;
    });

    get("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("venues", Venue.all());
      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venues", (request, response) -> {
      String venueName = request.queryParams("venueName");
      if (venueName.trim().length() > 0){
        Venue venue = new Venue(venueName);
        venue.save();
      }
      response.redirect("/venues");
      return null;
    });

    get("/band/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      model.put("band", band);
      model.put("allVenues", Venue.all());
      model.put("venues", band.getVenues());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/band/:id", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      int venueId = Integer.parseInt(request.queryParams("venueId"));
      Venue venue = Venue.find(venueId);
      band.addVenue(venue);
      response.redirect("/band/" + bandId);
      return null;
    });

    post("/band-update/:id", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      String newName = request.queryParams("newName");
      band.update(newName);
      response.redirect("/band/" + bandId);
      return null;
    });

    get("/venue/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int venueId = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(venueId);
      model.put("venue", venue);
      model.put("allBands", Band.all());
      model.put("bands", venue.getBands());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/venue/:id", (request, response) -> {
      int venueId = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(venueId);
      int bandId = Integer.parseInt(request.queryParams("bandId"));
      Band band = Band.find(bandId);
      venue.addBand(band);
      response.redirect("/venue/" + venueId);
      return null;
    });

    post("/venue-update/:id", (request, response) -> {
      int venueId = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(venueId);
      String newName = request.queryParams("newName");
      venue.update(newName);
      response.redirect("/venue/" + venueId);
      return null;
    });

    get("/band/:id/delete", (request, response) -> {
      int bandId = Integer.parseInt(request.params("id"));
      Band band = Band.find(bandId);
      band.deleteBand();
      response.redirect("/");
      return null;
    });

  }
}
