package org.example.demo.ticket.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.example.demo.ticket.consumer.contract.dao.UtilisateurDao;
import org.example.demo.ticket.consumer.impl.rowmapper.utilisateur.UtilisateurRM;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named
public class UtilisateurDaoImpl extends AbstractDaoImpl implements UtilisateurDao{

	@Override
	public Utilisateur getUtilisateur(Integer pId) {
		MapSqlParameterSource vParams = new MapSqlParameterSource();

		StringBuilder vSQL = new StringBuilder("SELECT * FROM public.utilisateur WHERE 1=1");

		if (pId != null) {
			vSQL.append(" AND id = :id");
			vParams.addValue("id", pId);
		}

		RowMapper<Utilisateur> vRowMapper = new UtilisateurRM();

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		return vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
	}
	
	@Override
	public List<Utilisateur> getListUtilisateurs(List<Integer> pIds) {
		MapSqlParameterSource vParams = new MapSqlParameterSource();

		StringBuilder vSQL = new StringBuilder("SELECT * FROM public.utilisateur WHERE 1=1");

		if (CollectionUtils.isNotEmpty(pIds)) {
			vSQL.append(" AND id IN (:ids)");
			vParams.addValue("ids", pIds);
		}

		RowMapper<Utilisateur> vRowMapper = new UtilisateurRM();

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		return vJdbcTemplate.query(vSQL.toString(), vParams, vRowMapper);
	}
}
