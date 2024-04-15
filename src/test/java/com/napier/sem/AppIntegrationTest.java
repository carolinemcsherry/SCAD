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
/*
 @Test
void testGetPopulationForCountry() {
    int population = User_report_1_Country.getPopulationByRegion ("China");
    assertTrue(population > 0);     }
@Test
void testGetPopulationForCity() {
    int population = User_report_1_Region.getPopulationByRegion ("Shanghai");
    assertTrue(population > 0);     }
@Test
void testGetPopulationForContinent() {
    int population = User_report_1_Continent.getPopulationByRegion ("Asia");
    assertTrue(population > 0);
}
*/





