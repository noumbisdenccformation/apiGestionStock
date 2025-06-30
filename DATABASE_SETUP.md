# Configuration de la Base de Données - API Gestion de Stock

## 📋 Prérequis

- MySQL 8.0 ou supérieur
- Privilèges root MySQL pour créer la base de données et les utilisateurs

## 🚀 Installation Automatique

### Option 1: Script d'installation automatique

1. **Rendre le script exécutable :**
   ```bash
   chmod +x setup-database.sh
   ```

2. **Exécuter le script :**
   ```bash
   ./setup-database.sh
   ```

3. **Suivre les instructions à l'écran :**
   - Entrer les identifiants MySQL root
   - Le script créera automatiquement :
     - La base de données `gestiondestock_db`
     - L'utilisateur `gestiondestock`
     - Les tables et contraintes
     - Les données d'exemple

## 🔧 Installation Manuelle

### Option 2: Installation manuelle

1. **Se connecter à MySQL :**
   ```bash
   mysql -u root -p
   ```

2. **Créer la base de données :**
   ```sql
   CREATE DATABASE gestiondestock_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **Créer l'utilisateur :**
   ```sql
   CREATE USER 'gestiondestock'@'localhost' IDENTIFIED BY 'gestiondestock123';
   GRANT ALL PRIVILEGES ON gestiondestock_db.* TO 'gestiondestock'@'localhost';
   FLUSH PRIVILEGES;
   ```

4. **Importer le schéma :**
   ```bash
   mysql -u gestiondestock -p'gestiondestock123' gestiondestock_db < database-schema.sql
   ```

5. **Importer les données d'exemple :**
   ```bash
   mysql -u gestiondestock -p'gestiondestock123' gestiondestock_db < database-sample-data.sql
   ```

## 📁 Fichiers SQL

### `database-schema.sql`
- Contient la structure complète de la base de données
- Tables, contraintes, index, triggers, etc.
- Généré automatiquement depuis votre base existante

### `database-sample-data.sql`
- Données d'exemple pour tester l'API
- Catégories, articles, clients, fournisseurs, etc.
- Permet de tester immédiatement les endpoints

## ⚙️ Configuration de l'Application

### Option 1: Utiliser le profil database
Ajoutez dans `application.properties` :
```properties
spring.profiles.active=database
```

### Option 2: Configuration directe
Ajoutez dans `application.properties` :
```properties
# Configuration de la base de données
spring.datasource.url=jdbc:mysql://localhost:3306/gestiondestock_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=gestiondestock
spring.datasource.password=gestiondestock123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuration JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## 🧪 Test de la Configuration

1. **Démarrer l'application :**
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Accéder à Swagger UI :**
   ```
   http://localhost:8080/swagger-ui.html
   ```

3. **Tester les endpoints :**
   - GET `/api/categories` - Liste des catégories
   - GET `/api/articles` - Liste des articles
   - POST `/api/categories` - Créer une catégorie
   - POST `/api/articles` - Créer un article

## 📊 Données d'Exemple Incluses

### Catégories
- Informatique (INFO)
- Matériel de bureau (BUREAU)
- Mobilier (MOBILIER)
- Électronique (ELECTRONIQUE)
- Livres et documentation (LIVRES)

### Articles
- Ordinateurs portables et de bureau
- Tables et chaises de bureau
- Fournitures de bureau
- Tablettes et équipements électroniques
- Livres techniques

### Clients et Fournisseurs
- Exemples de clients avec coordonnées
- Exemples de fournisseurs avec contacts

### Commandes
- Commandes clients et fournisseurs d'exemple
- Différents statuts de commande

### Mouvements de Stock
- Entrées et sorties de stock d'exemple
- Historique des mouvements

## 🔍 Vérification

Pour vérifier que tout fonctionne :

```bash
# Se connecter à la base de données
mysql -u gestiondestock -p'gestiondestock123' gestiondestock_db

# Vérifier les tables
SHOW TABLES;

# Vérifier les données
SELECT COUNT(*) FROM category;
SELECT COUNT(*) FROM article;
SELECT COUNT(*) FROM client;
SELECT COUNT(*) FROM fournisseur;
```

## 🚨 Dépannage

### Erreur de connexion
- Vérifiez que MySQL est démarré
- Vérifiez les identifiants
- Vérifiez que l'utilisateur a les bons privilèges

### Erreur de schéma
- Vérifiez que le fichier `database-schema.sql` existe
- Vérifiez les permissions sur le fichier

### Erreur de données
- Les erreurs d'insertion de données d'exemple sont normales si les tables n'existent pas encore
- L'application créera automatiquement les tables au démarrage

## 📝 Notes Importantes

- **Sécurité :** Changez le mot de passe par défaut en production
- **Sauvegarde :** Faites des sauvegardes régulières de votre base de données
- **Performance :** Ajustez les paramètres de connexion selon vos besoins
- **Migration :** Utilisez Flyway ou Liquibase pour les migrations en production 