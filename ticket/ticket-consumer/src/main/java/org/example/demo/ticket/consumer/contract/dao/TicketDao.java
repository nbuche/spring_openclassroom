package org.example.demo.ticket.consumer.contract.dao;

import java.util.List;

import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

public interface TicketDao {

	int getCountTicket(RechercheTicket pRechercheTicket);

	List<TicketStatut> getListStatut();

	void updateTicketStatus(TicketStatut pTicketStatut);

	void insertTicketStatut(TicketStatut pTicketStatut);
	
	void updateTicket(Ticket pTicket);

	List<TicketStatut> getListTicketStatut();

}
