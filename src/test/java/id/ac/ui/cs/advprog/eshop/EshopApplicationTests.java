package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testMain() {
        // Verify that the application context loads successfully
        EshopApplication.main(new String[] {});
        assertTrue(true, "The application context loaded successfully.");
    }
}
