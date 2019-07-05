import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);


        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int placeid = Integer.parseInt(request.queryParams("location"));
            int rangerid = Integer.parseInt(request.queryParams("ranger"));
            int animalid = Integer.parseInt(request.queryParams("animal"));
            Sighting sighting = new Sighting(placeid,animalid,rangerid);
            sighting.save();
            return new ModelAndView(model,"sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("sightings",Sighting.all());
            return  new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/add-info", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "links.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings/new", (request, response) -> {
            Map<String, Object> model= new HashMap<String, Object>();
            model.put("rangers",Ranger.all());
            model.put("places", Place.all());
            model.put("animals", Animal.all());
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/rangers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/rangers", (request, response) -> {
            Map<String,Object> model= new HashMap<String,Object>();
            String name = request.queryParams("name");
            String badge = request.queryParams("badge");
            Ranger ranger = new Ranger(name, badge);
            ranger.save();
            response.redirect("/rangers");
            return null;
        }, new HandlebarsTemplateEngine());
        get("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("rangers", Ranger.all());
            return new ModelAndView(model, "rangers.hbs");
        }, new HandlebarsTemplateEngine());

        get("/ranger/:id", (request, response) -> {
           Map<String, Object> model = new HashMap<String, Object>();
           Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
           model.put("ranger", ranger);
           model.put("sightings", ranger.getSightings());
           return new ModelAndView(model, "ranger.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return  new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animals", (request, response) -> {
            String name = request.queryParams("name");
            System.out.println(name);
            String health = request.queryParams("health");
            int age = Integer.parseInt(request.queryParams("age"));
            boolean endangered = Boolean.parseBoolean(request.queryParams("endangered"));
            Animal animal = new Animal(name,age,health,endangered);
            animal.save();
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/animal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
            model.put("animal", animal);
            return new ModelAndView(model, "animal.hbs");
        }, new HandlebarsTemplateEngine());

        get("/locations/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "location-form.hbs");
        }, new HandlebarsTemplateEngine());
        get("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("locations", Place.all());
            return new ModelAndView(model, "locations.hbs");
        }, new HandlebarsTemplateEngine());
        post("/locations", (request, response) -> {
            String name = request.queryParams("name");
            Place place = new Place(name);
            place.save();
            response.redirect("/locations");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/location/:id",  (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Place place = Place.find(Integer.parseInt(request.params(":id")));
            model.put("location", place);
            model.put("sightings", place.getSightings());
            return new ModelAndView(model, "location.hbs");
        }, new HandlebarsTemplateEngine());




    }

}
