package org.example.demo.ticket.business.impl.manager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.FunctionalException;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Named
public class ProjetManagerImpl extends AbstractManager implements ProjetManager {

	/**
	 * Renvoie le projet demandé
	 *
	 * @param pId l'identifiant du projet
	 * @return Le {@link Projet}
	 * @throws NotFoundException Si le projet n'est pas trouvé
	 */
	@Override
	public Projet getProjet(Integer pId) throws NotFoundException {

		if (pId < 1) {
			throw new NotFoundException("Projet non trouvé : ID=" + pId);
		}
		Projet vProjet = null;
		try {
			vProjet = getDaoFactory().getProjetDao().getProjet(pId);

			// Recherche du responsable projet
			Integer vIdResponsable = vProjet.getResponsable().getId();
			Utilisateur vResponsable = getDaoFactory().getUtilisateurDao().getUtilisateur(vIdResponsable);
			vProjet.setResponsable(vResponsable);
		} catch (EmptyResultDataAccessException e) {
			throw new NotFoundException("Projet non trouvé : ID=" + pId);
		}
		return vProjet;
	}

	/**
	 * Renvoie la liste des {@link Projet}
	 *
	 * @return List
	 */
	@Override
	public List<Projet> getListProjet() {

		List<Projet> vListProjets = getDaoFactory().getProjetDao().getListProjet();

		// List Projet --> List idRespnsables
		List<Utilisateur> vResponsables = vListProjets.stream().map(Projet::getResponsable)
				.collect(Collectors.toList());
		List<Integer> vIdsResponsables = vResponsables.stream().map(Utilisateur::getId).collect(Collectors.toList());

		// Recherche responsables
		vResponsables = getDaoFactory().getUtilisateurDao().getListUtilisateurs(vIdsResponsables);

		// List Utilisateur -- > Map<id,Utilisateur>
		Map<Integer, Utilisateur> vMapResponsables = vResponsables.stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> x));

		// Affectation utilisateur à chaque projet
		for (Projet projet : vListProjets) {
			int vIdResponsable = projet.getResponsable().getId();
			Utilisateur vResponsable = vMapResponsables.get(vIdResponsable);
			projet.setResponsable(vResponsable);
		}

		return vListProjets;
	}
	
	@Override
	public void insertVersion(Version pVersion) throws FunctionalException{
		
		if(pVersion == null || StringUtils.isEmpty(pVersion.getNumero()) || pVersion.getProjet() == null || pVersion.getProjet().getId() ==null) {
			throw new FunctionalException("Element à ajouter vides");
		}
		TransactionStatus vTransactionStatus = getPlatformTransactionManager()
				.getTransaction(new DefaultTransactionDefinition());
		try {
			
			getDaoFactory().getProjetDao().insertVersion(pVersion);
			
			getPlatformTransactionManager().commit(vTransactionStatus);
			vTransactionStatus = null;
		} finally {
			if (vTransactionStatus != null) {
				getPlatformTransactionManager().rollback(vTransactionStatus);
			}
		}
	}
}
