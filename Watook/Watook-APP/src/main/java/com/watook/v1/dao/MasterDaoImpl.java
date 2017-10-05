package com.watook.v1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.watook.model.CodeValue;

@Repository
public class MasterDaoImpl implements MasterDao {

	@Autowired
	DataSource dataSource;

	private static final String SP_GET_CODEVLAUE_LIST = "select type.codetype,codevalue.* from cfg_codevalue codevalue inner join cfg_codetype type on codevalue.codetypeid=type.codetypeid";

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

}
