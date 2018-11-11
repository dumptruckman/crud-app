package com.aquent.crudapp.data.dao.jdbc;

import com.aquent.crudapp.data.dao.ClientDao;
import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.domain.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Spring JDBC implementation of {@link ClientDao}.
 */
public class ClientJdbcDao implements ClientDao {

    private static final String SQL_LIST_CLIENTS
            = "SELECT c.*, p.* FROM client c"
            + "  LEFT JOIN ("
            + "    contact ct"
            + "    INNER JOIN"
            + "      person p"
            + "    ON"
            + "      ct.person_id = p.person_id"
            + "  ) ON"
            + "    ct.client_id = c.client_id"
            + "  ORDER BY c.company_name, c.client_id";
    private static final String SQL_READ_CLIENT
            = "SELECT c.*, p.* FROM client c"
            + "  LEFT JOIN ("
            + "    contact ct"
            + "    INNER JOIN"
            + "      person p"
            + "    ON"
            + "      ct.person_id = p.person_id"
            + "  ) ON"
            + "    ct.client_id = c.client_id"
            + "  WHERE c.client_id = :clientId";
    private static final String SQL_DELETE_CLIENT
            = "DELETE FROM client"
            + "  WHERE client_id = :clientId";
    private static final String SQL_UPDATE_CLIENT
            = "UPDATE client"
            + "  SET ("
            + "    company_name,"
            + "    website,"
            + "    phone_number"
            + "  ) = ("
            + "    :companyName,"
            + "    :website,"
            + "    :phoneNumber"
            + "  )"
            + "  WHERE client_id = :clientId";
    private static final String SQL_CREATE_CLIENT
            = "INSERT INTO client"
            + "  ("
            + "    company_name,"
            + "    website,"
            + "    phone_number"
            + "  ) VALUES ("
            + "    :companyName,"
            + "    :website,"
            + "    :phoneNumber"
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
            + "  WHERE client_id = :clientId";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Client> listClients() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SQL_LIST_CLIENTS, new ClientListResultSetExtractor());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Client readClient(Integer clientId) {
        return namedParameterJdbcTemplate.query(SQL_READ_CLIENT, Collections.singletonMap("clientId", clientId), new ClientResultSetExtractor());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteClient(Integer clientId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_CLIENT, Collections.singletonMap("clientId", clientId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateClient(Client client) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(client);
        namedParameterJdbcTemplate.update(SQL_UPDATE_CLIENT, parameterSource);

        // TODO figure out a better strategy that will update or insert if missing
        namedParameterJdbcTemplate.update(SQL_DELETE_CONTACT, parameterSource);
        for (Person contact : client.getContacts()) {
            namedParameterJdbcTemplate.update(SQL_CREATE_CONTACT, new BeanPropertySqlParameterSource(contact));
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createClient(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(client);

        namedParameterJdbcTemplate.update(SQL_CREATE_CLIENT, parameterSource, keyHolder);
        client.setClientId(keyHolder.getKey().intValue());

        // TODO find a way to batch update
        for (Person contact : client.getContacts()) {
            namedParameterJdbcTemplate.update(SQL_CREATE_CONTACT, new BeanPropertySqlParameterSource(contact));
        }

        return client.getClientId();
    }

    /**
     * Row mapper for client records.
     */
    private static final class ClientRowMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setClientId(rs.getInt("client_id"));
            client.setCompanyName(rs.getString("company_name"));
            client.setWebsite(rs.getString("website"));
            client.setPhoneNumber(rs.getString("phone_number"));
            return client;
        }
    }

    /**
     * ResultSet extractor capable of extract single clients from result sets with one ore more clients
     */
    private static final class ClientResultSetExtractor implements ResultSetExtractor<Client> {

        private final RowMapper<Client> clientRowMapper = new ClientRowMapper();
        private final RowMapper<Person> personRowMapper = new PersonJdbcDao.PersonRowMapper();

        private final int initialRow;
        private final BiConsumer<Integer, Boolean> rowUpdater;

        private ClientResultSetExtractor(int initialRow, BiConsumer<Integer, Boolean> rowUpdater) {
            this.initialRow = initialRow;
            this.rowUpdater = rowUpdater;
        }

        private ClientResultSetExtractor() {
            this(0, null);
        }

        @Override
        public Client extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Client client = null;
            int row = initialRow;

            if (row == 0) {
                // Needs to advance cursor to the first row if it hasn't ever been advanced for this result set
                if (!resultSet.next()) {
                    // No clients in client list
                    // Tell the list extractor there is no more rows
                    rowUpdater.accept(row, true);
                    return null;
                }
            }
            do {
                if (row == initialRow) {
                    client = clientRowMapper.mapRow(resultSet, row);
                    client.setContacts(new LinkedList<>());
                }

                int clientId = resultSet.getInt("client_id");
                if (client.getClientId() != clientId) {
                    // Update the list extractor with the current row because the current row is a new client
                    rowUpdater.accept(row, false);
                    return client;
                }

                client.getContacts().add(personRowMapper.mapRow(resultSet, row));
                row++;
            } while (resultSet.next());
            if (rowUpdater != null) {
                // Tell the list extractor there is no more rows
                rowUpdater.accept(row, true);
            }
            return client;
        }
    }

    private static final class ClientListResultSetExtractor implements ResultSetExtractor<List<Client>> {

        private int row = 0;
        private boolean finished = false;

        @Override
        public List<Client> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            List<Client> clients = new LinkedList<>();
            while (!finished) {
                Client client = new ClientResultSetExtractor(row, (newRow, finished) -> {
                    row = newRow;
                    this.finished = finished;
                }).extractData(resultSet);
                clients.add(client);
            }
            return clients;
        }
    }
}
