INSERT INTO users (id, name, created_at, email, password, phone_number, role) VALUES
(1, 'Rahul Doley', NOW(), 'rahul@local','$2a$10$VEFudSKOCud.dQuzagFEAeMPqvhHNaFhphz3YOLd/QF0oXbRLNcEy', '9876543210', 'ROLE_SELLER'),
(2, 'Anjali Das', NOW(), 'anjali@local','$2a$10$VEFudSKOCud.dQuzagFEAeMPqvhHNaFhphz3YOLd/QF0oXbRLNcEy','9876543210',  'ROLE_SELLER'),
(3, 'Arjun Reddy', NOW(), 'arjun@local','$2a$10$VEFudSKOCud.dQuzagFEAeMPqvhHNaFhphz3YOLd/QF0oXbRLNcEy','9876543210', 'ROLE_SELLER'),
(4, 'Admin', NOW(), 'admin@local','$2a$10$VEFudSKOCud.dQuzagFEAeMPqvhHNaFhphz3YOLd/QF0oXbRLNcEy','9876543210', 'ROLE_ADMIN');

INSERT INTO listings
(title, description, category, price, contact_info, status, created_at, updated_at, user_id)
VALUES
    ('MacBook Air M1','Lightly used MacBook Air M1 8GB RAM 256GB SSD','electronics',65000,'9876543210','APPROVED',NOW(),NOW(),1),

    ('iPhone 13','Excellent condition iPhone 13 128GB with box','electronics',52000,'9876543210','APPROVED',NOW(),NOW(),1),

    ('Dell XPS 13','Dell XPS 13 laptop 16GB RAM','electronics',72000,'9876543210','APPROVED',NOW(),NOW(),2),

    ('Study Table','Wooden study table perfect for students','furniture',2500,'9876543210','APPROVED',NOW(),NOW(),2),

    ('Office Chair','Ergonomic office chair good condition','furniture',1800,'9876543210','APPROVED',NOW(),NOW(),3),

    ('Samsung Galaxy S22','Flagship Samsung phone with charger','electronics',39000,'9876543210','APPROVED',NOW(),NOW(),3),

    ('Engineering Mathematics Book','B.Tech Mathematics reference book','books',400,'9876543210','APPROVED',NOW(),NOW(),1),

    ('Gaming Monitor 24inch','Full HD 144Hz gaming monitor','electronics',9000,'9876543210','APPROVED',NOW(),NOW(),2),

    ('Used Bicycle','Mountain bicycle good for commuting','vehicles',3500,'9876543210','APPROVED',NOW(),NOW(),3),

    ('HP Printer','HP DeskJet printer with ink','electronics',2200,'9876543210','APPROVED',NOW(),NOW(),1),

    ('Arduino Starter Kit','Arduino UNO starter electronics kit','electronics',1200,'9876543210','APPROVED',NOW(),NOW(),2),

    ('Mechanical Keyboard','RGB mechanical keyboard blue switches','electronics',3200,'9876543210','APPROVED',NOW(),NOW(),3),

    ('Hostel Bed','Single steel hostel bed','furniture',2000,'9876543210','APPROVED',NOW(),NOW(),1),

    ('Graphic Tablet','Wacom drawing tablet ideal for designers','electronics',4800,'9876543210','APPROVED',NOW(),NOW(),2),

    ('Physics Textbook','University physics textbook','books',500,'9876543210','APPROVED',NOW(),NOW(),3);
