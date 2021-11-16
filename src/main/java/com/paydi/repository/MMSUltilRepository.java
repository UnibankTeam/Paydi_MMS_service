package com.paydi.repository;

import com.paydi.config.multitenancy.TenantStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.sentry.Sentry;

@Repository
public class MMSUltilRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MMSUltilRepository() throws Exception {
		super();

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

	
}
