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

	private static final String SP_GET_USERLIST = "select * from cfg_codevalue where codetypeid=?";

	@Override
	public List<CodeValue> getCodeValueList(Integer codetypeid) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate.query(SP_GET_USERLIST, new Object[] {codetypeid}, new RowMapper<CodeValue>() {
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
	}

}
