package com.paydi.repository;

import com.paydi.config.multitenancy.TenantStorage;
import com.paydi.entity.MMSAppAccessEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.sentry.Sentry;

@Repository
public class MMSUtilRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MMSUtilRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public boolean isSameCurrency(Long srcAccountId, Long desAccountId) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT COUNT(*) ");
			sql.append(" JOIN " + TenantStorage.getCurrentTenantDB() + ".m_savings_account ac ");
			sql.append(" WHERE ac.id = ? OR  ac.id = ? GROUP BY ac.currency_code  ");

			int numOfcurrency = jdbcTemplate.queryForObject(sql.toString(), new Object[] { srcAccountId, desAccountId },
					Integer.class);
			return numOfcurrency == 1;
		} catch (Exception e) {
			Sentry.captureException(e);
			return false;
		}
	}

	public MMSAppAccessEntity checkApiKey(String apiKey) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ap.*, akc.api_key ");
			sql.append(" FROM mms_api_key_access akc ");
			sql.append(" JOIN mms_app_access ap ON ap.id = akc.app_id ");
			sql.append(" WHERE akc.api_key = ?  ");

			return jdbcTemplate.queryForObject(sql.toString(), new Object[] { apiKey },
					(rs, rowNum) -> new MMSAppAccessEntity(rs.getLong("id"), rs.getString("api_key"),
							rs.getString("name"), rs.getString("desc"), rs.getString("external_id")));
		} catch (Exception e) {
			Sentry.captureException(e);
			return null;
		}
	}

}
