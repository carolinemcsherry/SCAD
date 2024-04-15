package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.swing.plaf.synth.Region;
import java.util.ArrayList;
import java.util.jar.Attributes;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);

    }
}
/* @Test
void testGetPopulationForCountry() {
    int population = app.getPopulationForCountry ("China");
    assertTrue(population > 0);     }
@Test
void testGetPopulationForCity() {
    int population = app.getPopulationForCity ("Shanghai");
    assertTrue(population > 0);     }
@Test
void testGetPopulationForContinent() {
    int population = app.getPopulationForContinent ("Asia");
    assertTrue(population > 0);
}
}
 */
/*
@Test
void testGetPopulationForCountry() {
    SELECT COUNT (name)
            From country then as if COUNT (name) > 1

}*/
