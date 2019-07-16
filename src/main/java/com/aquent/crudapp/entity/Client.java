package com.aquent.crudapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "client")
public class Client implements BaseEntity {

    @Id
    @SequenceGenerator(name = "CLIENT_SEQ", sequenceName = "client_seq", allocationSize = 1)
    @GeneratedValue(generator = "CLIENT_SEQ")
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "phone_number", length = 10, nullable = false)
    private String phoneNumber;

    @Column(name = "website_uri", length = 50, nullable = false)
    private String websiteUri;

    @Column(name = "street_address", length = 50, nullable = false)
    private String streetAddress;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "state", length = 2, nullable = false)
    private String state;

    @Column(name = "zip_code", length = 9, nullable = false)
    private String zipCode;

    /**
     * This is the other side of the contacts
     * junction table declaration.
     *
     * The "mappedBy" attribute makes this class
     * responsible for mapping its own ID in the
     * junction table.
     */
    @OneToMany(mappedBy = "client")
    private Set<Person> contacts = new HashSet<>();

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;
}
