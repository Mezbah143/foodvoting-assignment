package com.example.foodvoting.repository;

import com.example.foodvoting.entity.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByOrderByVoteCountDescNameAsc();

    List<Food> findByVoteCountOrderByNameAsc(int voteCount);
}
