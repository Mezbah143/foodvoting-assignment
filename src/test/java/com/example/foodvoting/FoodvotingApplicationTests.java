package com.example.foodvoting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FoodvotingApplicationTests {

    @Test
    void applicationClassLoads() {
        assertNotNull(FoodvotingApplication.class);
    }
}
