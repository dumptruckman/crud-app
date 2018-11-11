CREATE TABLE person (
    person_id integer IDENTITY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL
);

CREATE TABLE client (
    client_id integer IDENTITY,
    company_name varchar(100) NOT NULL,
    website varchar(100) NOT NULL,
    phone_number varchar(10) NOT NULL
);

CREATE TABLE address (
    address_id integer IDENTITY,
    street_address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip_code varchar(5) NOT NULL
);

CREATE TABLE address_type (
    address_type_id integer IDENTITY,
    type_name varchar(50) NOT NULL
);

CREATE TABLE person_address (
    person_address_id integer IDENTITY,
    person_id integer NOT NULL,
    address_id integer NOT NULL,
    address_type_id integer NOT NULL,
    CONSTRAINT u_person_address UNIQUE (person_id,address_type_id),
    CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person(person_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_person_address_id FOREIGN KEY (address_id) REFERENCES address(address_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_person_address_type_id FOREIGN KEY (address_type_id) REFERENCES address_type(address_type_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE client_address (
    client_address_id integer IDENTITY,
    client_id integer NOT NULL,
    address_id integer NOT NULL,
    address_type_id integer NOT NULL,
    CONSTRAINT u_client_address UNIQUE (client_id,address_type_id),
    CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES client(client_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_client_address_id FOREIGN KEY (address_id) REFERENCES address(address_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_client_address_type_id FOREIGN KEY (address_type_id) REFERENCES address_type(address_type_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE contact (
    contact_id integer IDENTITY,
    person_id integer NOT NULL,
    client_id integer NOT NULL,
    CONSTRAINT u_contact UNIQUE (person_id),
    CONSTRAINT fk_contact_client_id FOREIGN KEY (client_id) REFERENCES client(client_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_contact_person_id FOREIGN KEY (person_id) REFERENCES person(person_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
