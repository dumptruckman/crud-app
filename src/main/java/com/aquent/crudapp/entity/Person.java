package com.aquent.crudapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

/**
 * The person entity corresponding to the "person" table in the database.
 */
@Entity
@Getter
@Setter
@Table(name = "person")
public class Person implements BaseEntity {

    @Id
    @SequenceGenerator(name = "PERSON_SEQ", sequenceName = "person_seq", allocationSize = 1)
    @GeneratedValue(generator = "PERSON_SEQ")
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "email_address", length = 50, nullable = false)
    private String emailAddress;

    @Column(name = "street_address", length = 50, nullable = false)
    private String streetAddress;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "state", length = 2, nullable = false)
    private String state;

    @Column(name = "zip_code", length = 9, nullable = false)
    private String zipCode;

    /** This fun mapping handles the relationship between
     *  a person and contacts.
     *
     *  It creates a junction table (called "contacts") in the background,
     *  but that junction table is not explicitly declared
     *  as a JPA entity.
     */
    @OneToOne
    @JoinTable(
            name = "contacts",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private Client client;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;

    public Optional<Client> getClient() {
        return Optional.ofNullable(client);
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
