package com.pricebasket.bjss;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;

/**
 * Integration Test for the Price Basket Application
 * Run this test to spin the actual spring boot application
 * You can see the files are loaded, discount rules are applied, prices are calculated
 * Output Response
 * SubTotal : 4.40
 * Apples - 10% off: 0.10 Bread - 50% off: 0.40
 * Total : 3.90
 *
 * You can also change the input and test the response
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    Application app;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private String responseForactualTestScenario = "Apples - 10% off: 0.10 Bread - 50% off: 0.40";
    private String responseForFiveApplesInCart = "Apples - 10% off: 0.50";
    private String responseForNoDiscountsApplicable = "(No offers available)";

    @Test
    public void integrationTestToRunSpringBootAppWithActualTestData() throws Exception {
        app.run( "Apples", "Bread", "Soup", "Milk", "Soup");
        Assert.assertThat(systemOutRule.getLog(), containsString(responseForactualTestScenario));
    }

    @Test
    public void integrationTestWithFiveApplesInCart() throws Exception {
        app.run( "Apples", "Apples", "Apples", "Apples", "Apples");
        Assert.assertThat(systemOutRule.getLog(), containsString(responseForFiveApplesInCart));
    }

    @Test
    public void integrationTestWithNoDiscountsApplicable() throws Exception {
        app.run( "Milk", "Bread");
        Assert.assertThat(systemOutRule.getLog(), containsString(responseForNoDiscountsApplicable));
    }
}
