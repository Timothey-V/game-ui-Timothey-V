package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.pokemonTypes.service.TrainerService;
import com.miage.altea.game_ui.pokemonTypes.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {

    @Autowired
    TrainerService trainerService;

    public void setPokemonTypeService(TrainerService p) {
        this.trainerService = p;
    }

    @GetMapping("/trainers")
    public ModelAndView trainer() {
        var modelAndView = new ModelAndView("trainer");
        modelAndView.addObject("trainer", trainerService.getAllTrainersDTO());
        return modelAndView;
    }

    @GetMapping(value="/trainers/{name}")
    public ModelAndView trainerByName(@PathVariable String name) {
        var modelAndView = new ModelAndView("trainerSolo");
        modelAndView.addObject("trainerSolo", trainerService.getTrainerDTO(name));
        return modelAndView;

    }
    @GetMapping(value="/profile")
    public ModelAndView getProfile() {
        var modelAndView = new ModelAndView("profile");
        UserDetails t=  (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("profile", trainerService.getTrainerDTO(t.getUsername()));
        return modelAndView;

    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}