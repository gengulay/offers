package com.gengulay.spring.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gengulay.spring.web.dao.Offer;
import com.gengulay.spring.web.service.OfferService;

@Controller
public class OfferController {

	private OfferService offerservice;

	@Autowired
	public void setOfferservice(OfferService offerservice) {
		this.offerservice = offerservice;
	}

	@RequestMapping("/offers")
	public String showOffers(Model model) {

		// offerservice.throwTestException();
		List<Offer> offers = offerservice.getCurrent();

		model.addAttribute("offers", offers);

		return "offers";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {

		System.out.print("id is :" + id);

		return "home";
	}

	/*
	 * @ExceptionHandler(DataAccessException.class) public String
	 * handleDatabaseException(DataAccessException ex) {
	 * 
	 * return "error";
	 * 
	 * }
	 */

	@RequestMapping(value = "/postoffer")
	public String getOffer(Offer offer, Model model) {

		model.addAttribute("offer", new Offer());

		return "postoffer";
	}

	@RequestMapping(value = "/postoffer", method = RequestMethod.POST)
	public String postOffer(@Valid Offer offer, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "postoffer";
		}

		offerservice.create(offer);

		return "offerposted";
	}
}
