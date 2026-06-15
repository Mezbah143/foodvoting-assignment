package com.example.foodvoting.controller;

import com.example.foodvoting.entity.Food;
import com.example.foodvoting.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("foods", foodService.getAllFoods());
        model.addAttribute("winners", foodService.getWinnerFoods());
        model.addAttribute("highestVoteCount", foodService.getHighestVoteCount());
        return "index";
    }

    @GetMapping("/foods/new")
    public String newFoodForm(Model model) {
        model.addAttribute("food", new Food());
        return "add-food";
    }

    @PostMapping("/foods")
    public String createFood(
            @Valid @ModelAttribute("food") Food food,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-food";
        }
        foodService.saveFood(food);
        redirectAttributes.addFlashAttribute("message", "Food added successfully.");
        return "redirect:/";
    }

    @PostMapping("/foods/{id}/vote")
    public String vote(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        foodService.voteForFood(id);
        redirectAttributes.addFlashAttribute("message", "Your vote was counted.");
        return "redirect:/";
    }

    @GetMapping("/winner")
    public String winner(Model model) {
        model.addAttribute("winners", foodService.getWinnerFoods());
        model.addAttribute("highestVoteCount", foodService.getHighestVoteCount());
        return "winner";
    }
}
