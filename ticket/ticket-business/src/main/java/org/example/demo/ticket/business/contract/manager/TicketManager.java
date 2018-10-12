package org.example.demo.ticket.business.contract.manager;

import org.example.demo.ticket.model.bean.ticket.Commentaire;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

import java.io.IOException;
import java.util.List;

public interface TicketManager {
    Ticket getTicket(Long pNumero) throws NotFoundException;

    List<Ticket> getListTicket(RechercheTicket pRechercheTicket);

    int getCountTicket(RechercheTicket pRechercheTicket);

	void changerStatut(Ticket pTicket, TicketStatut pNewStatut, Utilisateur pUtilisateur, Commentaire pCommentaire);

	void exportTicketStatut(String exportUrl) throws IOException;
	
	List<TicketStatut> getListTicketStatut();
}
