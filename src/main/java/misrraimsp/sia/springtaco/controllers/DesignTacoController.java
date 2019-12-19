package misrraimsp.sia.springtaco.controllers;

import misrraimsp.sia.springtaco.data.IngredientRepository;
import misrraimsp.sia.springtaco.data.TacoRepository;
import misrraimsp.sia.springtaco.domain.Ingredient;
import misrraimsp.sia.springtaco.domain.Ingredient.Type;
import misrraimsp.sia.springtaco.domain.Order;
import misrraimsp.sia.springtaco.domain.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;
  private TacoRepository designRepo;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
    this.ingredientRepo = ingredientRepo;
    this.designRepo = designRepo;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }
  
  @GetMapping
  public String showDesignForm(Model model) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
    if (errors.hasErrors()) {
      return "design";
    }
    Taco saved = designRepo.save(design);
    order.addDesign(saved);
    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList())
            ;
  }

}