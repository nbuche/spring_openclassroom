package org.example.demo.ticket.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.business.contract.manager.TicketManager;

@Named("managerFactory")
public class ManagerFactoryImpl {


    // Ajout d'un attribut projetManager
	@Inject
    private ProjetManager projetManager;
	
	@Inject
    private TicketManager ticketManager;

        // On renvoie désormais simplement l'attribut projetManager
    public ProjetManager getProjetManager() {
        return projetManager;
    }

    // Ajout d'un setter pour l'attribut projetManager
    public void setProjetManager(ProjetManager pProjetManager) {
        projetManager = pProjetManager;
    }

    public TicketManager getTicketManager() {
        return ticketManager;
    }

    public void setTicketManager(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

}
