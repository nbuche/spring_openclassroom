package org.example.demo.ticket.consumer.contract.dao;

import java.util.List;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.FunctionalException;
import org.springframework.dao.EmptyResultDataAccessException;

public interface ProjetDao {

	List<Projet> getListProjet();

	Projet getProjet(Integer pId) throws EmptyResultDataAccessException;

	void insertVersion(Version pVersion)  throws FunctionalException;

}
