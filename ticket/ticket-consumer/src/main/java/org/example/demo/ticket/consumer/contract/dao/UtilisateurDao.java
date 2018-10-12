package org.example.demo.ticket.consumer.contract.dao;

import java.util.List;

import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;

public interface UtilisateurDao {

	Utilisateur getUtilisateur(Integer pId);

	List<Utilisateur> getListUtilisateurs(List<Integer> pIds);

}
