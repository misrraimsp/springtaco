package misrraimsp.sia.springtaco.controllers;

import lombok.extern.slf4j.Slf4j;
import misrraimsp.sia.springtaco.data.IngredientRepository;
import misrraimsp.sia.springtaco.data.TacoRepository;
import misrraimsp.sia.springtaco.data.UserRepository;
import misrraimsp.sia.springtaco.domain.Ingredient;
import misrraimsp.sia.springtaco.domain.Ingredient.Type;
import misrraimsp.sia.springtaco.domain.Order;
import misrraimsp.sia.springtaco.domain.Taco;
import misrraimsp.sia.springtaco.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Slf4j
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;
  private TacoRepository designRepo;
  private UserRepository userRepository;

  @Autowired
  public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo,
                              UserRepository userRepository) {
    this.ingredientRepo = ingredientRepo;
    this.designRepo = designRepo;
    this.userRepository = userRepository;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }
  
  @ModelAttribute(name = "design")
  public Taco design() {
    return new Taco();
  }
  
  @GetMapping
  public String showDesignForm(Model model, Principal principal) {

    log.info("   --- Designing taco");

    List<Ingredient> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));
    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    String username = principal.getName();
    User user = userRepository.findByUsername(username);
    model.addAttribute("user", user);

    return "design";
  }

  @PostMapping
  public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {

    log.info("   --- Saving taco");

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