package org.example.demo.ticket.consumer.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.demo.ticket.consumer.contract.DaoFactory;
import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.consumer.contract.dao.UtilisateurDao;

@Named
public class DaoFactoryImpl implements DaoFactory {

	@Inject
	public ProjetDao projetDao;
	
	@Inject
	public TicketDao ticketDao;
	
	@Inject
	public UtilisateurDao utilisateurDao;

	@Override
	public ProjetDao getProjetDao() {
		return projetDao;
	}

	@Override
	public TicketDao getTicketDao() {
		return ticketDao;
	}

	@Override
	public UtilisateurDao getUtilisateurDao() {
		return utilisateurDao;
	}

	
}
