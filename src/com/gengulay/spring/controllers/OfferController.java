package com.gengulay.spring.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gengulay.spring.web.dao.FormValidationGroup;
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
	public String getOffer(Model model, Principal principal) {

		Offer offer = null;

		if (principal != null) {
			String username = principal.getName();

			offer = offerservice.getOfferByUsername(username);
		}

		if (offer == null)
			offer = new Offer();

		model.addAttribute("offer", offer);

		return "postoffer";
	}

	@RequestMapping(value = "/postoffer", method = RequestMethod.POST)
	public String postOffer(@Validated(value = FormValidationGroup.class) Offer offer, BindingResult result,
			Model model, Principal principal, @RequestParam(value = "delete", required = false) String delete) {

		if (result.hasErrors()) {
			return "postoffer";
		}

		if (delete == null) {
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offerservice.createOrUpdateOffer(offer);
			return "offerposted";
		} else {
			offerservice.deleteById(offer.getId());
			return "offerdeleted";

		}

	}
}
