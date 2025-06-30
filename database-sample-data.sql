-- =====================================================
-- DONNÉES D'EXEMPLE POUR L'API GESTION DE STOCK
-- =====================================================

-- Insertion des catégories d'exemple
INSERT INTO category (id, code, designation, creation_date, last_modified_date) VALUES
(1, 'INFO', 'Informatique', NOW(), NOW()),
(2, 'BUREAU', 'Matériel de bureau', NOW(), NOW()),
(3, 'MOBILIER', 'Mobilier', NOW(), NOW()),
(4, 'ELECTRONIQUE', 'Électronique', NOW(), NOW()),
(5, 'LIVRES', 'Livres et documentation', NOW(), NOW());

-- Insertion des articles d'exemple
INSERT INTO article (id, code_article, designation, prix_unitaire_ht, taux_tva, prix_unitaire_ttc, photo, category_id, creation_date, last_modified_date) VALUES
(1, 'PC001', 'Ordinateur portable Dell', 800.00, 20.00, 960.00, 'pc-dell.jpg', 1, NOW(), NOW()),
(2, 'PC002', 'Ordinateur de bureau HP', 600.00, 20.00, 720.00, 'pc-hp.jpg', 1, NOW(), NOW()),
(3, 'TB001', 'Table de bureau', 150.00, 20.00, 180.00, 'table-bureau.jpg', 3, NOW(), NOW()),
(4, 'CH001', 'Chaise de bureau ergonomique', 200.00, 20.00, 240.00, 'chaise-bureau.jpg', 3, NOW(), NOW()),
(5, 'STYLO001', 'Stylos à bille (lot de 10)', 5.00, 20.00, 6.00, 'stylos.jpg', 2, NOW(), NOW()),
(6, 'PAPIER001', 'Papier A4 (500 feuilles)', 8.00, 20.00, 9.60, 'papier-a4.jpg', 2, NOW(), NOW()),
(7, 'TAB001', 'Tablette Samsung', 300.00, 20.00, 360.00, 'tablette-samsung.jpg', 4, NOW(), NOW()),
(8, 'LIVRE001', 'Guide Spring Boot', 25.00, 5.50, 26.38, 'livre-spring.jpg', 5, NOW(), NOW());

-- Insertion des utilisateurs d'exemple (si la table existe)
-- Note: Les mots de passe sont hashés avec BCrypt
INSERT INTO utilisateur (id, nom, prenom, email, mot_de_passe, photo, date_de_naissance, creation_date, last_modified_date) VALUES
(1, 'Admin', 'System', 'admin@gestiondestock.com', '$2a$10$rDmFN6ZqJ9K8vX7Y2L3M4N5O6P7Q8R9S0T1U2V3W4X5Y6Z7A8B9C0D1E2F3', 'admin.jpg', '1990-01-01', NOW(), NOW()),
(2, 'Dupont', 'Jean', 'jean.dupont@example.com', '$2a$10$rDmFN6ZqJ9K8vX7Y2L3M4N5O6P7Q8R9S0T1U2V3W4X5Y6Z7A8B9C0D1E2F3', 'jean.jpg', '1985-05-15', NOW(), NOW()),
(3, 'Martin', 'Marie', 'marie.martin@example.com', '$2a$10$rDmFN6ZqJ9K8vX7Y2L3M4N5O6P7Q8R9S0T1U2V3W4X5Y6Z7A8B9C0D1E2F3', 'marie.jpg', '1992-08-20', NOW(), NOW());

-- Insertion des adresses d'exemple
INSERT INTO adresse (id, adresse1, adresse2, ville, code_postale, pays, creation_date, last_modified_date) VALUES
(1, '123 Rue de la Paix', 'Bâtiment A', 'Paris', '75001', 'France', NOW(), NOW()),
(2, '456 Avenue des Champs', '', 'Lyon', '69001', 'France', NOW(), NOW()),
(3, '789 Boulevard Central', 'Étage 3', 'Marseille', '13001', 'France', NOW(), NOW());

-- Insertion des clients d'exemple
INSERT INTO client (id, nom, prenom, mail, num_tel, creation_date, last_modified_date) VALUES
(1, 'Durand', 'Pierre', 'pierre.durand@client.com', '0123456789', NOW(), NOW()),
(2, 'Leroy', 'Sophie', 'sophie.leroy@client.com', '0987654321', NOW(), NOW()),
(3, 'Moreau', 'Paul', 'paul.moreau@client.com', '0555666777', NOW(), NOW());

-- Insertion des fournisseurs d'exemple
INSERT INTO fournisseur (id, nom, prenom, mail, num_tel, creation_date, last_modified_date) VALUES
(1, 'TechSupply', 'SARL', 'contact@techsupply.com', '0123456789', NOW(), NOW()),
(2, 'OfficePro', 'SAS', 'info@officepro.com', '0987654321', NOW(), NOW()),
(3, 'MobilierPlus', 'EURL', 'contact@mobilierplus.com', '0555666777', NOW(), NOW());

-- Insertion des commandes clients d'exemple
INSERT INTO commande_client (id, code, date_commande, statut, client_id, creation_date, last_modified_date) VALUES
(1, 'CMD-CLI-001', '2024-01-15 10:30:00', 'EN_PREPARATION', 1, NOW(), NOW()),
(2, 'CMD-CLI-002', '2024-01-16 14:20:00', 'LIVREE', 2, NOW(), NOW()),
(3, 'CMD-CLI-003', '2024-01-17 09:15:00', 'EN_COURS', 3, NOW(), NOW());

-- Insertion des commandes fournisseurs d'exemple
INSERT INTO commande_fournisseur (id, code, date_commande, statut, fournisseur_id, creation_date, last_modified_date) VALUES
(1, 'CMD-FOUR-001', '2024-01-10 11:00:00', 'RECUE', 1, NOW(), NOW()),
(2, 'CMD-FOUR-002', '2024-01-12 16:45:00', 'EN_PREPARATION', 2, NOW(), NOW()),
(3, 'CMD-FOUR-003', '2024-01-14 08:30:00', 'COMMANDEE', 3, NOW(), NOW());

-- Insertion des mouvements de stock d'exemple
INSERT INTO mouvement_stock (id, article_id, date_mouvement, quantite, type_mouvement, source_mouvement, creation_date, last_modified_date) VALUES
(1, 1, '2024-01-15 10:00:00', 5.00, 'ENTREE', 'Commande fournisseur', NOW(), NOW()),
(2, 2, '2024-01-15 10:30:00', 3.00, 'SORTIE', 'Commande client', NOW(), NOW()),
(3, 3, '2024-01-16 09:00:00', 10.00, 'ENTREE', 'Commande fournisseur', NOW(), NOW()),
(4, 4, '2024-01-16 14:00:00', 2.00, 'SORTIE', 'Commande client', NOW(), NOW()),
(5, 5, '2024-01-17 08:00:00', 50.00, 'ENTREE', 'Commande fournisseur', NOW(), NOW());

-- =====================================================
-- FIN DES DONNÉES D'EXEMPLE
-- ===================================================== 