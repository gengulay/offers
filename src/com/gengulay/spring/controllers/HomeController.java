package com.gengulay.spring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gengulay.spring.web.dao.Offer;
import com.gengulay.spring.web.service.OfferService;

@Controller
public class HomeController {

	@Autowired
	private OfferService offerService;

	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {

		List<Offer> offers = offerService.getCurrent();

		model.addAttribute("offers", offers);

		boolean hasOffer = false;

		if (principal != null) {
			hasOffer = offerService.hasOffer(principal.getName());
		}

		model.addAttribute("hasOffer", hasOffer);

		return "home";
	}

}
