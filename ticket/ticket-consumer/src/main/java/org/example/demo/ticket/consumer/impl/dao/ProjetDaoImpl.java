package org.example.demo.ticket.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.impl.rowmapper.projet.ProjetRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.FunctionalException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named
public class ProjetDaoImpl extends AbstractDaoImpl implements ProjetDao {

	/** Logger pour la classe */
	private static final Log LOGGER = LogFactory.getLog(ProjetDaoImpl.class);

	@Override
	public Projet getProjet(Integer pId) throws EmptyResultDataAccessException {
		MapSqlParameterSource vParams = new MapSqlParameterSource();

		StringBuilder vSQL = new StringBuilder("SELECT * FROM projet WHERE 1=1");

		if (pId != null) {
			vSQL.append(" AND id = :id");
			vParams.addValue("id", pId);
		}

		RowMapper<Projet> vRowMapper = new ProjetRM();

		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		return vJdbcTemplate.queryForObject(vSQL.toString(), vParams, vRowMapper);
	}

	@Override
	public List<Projet> getListProjet() {
		String vSQL = "SELECT * FROM public.projet";

		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());

		RowMapper<Projet> vRowMapper = new ProjetRM();

		List<Projet> vListProjets = vJdbcTemplate.query(vSQL, vRowMapper);

		return vListProjets;
	}

	@Override
	public void insertVersion(Version pVersion) throws FunctionalException {

		String vSQL = "INSERT INTO public.version (projet_id, numero) VALUES (:id, :numero)";
		NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		MapSqlParameterSource vParams = new MapSqlParameterSource();
		vParams.addValue("id", pVersion.getProjet().getId());
		vParams.addValue("numero", pVersion.getNumero());

		try {
			vJdbcTemplate.update(vSQL, vParams);
		} catch (DuplicateKeyException vEx) {
			String message = "La Version existe déjà ! id=" + pVersion.getNumero() + "/" + pVersion.getProjet().getId();
			LOGGER.error(message, vEx);
			throw new FunctionalException(message);
		}
	}

}
