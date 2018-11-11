INSERT INTO person (
    first_name,
    last_name,
    email_address
) VALUES (
    'John',
    'Smith',
    'fake1@aquent.com'
), (
    'Jane',
    'Smith',
    'fake2@aquent.com'
);

INSERT INTO address_type (
    type_name
) VALUES (
    'Personal'
), (
    'Physical'
), (
    'Mailing'
);

INSERT INTO address (
    street_address,
    city,
    state,
    zip_code
) VALUES (
    '123 Any St.',
    'Asheville',
    'NC',
    '28801'
), (
    '123 Any St.',
    'Asheville',
    'NC',
    '28801'
), (
    '123 Any St.',
    'Asheville',
    'NC',
    '28801'
), (
    '123 Any St.',
    'Asheville',
    'NC',
    '28801'
);

INSERT INTO person_address (
    person_id,
    address_id,
    address_type_id
) VALUES (
    0,
    0,
    0
), (
    1,
    1,
    0
);

INSERT INTO client (
    company_name,
    website,
    phone_number
) VALUES (
    'XYZ, Inc.',
    'www.xyz.com',
    '8008675309'
);

INSERT INTO client_address (
    client_id,
    address_id,
    address_type_id
) VALUES (
    0,
    2,
    1
), (
    0,
    3,
    2
);