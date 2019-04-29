package com.gengulay.spring.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("offersDao")
public class OffersDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getAllOffers() {

		Criteria crit = session().createCriteria(Offer.class);

		crit.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return crit.list();

	}

	@SuppressWarnings("unchecked")
	public List<Offer> getAllOffersByUsername(String username) {
		Criteria crit = session().createCriteria(Offer.class);

		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.eq("u.username", username));
		return crit.list();

	}

	public Offer getOfferById(int id) {

		Criteria crit = session().createCriteria(Offer.class);

		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));
		return (Offer) crit.uniqueResult();

	}

	public boolean deleteOfferById(int id) {

		Query query = session().createQuery("delete from Offer where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}

	public void createOrUpdateOffer(Offer offer) {
		session().saveOrUpdate(offer);
	}

}
