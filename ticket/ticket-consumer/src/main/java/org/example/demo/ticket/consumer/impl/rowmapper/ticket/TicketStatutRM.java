package org.example.demo.ticket.consumer.impl.rowmapper.ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.springframework.jdbc.core.RowMapper;

public class TicketStatutRM implements RowMapper<TicketStatut> {
	
	public TicketStatut mapRow(ResultSet pRS, int pRowNum) throws SQLException {
	    TicketStatut vTicketStatut = new TicketStatut(pRS.getInt("id"));
	    vTicketStatut.setLibelle(pRS.getString("libelle"));
	    return vTicketStatut;
	}
}