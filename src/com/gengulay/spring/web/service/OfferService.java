package com.gengulay.spring.web.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.gengulay.spring.web.dao.Offer;
import com.gengulay.spring.web.dao.OffersDao;

@Service("offersService")
public class OfferService {

	private OffersDao offersDao;

	@Autowired
	public void setOffersDao(OffersDao offersDao) {
		this.offersDao = offersDao;
	}

	public List<Offer> getCurrent() {
		return offersDao.getAllOffers();

	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public void create(@Valid Offer offer) {
		offersDao.createOffer(offer);

	}

}
