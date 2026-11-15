
package uilab;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import uilab.pages.AdminPage;
import uilab.pages.HomePage;

class QP01PageObjectTest extends BaseTest {

    // QP-01 with page objects: a custom pizza keeps its restrictions,
    // rating it needs a login, and the admin sees the new pizza.
    @Test
    void customPizzaReachesTheAdminList() {

        String stamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        String pizzaName = "Lab Pizza " + stamp;

        HomePage home = new HomePage(driver, wait);
        AdminPage admin = new AdminPage(driver, wait);

        // Step 1: open home page
        home.open();
        assertEquals("Looking to break out of your pizza routine?",
                home.heading());

        // Step 2: turn on Advanced
        home.showAdvanced();

        // Step 3: fill in restrictions and name
        home.setMaxCalories(600);
        assertEquals("600", home.maxCalories());

        home.excludeTool("Knife");
        home.mustBeVegetarian();
        home.nameThePizza(pizzaName);

        shot(stamp + "/step-3");

        // Step 4: ask for pizza
        home.askForPizza();

        assertEquals(pizzaName, home.result("Name: "));

        String tool = home.result("Tool: ");

        assertNotEquals("Knife", tool);

        int perSlice =
                Integer.parseInt(home.result("Calories per slice: "));

        assertTrue(perSlice <= 600,
                "calories per slice: " + perSlice);

        shot(stamp + "/step-4");

        // Step 5: rate without logging in
        assertEquals("Please log in first.", home.loveIt());

        shot(stamp + "/step-5");

        // Step 6: log in as admin
        admin.open();
        admin.logIn("admin", "admin");

        // Step 7: find pizza in admin list
        String line = admin.lineFor(pizzaName);

        assertTrue(line.contains("(tool=" + tool + ","), line);

        shot(stamp + "/step-7");
    }
}
