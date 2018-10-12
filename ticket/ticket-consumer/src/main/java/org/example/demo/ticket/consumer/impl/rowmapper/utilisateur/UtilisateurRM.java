package org.example.demo.ticket.consumer.impl.rowmapper.utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

public class UtilisateurRM implements RowMapper<Utilisateur> {
	
	public Utilisateur mapRow(ResultSet pRS, int pRowNum) throws SQLException {
		Utilisateur vUtilisateur = new Utilisateur(pRS.getInt("id"));
		vUtilisateur.setNom(pRS.getString("nom"));
		vUtilisateur.setPrenom(pRS.getString("prenom"));
	    return vUtilisateur;
	}

}
