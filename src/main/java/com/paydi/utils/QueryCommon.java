package com.paydi.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
 

@Service
public class QueryCommon<IFTBParamsEntity> {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(QueryCommon.class);
	@Autowired
    private JdbcTemplate jdbcTemplate; 
	 
	public Map<String, String> findParamByParamCode(String paramCode) throws Exception {
		 
	    String sql = "select param_value, param_description  from iftb_params where param_code = ?";
	 
	    Map<String, String> map = (Map) jdbcTemplate.query(sql, new Object[]{paramCode}, new ResultSetExtractor() {
	        public Object extractData(ResultSet rs) throws SQLException {
	            Map map = new HashMap();
	            while (rs.next()) {
	                String param_value = rs.getString("param_value");
	                String param_description = rs.getString("param_description");
	                map.put(param_value, param_description);
	            }
	            logger.info(map + " = " + map.size());
	            return map;
	        };
	    });
	     
	    return map;
	}


}
