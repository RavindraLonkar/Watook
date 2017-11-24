package com.watook.v1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.watook.model.CodeValue;

@Repository
public class MasterDaoImpl implements MasterDao {

	@Autowired
	DataSource dataSource;

	private static final String SP_GET_CODEVLAUE_LIST = "select type.codetype,codevalue.* from cfg_codevalue codevalue inner join cfg_codetype type on codevalue.codetypeid=type.codetypeid";
	
	private static final String SP_GET_TERMS_AND_CONDITION = "select * from cfg_codevalue where codevalueid=801 and codetypeid=8";

	@Override
	public List<CodeValue> getCodeValueList() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.query(SP_GET_CODEVLAUE_LIST, new Object[] {}, new RowMapper<CodeValue>() {
			@Override
			public CodeValue mapRow(ResultSet rs, int rowNum) throws SQLException {
				CodeValue codeValue = new CodeValue();
				codeValue.setCodeType(rs.getString("CodeType"));
				codeValue.setCodeValueID(rs.getString("CodeValueID"));
				codeValue.setCodeTypeID(rs.getString("CodeTypeID"));
				codeValue.setCodeValue(rs.getString("CodeValue"));
				codeValue.setCodeValueDescription(rs.getString("CodeValueDescription"));
				codeValue.setIsActive(rs.getString("IsActive"));
				return codeValue;
			}
		});
	}

	
	@Override
	public CodeValue getTermsandConditions() {
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		CodeValue termsAndCondition = namedJdbcTemplate.queryForObject(SP_GET_TERMS_AND_CONDITION, parameters,new BeanPropertyRowMapper<CodeValue>());
		return termsAndCondition;
	}

}
