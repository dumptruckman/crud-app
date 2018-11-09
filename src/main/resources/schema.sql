CREATE TABLE person (
    person_id integer IDENTITY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email_address varchar(50) NOT NULL
);

CREATE TABLE address (
    address_id integer IDENTITY,
    street_address varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip_code varchar(5) NOT NULL
);

CREATE TABLE person_address (
    person_address_id integer IDENTITY,
    person_id integer NOT NULL,
    address_id integer NOT NULL,
    CONSTRAINT u_person_address UNIQUE (person_id,address_id),
    CONSTRAINT fk_person_id FOREIGN KEY (person_id) REFERENCES person(person_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_address_id FOREIGN KEY (address_id) REFERENCES address(address_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE address_type (
    address_type_id integer IDENTITY,
    type_name varchar(50) NOT NULL
);