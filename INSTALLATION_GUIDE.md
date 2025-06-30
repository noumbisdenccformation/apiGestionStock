# Guide d'Installation Rapide - API Gestion de Stock

## Prérequis

- Java 21 ou supérieur
- MySQL 8.0 ou supérieur
- Maven 3.6 ou supérieur

## Installation en 5 étapes

### 1. Cloner le projet
```bash
git clone <url-du-repo>
cd gestiondestock
```

### 2. Configurer MySQL
```sql
-- Se connecter à MySQL
mysql -u root -p

-- Créer la base de données
CREATE DATABASE gestiondestock_db;

-- Créer un utilisateur (optionnel)
CREATE USER 'gestiondestock'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON gestiondestock_db.* TO 'gestiondestock'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configurer l'application
Éditer `src/main/resources/application.properties` :
```properties
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe_mysql
```

### 4. Compiler et exécuter
```bash
# Compiler
mvn clean compile

# Exécuter
mvn spring-boot:run
```

### 5. Tester l'API
L'application sera accessible sur : `http://localhost:8081`

## Tests rapides

### 1. Inscription d'un utilisateur
```bash
curl -X POST http://localhost:8081/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Test",
    "prenom": "User",
    "email": "test@example.com",
    "motDePasse": "password123"
  }'
```

### 2. Connexion
```bash
curl -X POST http://localhost:8081/api/v1/auth/authenticate \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123"
  }'
```

### 3. Récupérer les articles (avec le token obtenu)
```bash
curl -X GET http://localhost:8081/api/v1/articles \
  -H "Authorization: Bearer VOTRE_TOKEN_JWT"
```

## Données de test

L'application inclut des données de test automatiquement chargées :
- 5 catégories (Électronique, Informatique, Mobilier, Vêtements, Livres)
- 5 articles avec prix et descriptions
- 2 utilisateurs de test

## Utilisateurs de test

- **Admin** : admin@nccformation.com / password123
- **Utilisateur** : john.doe@example.com / password123

## Dépannage

### Erreur de connexion MySQL
- Vérifiez que MySQL est démarré
- Vérifiez les identifiants dans `application.properties`
- Vérifiez que la base de données existe

### Port déjà utilisé
- Changez le port dans `application.properties` : `server.port=8082`

### Erreur de compilation
- Vérifiez que Java 21 est installé : `java -version`
- Vérifiez que Maven est installé : `mvn -version`

## Support

Pour plus d'informations, consultez :
- `README.md` - Documentation complète
- `API_DOCUMENTATION.md` - Documentation de l'API
- `Gestion_Stock_API.postman_collection.json` - Collection Postman 