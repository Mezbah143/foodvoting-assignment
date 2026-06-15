package com.example.foodvoting.config;

import com.example.foodvoting.entity.Food;
import com.example.foodvoting.repository.FoodRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final FoodRepository foodRepository;

    public DemoDataLoader(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void run(String... args) {
        if (foodRepository.count() > 0) {
            return;
        }

        foodRepository.saveAll(List.of(
                demoFood(
                        "Chicken Biryani",
                        "Spiced rice with tender chicken, herbs, and a rich restaurant-style aroma.",
                        "https://images.unsplash.com/photo-1563379091339-03246963d96c?auto=format&fit=crop&w=900&q=80",
                        8),
                demoFood(
                        "Margherita Pizza",
                        "Classic tomato, mozzarella, and basil pizza with a crisp golden crust.",
                        "https://images.unsplash.com/photo-1604382355076-af4b0eb60143?auto=format&fit=crop&w=900&q=80",
                        6),
                demoFood(
                        "Beef Burger",
                        "Juicy burger with melted cheese, fresh lettuce, tomato, and a toasted bun.",
                        "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=900&q=80",
                        5),
                demoFood(
                        "Sushi Platter",
                        "Fresh sushi rolls served with soy sauce, wasabi, and pickled ginger.",
                        "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?auto=format&fit=crop&w=900&q=80",
                        4)
        ));
    }

    private Food demoFood(String name, String description, String imageUrl, int voteCount) {
        Food food = new Food();
        food.setName(name);
        food.setDescription(description);
        food.setImageUrl(imageUrl);
        food.setVoteCount(voteCount);
        return food;
    }
}
