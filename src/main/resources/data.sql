INSERT INTO users(name, email, password, phone_number, role, created_at)
VALUES ('Admin',
        'admin@local',
        '$2a$10$VEFudSKOCud.dQuzagFEAeMPqvhHNaFhphz3YOLd/QF0oXbRLNcEy',
        '6900830239',
       'ADMIN',
        CURRENT_TIMESTAMP
       ),
    ('Seller One',
     'seller@local',
     '$2a$10$VEFudSKOCud.dQuzagFEAeMPqvhHNaFhphz3YOLd/QF0oXbRLNcEy',
     '8822648739',
     'SELLER',
     CURRENT_TIMESTAMP
     );
