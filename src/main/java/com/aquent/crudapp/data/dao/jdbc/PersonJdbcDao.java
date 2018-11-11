package com.aquent.crudapp.data.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.aquent.crudapp.data.dao.PersonDao;
import com.aquent.crudapp.domain.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aquent.crudapp.domain.Person;

/**
 * Spring JDBC implementation of {@link PersonDao}.
 */
public class PersonJdbcDao implements PersonDao {

    private static final String SQL_LIST_PEOPLE
            = "SELECT p.*, c.* FROM person p"
            + "  LEFT JOIN ("
            + "    contact ct"
            + "    INNER JOIN"
            + "      client c"
            + "    ON"
            + "      ct.client_id = c.client_id"
            + "  ) ON"
            + "    ct.person_id = p.person_id"
            + "  ORDER BY p.first_name, p.last_name, p.person_id";
    private static final String SQL_READ_PERSON
            = "SELECT p.*, c.* FROM person p"
            + "  LEFT JOIN ("
            + "    contact ct"
            + "    INNER JOIN"
            + "      client c"
            + "    ON"
            + "      ct.client_id = c.client_id"
            + "  ) ON"
            + "    ct.person_id = p.person_id"
            + "  WHERE p.person_id = :personId";
    private static final String SQL_DELETE_PERSON
            = "DELETE FROM person"
            + "  WHERE person_id = :personId";
    private static final String SQL_UPDATE_PERSON
            = "UPDATE person"
            + "  SET ("
            + "    first_name,"
            + "    last_name,"
            + "    email_address"
            + "  ) = ("
            + "    :firstName,"
            + "    :lastName,"
            + "    :emailAddress"
            + "  )"
            + "  WHERE person_id = :personId";
    private static final String SQL_CREATE_PERSON
            = "INSERT INTO person"
            + "  ("
            + "    first_name,"
            + "    last_name,"
            + "    email_address"
            + "  ) VALUES ("
            + "    :firstName,"
            + "    :lastName,"
            + "    :emailAddress"
            + "  )";
    private static final String SQL_CREATE_CONTACT
            = "INSERT INTO contact"
            + "  ("
            + "    person_id,"
            + "    client_id"
            + "  ) VALUES ("
            + "    :personId,"
            + "    :client.clientId"
            + "  )";
    private static final String SQL_DELETE_CONTACT
            = "DELETE FROM contact"
            + "  WHERE person_id = :personId";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Person> listPeople() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_PEOPLE, new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Person readPerson(Integer personId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_PERSON, Collections.singletonMap("personId", personId), new PersonRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deletePerson(Integer personId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_PERSON, Collections.singletonMap("personId", personId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updatePerson(Person person) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person);
        namedParameterJdbcTemplate.update(SQL_UPDATE_PERSON, parameterSource);

        // TODO figure out a better strategy that will update or insert if missing
        namedParameterJdbcTemplate.update(SQL_DELETE_CONTACT, parameterSource);
        if (person.getClient().getClientId() != null) {
            namedParameterJdbcTemplate.update(SQL_CREATE_CONTACT, parameterSource);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPerson(Person person) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person);
        namedParameterJdbcTemplate.update(SQL_CREATE_PERSON, parameterSource, keyHolder);
        person.setPersonId(keyHolder.getKey().intValue());
        if (person.getClient().getClientId() != null) {
            namedParameterJdbcTemplate.update(SQL_CREATE_CONTACT, parameterSource);
        }
        return person.getPersonId();
    }

    /**
     * Row mapper for person records.
     */
    static final class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setPersonId(rs.getInt("person_id"));
            person.setFirstName(rs.getString("first_name"));
            person.setLastName(rs.getString("last_name"));
            person.setEmailAddress(rs.getString("email_address"));
            int clientId = rs.getInt("client_id");
            Client client = new Client();
            if (!rs.wasNull()) {
                client.setClientId(clientId);
                client.setCompanyName(rs.getString("company_name"));
            }
            person.setClient(client);
            return person;
        }
    }
}
