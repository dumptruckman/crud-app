package com.aquent.crudapp.data.dao.jdbc;

import com.aquent.crudapp.data.dao.AddressDao;
import com.aquent.crudapp.domain.Address;
import com.aquent.crudapp.domain.AddressType;
import com.aquent.crudapp.domain.Client;
import com.aquent.crudapp.domain.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring JDBC implementation of {@link AddressDao}.
 */
public class AddressJdbcDao implements AddressDao {

    private static final String SQL_READ_ADDRESS_FOR_PERSON
            = "SELECT a.*, t.* FROM address a"
            + "  INNER JOIN ("
            + "    person_address pa"
            + "    INNER JOIN"
            + "      address_type t"
            + "    ON"
            + "      pa.address_type_id = t.address_type_id"
            + "  ) ON"
            + "    pa.address_id = a.address_id"
            + "  WHERE pa.person_id = :personId";
    private static final String SQL_READ_PHYSICAL_ADDRESS_FOR_CLIENT
            = "SELECT a.*, t.* FROM address a"
            + "  INNER JOIN ("
            + "    client_address ca"
            + "    INNER JOIN"
            + "      address_type t"
            + "    ON"
            + "      ca.address_type_id = t.address_type_id"
            + "  ) ON"
            + "    ca.address_id = a.address_id"
            + "  WHERE"
            + "     ca.client_id = :clientId"
            + "  AND"
            + "     t.type_name = 'Physical'";
    private static final String SQL_READ_MAILING_ADDRESS_FOR_CLIENT
            = "SELECT a.*, t.* FROM address a"
            + "  INNER JOIN ("
            + "    client_address ca"
            + "    INNER JOIN"
            + "      address_type t"
            + "    ON"
            + "      ca.address_type_id = t.address_type_id"
            + "  ) ON"
            + "    ca.address_id = a.address_id"
            + "  WHERE"
            + "     ca.client_id = :clientId"
            + "  AND"
            + "     t.type_name = 'Mailing'";
    private static final String SQL_DELETE_ADDRESS
            = "DELETE FROM address"
            + "  WHERE address_id = :addressId";
    private static final String SQL_UPDATE_ADDRESS
            = "UPDATE address"
            + "  SET ("
            + "    street_address,"
            + "    city,"
            + "    state,"
            + "    zip_code"
            + "  ) = ("
            + "    :streetAddress,"
            + "    :city,"
            + "    :state,"
            + "    :zipCode"
            + "  )"
            + "  WHERE address_id = :addressId";
    private static final String SQL_CREATE_ADDRESS
            = "INSERT INTO address"
            + "  ("
            + "    street_address,"
            + "    city,"
            + "    state,"
            + "    zip_code"
            + "  ) VALUES ("
            + "    :streetAddress,"
            + "    :city,"
            + "    :state,"
            + "    :zipCode"
            + "  )";
    private static final String SQL_CREATE_PERSON_ADDRESS
            = "INSERT INTO person_address"
            + "  ("
            + "    person_id,"
            + "    address_id,"
            + "    address_type_id"
            + "  ) VALUES ("
            + "    :personId,"
            + "    :addressId,"
            + "    (SELECT address_type_id FROM address_type WHERE type_name = 'Personal')"
            + "  )";
    private static final String SQL_CREATE_PHYSICAL_CLIENT_ADDRESS
            = "INSERT INTO client_address"
            + "  ("
            + "    client_id,"
            + "    address_id,"
            + "    address_type_id"
            + "  ) VALUES ("
            + "    :clientId,"
            + "    :addressId,"
            + "    (SELECT address_type_id FROM address_type WHERE type_name = 'Physical')"
            + "  )";
    private static final String SQL_CREATE_MAILING_CLIENT_ADDRESS
            = "INSERT INTO client_address"
            + "  ("
            + "    client_id,"
            + "    address_id,"
            + "    address_type_id"
            + "  ) VALUES ("
            + "    :clientId,"
            + "    :addressId,"
            + "    (SELECT address_type_id FROM address_type WHERE type_name = 'Mailing')"
            + "  )";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Address readAddressForPerson(Integer personId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_ADDRESS_FOR_PERSON, Collections.singletonMap("personId", personId), new AddressRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Address readPhysicalAddressForClient(Integer clientId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_PHYSICAL_ADDRESS_FOR_CLIENT, Collections.singletonMap("clientId", clientId), new AddressRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Address readMailingAddressForClient(Integer clientId) {
        return namedParameterJdbcTemplate.queryForObject(SQL_READ_MAILING_ADDRESS_FOR_CLIENT, Collections.singletonMap("clientId", clientId), new AddressRowMapper());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void deleteAddress(Integer addressId) {
        namedParameterJdbcTemplate.update(SQL_DELETE_ADDRESS, Collections.singletonMap("addressId", addressId));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void updateAddress(Address address) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(address);
        namedParameterJdbcTemplate.update(SQL_UPDATE_ADDRESS, parameterSource);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createAddressForPerson(Person person) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person.getAddress());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_ADDRESS, parameterSource, keyHolder);

        Map<String, Integer> parameterMap = new HashMap<>();
        parameterMap.put("personId", person.getPersonId());
        parameterMap.put("addressId", keyHolder.getKey().intValue());
        namedParameterJdbcTemplate.update(SQL_CREATE_PERSON_ADDRESS, parameterMap);

        return keyHolder.getKey().intValue();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createPhysicalAddressForClient(Client client) {
        return createAddressForClient(client, new BeanPropertySqlParameterSource(client.getPhysicalAddress()), SQL_CREATE_PHYSICAL_CLIENT_ADDRESS);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public Integer createMailingAddressForClient(Client client) {
        return createAddressForClient(client, new BeanPropertySqlParameterSource(client.getMailingAddress()), SQL_CREATE_MAILING_CLIENT_ADDRESS);
    }

    private Integer createAddressForClient(Client client, BeanPropertySqlParameterSource parameterSource, String sqlStatement) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_ADDRESS, parameterSource, keyHolder);

        Map<String, Integer> parameterMap = new HashMap<>();
        parameterMap.put("clientId", client.getClientId());
        parameterMap.put("addressId", keyHolder.getKey().intValue());
        namedParameterJdbcTemplate.update(sqlStatement, parameterMap);

        return keyHolder.getKey().intValue();
    }

    /**
     * Row mapper for address records.
     */
    private static final class AddressRowMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address address = new Address();
            address.setAddressId(rs.getInt("address_id"));
            address.setStreetAddress(rs.getString("street_address"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZipCode(rs.getString("zip_code"));
            address.setAddressType(AddressType.fromTypeName(rs.getString("type_name")));
            return address;
        }
    }
}
