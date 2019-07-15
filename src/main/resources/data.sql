INSERT INTO person (
    id,
    first_name,
    last_name,
    email_address,
    street_address,
    city,
    state,
    zip_code,
    created_date,
    last_modified_date
) VALUES (
    person_seq.nextval,
    'John',
    'Smith',
    'fake1@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    sysdate,
    sysdate
), (
    person_seq.nextval,
    'Jane',
    'Smith',
    'fake2@aquent.com',
    '123 Any St.',
    'Asheville',
    'NC',
    '28801',
    sysdate,
    sysdate
);

INSERT INTO client (
    id,
    name,
    website_uri,
    phone_number,
    street_address,
    city,
    state,
    zip_code,
    created_date,
    last_modified_date
) VALUES (
    client_seq.nextval,
    'Acme Inc.',
    'http://www.acme.inc',
    '8885551234',
    '123 Main St.',
    'Somewhere',
    'NC',
    '12345',
    sysdate,
    sysdate
);

INSERT into contacts (
    person_id, client_id
) VALUES (
    1, 1
), (
    2, 1
);
