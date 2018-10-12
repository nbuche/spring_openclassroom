package org.example.demo.ticket.consumer.impl.rowmapper.projet;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

public class ProjetRM implements RowMapper<Projet> {
	
	public Projet mapRow(ResultSet pRS, int pRowNum) throws SQLException {
	    Projet vProjet = new Projet(pRS.getInt("id"));
	    vProjet.setNom(pRS.getString("nom"));
	    vProjet.setDateCreation(pRS.getDate("date_creation"));
	    vProjet.setCloture(pRS.getBoolean("cloture"));
	    vProjet.setResponsable(new Utilisateur(pRS.getInt("responsable_id")));
	    return vProjet;
	}
}