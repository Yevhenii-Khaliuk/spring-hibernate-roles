package com.khaliuk.dao.template;

import com.khaliuk.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

//@Component
@Deprecated
public class UserJdbcTemplate extends JdbcTemplate {
    public UserJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    public User getUserByUsername(String username) {
        return queryForObject("SELECT * FROM USERS U WHERE U.USERNAME = ?",
                new Object[] {username}, new UserMapper());
    }

    private class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getLong("ID"));
            user.setUsername(resultSet.getString("USERNAME"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setToken(resultSet.getString("TOKEN"));
            user.setFirstName(resultSet.getString("FIRST_NAME"));
            user.setLastName(resultSet.getString("LAST_NAME"));
            return user;
        }
    }
}
