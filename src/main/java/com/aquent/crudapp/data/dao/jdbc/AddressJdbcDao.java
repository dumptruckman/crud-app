package com.aquent.crudapp.data.dao.jdbc;

import com.aquent.crudapp.data.dao.AddressDao;
import com.aquent.crudapp.domain.Address;
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
            = "SELECT a.* FROM address a"
            + "  INNER JOIN"
            + "    person_address pa"
            + "  ON"
            + "    pa.address_id = a.address_id"
            + "  WHERE pa.person_id = :personId";
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
            + "    :address.streetAddress,"
            + "    :address.city,"
            + "    :address.state,"
            + "    :address.zipCode"
            + "  )";
    private static final String SQL_CREATE_PERSON_ADDRESS
            = "INSERT INTO person_address"
            + "  ("
            + "    person_id,"
            + "    address_id"
            + "  ) VALUES ("
            + "    :personId,"
            + "    :addressId"
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
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SQL_CREATE_ADDRESS, parameterSource, keyHolder);

        Map<String, Integer> parameterMap = new HashMap<>();
        parameterMap.put("personId", person.getPersonId());
        parameterMap.put("addressId", keyHolder.getKey().intValue());
        namedParameterJdbcTemplate.update(SQL_CREATE_PERSON_ADDRESS, parameterMap);

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
            return address;
        }
    }
}
