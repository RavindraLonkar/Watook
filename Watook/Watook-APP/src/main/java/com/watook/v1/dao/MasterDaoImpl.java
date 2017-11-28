package com.watook.v1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.watook.model.CodeValue;
import com.watook.utils.CommonQueries;

/**
 * @author namdev.arade
 * @cretaedDate : 26/10/2017
 * @description : This class is used to get master data information.
 *
 */
@Repository
public class MasterDaoImpl implements MasterDao {

	@Autowired
	DataSource dataSource;

	/**
	 * @date 26/10/2017
	 * @param
	 * @description:This method is for get master of codevalue.
	 * @return: All codevalues.
	 * 
	 */
	@Override
	public List<CodeValue> getCodeValueList() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.query(CommonQueries.SP_GET_CODEVLAUE_LIST, new Object[] {}, new RowMapper<CodeValue>() {
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

	/**
	 * @date 27/11/2017
	 * @param 
	 * @description:This method is for get terms and conditions.
	 * @return: All codevalues.
	 * 
	 */
	@Override
	public CodeValue getTermsandConditions() {
		NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		CodeValue termsAndCondition = namedJdbcTemplate.queryForObject(CommonQueries.SP_GET_TERMS_AND_CONDITION,
				parameters, new RowMapper<CodeValue>() {
					@Override
					public CodeValue mapRow(ResultSet rs, int rowNum) throws SQLException {
						CodeValue codeValue = new CodeValue();
						codeValue.setCodeValueID(rs.getString("CodeValueID"));
						codeValue.setCodeTypeID(rs.getString("CodeTypeID"));
						codeValue.setCodeValue(rs.getString("CodeValue"));
						codeValue.setCodeValueDescription(rs.getString("CodeValueDescription"));
						codeValue.setIsActive(rs.getString("IsActive"));
						return codeValue;
					}

				});
		return termsAndCondition;
	}

}
