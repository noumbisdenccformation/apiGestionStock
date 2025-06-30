-- Script d'initialisation de la base de données MySQL pour Gestion de Stock
-- Ce script sera exécuté automatiquement par Spring Boot

-- Insertion des entreprises
INSERT INTO entreprise (id, creationDate, lastModifiedDate) VALUES 
(1, NOW(), NOW());

-- Insertion des catégories
INSERT INTO category (id, code, designation, creationDate, lastModifiedDate) VALUES 
(1, 'CAT001', 'Électronique', NOW(), NOW()),
(2, 'CAT002', 'Informatique', NOW(), NOW()),
(3, 'CAT003', 'Mobilier', NOW(), NOW()),
(4, 'CAT004', 'Vêtements', NOW(), NOW()),
(5, 'CAT005', 'Livres', NOW(), NOW());

-- Insertion des articles
INSERT INTO article (id, codearticle, designation, prixunitaireht, tauxtva, prixunitairettc, photo, idcategory, identreprise, creationDate, lastModifiedDate) VALUES 
(1, 'ART001', 'Ordinateur portable Dell', 899.99, 20.00, 1079.99, 'laptop.jpg', 1, 1, NOW(), NOW()),
(2, 'ART002', 'Souris sans fil', 25.99, 20.00, 31.19, 'mouse.jpg', 1, 1, NOW(), NOW()),
(3, 'ART003', 'Clavier mécanique', 89.99, 20.00, 107.99, 'keyboard.jpg', 2, 1, NOW(), NOW()),
(4, 'ART004', 'Bureau en bois', 299.99, 20.00, 359.99, 'desk.jpg', 3, 1, NOW(), NOW()),
(5, 'ART005', 'T-shirt coton', 19.99, 20.00, 23.99, 'tshirt.jpg', 4, 1, NOW(), NOW());

-- Insertion des utilisateurs
INSERT INTO utilisateur (id, nom, prenom, email, motdepasse, datedenaissance, photo, identreprise, adresse1, adresse2, ville, codepostale, pays, creation_date, last_modified_date) VALUES 
(1, 'Admin', 'User', 'admin@gestiondestock.com', '$2a$10$rDmFN6ZqJ9K9ZqJ9K9ZqJ9K9ZqJ9K9ZqJ9K9ZqJ9K9ZqJ9K9ZqJ9K9ZqJ9', '1990-01-01', 'admin.jpg', 1, '123 Rue de la Paix', 'Appartement 1', 'Paris', '75001', 'France', NOW(), NOW());

-- Insertion des rôles
INSERT INTO role (id, rolename, idutilisateur, creation_date, last_modified_date) VALUES 
(1, 'ADMIN', 1, NOW(), NOW()),
(2, 'USER', 1, NOW(), NOW()); 