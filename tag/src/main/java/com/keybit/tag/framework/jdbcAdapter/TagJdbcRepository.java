package com.keybit.tag.framework.jdbcAdapter;

import com.keybit.tag.domain.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class TagJdbcRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String TABLE = "tag";

    public void saveAll(Set<Tag> tags) {
        String sql = String.format("""
            INSERT INTO %s (id, name)
            VALUES (:id, :name)
        """, TABLE);

        SqlParameterSource[] params = tags
                .stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
