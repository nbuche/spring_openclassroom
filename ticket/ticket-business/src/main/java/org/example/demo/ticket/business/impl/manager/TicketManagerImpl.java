package org.example.demo.ticket.business.impl.manager;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.example.demo.ticket.business.contract.manager.TicketManager;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.Bug;
import org.example.demo.ticket.model.bean.ticket.Commentaire;
import org.example.demo.ticket.model.bean.ticket.Evolution;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Named
public class TicketManagerImpl extends AbstractManager implements TicketManager {
    
	/**
	 * Cherche et renvoie le {@link Ticket} numéro {@code pNumero}
	 *
	 * @param pNumero le numéro du Ticket
	 * @return Le {@link Ticket}
	 * @throws NotFoundException Si le Ticket n'est pas trouvé
	 */
	@Override
	public Ticket getTicket(Long pNumero) throws NotFoundException {
		// Je n'ai pas encore codé la DAO
		// Je mets juste un code temporaire pour commencer le cours...
		if (pNumero < 1L) {
			throw new NotFoundException("Ticket non trouvé : numero=" + pNumero);
		}
		Evolution vEvolution = new Evolution(pNumero);
		vEvolution.setPriorite(10);
		return vEvolution;
	}

	/**
	 * Renvoie la liste des {@link Ticket} correspondants aux critères de recherche.
	 *
	 * @param pRechercheTicket -
	 * @return List
	 */
	@Override
	public List<Ticket> getListTicket(RechercheTicket pRechercheTicket) {
		// Je n'ai pas encore codé la DAO
		// Je mets juste un code temporaire pour commencer le cours...
		List<Ticket> vList = new ArrayList<>();
		if (pRechercheTicket.getProjetId() != null) {
			Projet vProjet = new Projet(pRechercheTicket.getProjetId());
			for (int vI = 0; vI < 4; vI++) {
				Ticket vTicket = new Bug((long) pRechercheTicket.getProjetId() * 10 + vI);
				vTicket.setProjet(vProjet);
				vList.add(vTicket);
			}
		} else {
			for (int vI = 0; vI < 9; vI++) {
				Ticket vTicket = new Evolution((long) vI);
				vList.add(vTicket);
			}
		}
		return vList;
	}

	/**
	 * Renvoie le nombre de {@link Ticket} correspondants aux critères de recherche.
	 *
	 * @param pRechercheTicket -
	 * @return int
	 */
	@Override
	public int getCountTicket(RechercheTicket pRechercheTicket) {
		// Je n'ai pas encore codé la DAO
		// Je mets juste un code temporaire pour commencer le cours...
		return 42;
	}

	
	@Override
	public void changerStatut(Ticket pTicket, TicketStatut pNewStatut, Utilisateur pUtilisateur,
			Commentaire pCommentaire) {
		TransactionStatus vTransactionStatus = getPlatformTransactionManager()
				.getTransaction(new DefaultTransactionDefinition());
		try {
			pTicket.setStatut(pNewStatut);
			getDaoFactory().getTicketDao().updateTicket(pTicket);
			// TODO : Ajout de la ligne d'historique + commentaire ...

			getPlatformTransactionManager().commit(vTransactionStatus);
			vTransactionStatus = null;
		} finally {
			if (vTransactionStatus != null) {
				getPlatformTransactionManager().rollback(vTransactionStatus);
			}
		}
	}
	
	@Override
	public List<TicketStatut> getListTicketStatut() {
		return getDaoFactory().getTicketDao().getListTicketStatut();
	}
	
	@Override
	public void exportTicketStatut(String exportUrl) throws IOException{
		
	
				
		// Chargement des statuts
		List<TicketStatut> vListTicketStatut = getListTicketStatut();
		
		// Création du fichier
		Path vPathFile = Paths.get(exportUrl+"\\exportTicketStatut.txt");
		
		// Creation du répertoire
		try {
		    Path newDir = Files.createDirectory(Paths.get(exportUrl));
		} catch(FileAlreadyExistsException e){
		    // the directory already exists.
			// On ne fait rien
		}
		
		// Si le fichier existe, on le supprime
		if(Files.exists(vPathFile, new LinkOption[]{ LinkOption.NOFOLLOW_LINKS})) {
			Files.delete(vPathFile);	
		}
		
		List<String> vLines = vListTicketStatut.stream().map(TicketStatut::toString).collect(Collectors.toList());
		
		// Ecriture dans fichier
		Files.write(vPathFile, vLines);
		
	}
}
