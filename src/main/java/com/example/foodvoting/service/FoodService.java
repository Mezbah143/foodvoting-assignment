package com.example.foodvoting.service;

import com.example.foodvoting.entity.Food;
import com.example.foodvoting.repository.FoodRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAllByOrderByVoteCountDescNameAsc();
    }

    public Food saveFood(Food food) {
        food.setVoteCount(0);
        return foodRepository.save(food);
    }

    @Transactional
    public void voteForFood(Long id) {
        foodRepository.findById(id).ifPresent(food -> {
            food.addVote();
            foodRepository.save(food);
        });
    }

    public List<Food> getWinnerFoods() {
        List<Food> foods = getAllFoods();
        if (foods.isEmpty() || foods.get(0).getVoteCount() == 0) {
            return List.of();
        }
        return foodRepository.findByVoteCountOrderByNameAsc(foods.get(0).getVoteCount());
    }

    public int getHighestVoteCount() {
        List<Food> winners = getWinnerFoods();
        if (winners.isEmpty()) {
            return 0;
        }
        return winners.get(0).getVoteCount();
    }
}
