package com.paydi.config.multitenancy;

import java.sql.SQLException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

@Configuration
public class DataSourceConfig {

	private final DataSourceProperties dataSourceProperties;

	public DataSourceConfig(DataSourceProperties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
	}

	@Bean
	public DataSource dataSource() {
		TenantRoutingDataSource customDataSource = new TenantRoutingDataSource();
		customDataSource.setTargetDataSources(dataSourceProperties.getDataSources());
		return customDataSource;
	}

	@PostConstruct
	public void migrate() {
		dataSourceProperties.getDataSources().values().stream().map(dataSource -> (DataSource) dataSource)
				.forEach(t -> {
					try {
						migrate(t);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
	}

	private void migrate(DataSource dataSource) throws SQLException {

		final Flyway flyway = Flyway.configure().dataSource(dataSource).locations("sql/migrations").outOfOrder(true)
				.placeholderReplacement(false).configuration(Map.of("flyway.table", "schema_version")).load();
		repairFlywayVersionSkip(flyway.getConfiguration().getDataSource());

		try {
			flyway.repair();
			flyway.migrate();
		} catch (FlywayException e) {
			String betterMessage = e.getMessage() + "; for Tenant DB URL: " + dataSource.getConnection().getSchema()
					+ ", username: " + dataSource.getConnection().getClientInfo();
			throw new FlywayException(betterMessage, e);
		}
	}

	private void repairFlywayVersionSkip(DataSource source) {
		JdbcTemplate template = new JdbcTemplate(source);
		System.out.println("repairFlywayVersionSkip: Check whether the version table is in old format ");
		SqlRowSet ts = template.queryForRowSet("SHOW TABLES LIKE 'schema_version';");
		if (ts.next()) {
			SqlRowSet rs = template.queryForRowSet("SHOW  COLUMNS FROM `schema_version` LIKE 'version_rank';");
			if (rs.next()) {
				System.out.println(
						"repairFlywayVersionSkip: The schema_version table is in old format, executing repair ");
				template.execute("CREATE TABLE `schema_version_history` (  `version_rank` int(11) NOT NULL, "
						+ " `installed_rank` int(11) NOT NULL,  `version` varchar(50) NOT NULL,  `description` varchar(200) NOT NULL,  "
						+ "`type` varchar(20) NOT NULL,  `script` varchar(1000) NOT NULL,  `checksum` int(11) DEFAULT NULL, "
						+ " `installed_by` varchar(100) NOT NULL,  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP, "
						+ " `execution_time` int(11) NOT NULL,  `success` tinyint(1) NOT NULL,  PRIMARY KEY (`version`), "
						+ " KEY `schema_version_vr_idx` (`version_rank`),  KEY `schema_version_ir_idx` (`installed_rank`), "
						+ " KEY `schema_version_s_idx` (`success`));");
				template.execute("INSERT INTO schema_version_history select * from schema_version;");
				template.execute("DROP TABLE schema_version;");
				template.execute("CREATE TABLE `schema_version` ( `installed_rank` int(11) NOT NULL, "
						+ "  `version` varchar(50) DEFAULT NULL,   `description` varchar(200) NOT NULL,   `type` varchar(20) NOT NULL,  "
						+ " `script` varchar(1000) NOT NULL,   `checksum` int(11) DEFAULT NULL,   `installed_by` varchar(100) NOT NULL,  "
						+ " `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,   `execution_time` int(11) NOT NULL, "
						+ "  `success` tinyint(1) NOT NULL,   PRIMARY KEY (`installed_rank`),   KEY `flyway_schema_history_s_idx` (`success`) );");
				template.execute(
						"INSERT INTO schema_version (installed_rank, version, description, type, script, checksum, "
								+ "installed_by, installed_on, execution_time, success) SELECT installed_rank, version, description, type, "
								+ "script, checksum, installed_by, installed_on, execution_time, success FROM schema_version_history;");
				template.execute("DROP TABLE schema_version_history;");

				System.out.println("repairFlywayVersionSkip: The schema_version repair completed.");
			} else {
				System.out.println("repairFlywayVersionSkip: The schema_version table format is new, aborting repair");
			}
		} else {
			System.out.println("repairFlywayVersionSkip: The schema_version table does not exist, aborting repair");
		}
	}

}