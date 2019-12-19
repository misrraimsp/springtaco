package misrraimsp.sia.springtaco;

import misrraimsp.sia.springtaco.data.IngredientRepository;
import misrraimsp.sia.springtaco.data.OrderRepository;
import misrraimsp.sia.springtaco.data.TacoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private IngredientRepository ingredientRepository;

  @MockBean
  private TacoRepository designRepository;

  @MockBean
  private OrderRepository orderRepository;

  @Test
  public void testHomePage() throws Exception {
    mockMvc.perform(get("/"))
      .andExpect(status().isOk())
      .andExpect(view().name("home"))
      .andExpect(content().string(
          containsString("Welcome to...")));  
  }

}
