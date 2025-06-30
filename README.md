# API Gestion de Stock - Spring Boot

Une API REST complète pour la gestion de stock développée avec Spring Boot, MySQL et Swagger UI.

## 📋 Fonctionnalités

- ✅ Gestion des articles et catégories
- ✅ Gestion des clients et fournisseurs
- ✅ Gestion des commandes clients et fournisseurs
- ✅ Gestion des mouvements de stock
- ✅ API REST documentée avec Swagger
- ✅ Authentification JWT (désactivée pour les tests)
- ✅ Base de données MySQL avec données d'exemple

## 🚀 Installation Rapide

### 1. Installation de la Base de Données

#### Option A: Installation Automatique (Recommandée)
```bash
# Rendre le script exécutable
chmod +x setup-database.sh

# Exécuter le script d'installation
./setup-database.sh
```

#### Option B: Installation Manuelle
Suivez les instructions détaillées dans [DATABASE_SETUP.md](DATABASE_SETUP.md)

### 2. Démarrage de l'Application
```bash
# Cloner le projet
git clone <votre-repo-url>
cd gestiondestock

# Démarrer l'application
./mvnw spring-boot:run
```

### 3. Accès à l'API
- **Swagger UI :** http://localhost:8080/swagger-ui.html
- **API Base URL :** http://localhost:8080/api

## 📁 Fichiers de Base de Données

- `database-schema.sql` - Structure complète de la base de données
- `database-sample-data.sql` - Données d'exemple pour les tests
- `setup-database.sh` - Script d'installation automatique
- `DATABASE_SETUP.md` - Documentation complète de l'installation

## Description
API REST pour la gestion de stock développée avec Spring Boot. Cette API permet de gérer les articles, catégories, commandes clients/fournisseurs et mouvements de stock.

## Configuration actuelle
- **Authentification** : Désactivée pour faciliter les tests
- **Base de données** : MySQL
- **Port** : 8081
- **Documentation API** : Swagger UI disponible

## Démarrage rapide

### Prérequis
- Java 17+
- MySQL 8.0+
- Maven

### Installation
1. Cloner le projet
2. Configurer la base de données MySQL
3. Lancer l'application :
```bash
./mvnw spring-boot:run
```

### Accès à l'API
- **Application** : http://localhost:8081
- **Swagger UI** : http://localhost:8081/swagger-ui.html
- **Documentation API** : http://localhost:8081/v3/api-docs

## Endpoints principaux

### Catégories
- `GET /api/v1/categories` - Liste des catégories
- `POST /api/v1/categories` - Créer une catégorie
- `GET /api/v1/categories/{id}` - Détail d'une catégorie
- `PUT /api/v1/categories/{id}` - Modifier une catégorie
- `DELETE /api/v1/categories/{id}` - Supprimer une catégorie

### Articles
- `GET /api/articles` - Liste des articles
- `POST /api/articles` - Créer un article
- `GET /api/articles/{id}` - Détail d'un article
- `PUT /api/articles/{id}` - Modifier un article
- `DELETE /api/articles/{id}` - Supprimer un article

### Authentification (désactivée)
- `POST /api/v1/auth/register` - Inscription utilisateur
- `POST /api/v1/auth/authenticate` - Connexion utilisateur

## Tests via Swagger

1. Ouvrir http://localhost:8081/swagger-ui.html
2. Tester les endpoints dans cet ordre :
   - Créer une catégorie (POST /api/v1/categories)
   - Créer un article (POST /api/articles)
   - Lister les catégories (GET /api/v1/categories)
   - Lister les articles (GET /api/articles)

## Format des données

### Créer une catégorie
```json
{
  "code": "INFO",
  "designation": "Informatique"
}
```

### Créer un article
```json
{
  "codeArticle": "TB001",
  "designation": "Table bureau",
  "prixUnitaireHt": 25000,
  "tauxTva": 0,
  "prixUnitaireTtc": 25000,
  "photo": "",
  "category": {
    "id": 1
  }
}
```

## Configuration de la base de données

Le fichier `application-mysql.properties` contient la configuration de connexion à MySQL :
- Base : `gestiondestock_db`
- Utilisateur : `gestiondestock`
- Mot de passe : `gestiondestock123`

## Sécurité

**Note importante** : L'authentification est actuellement désactivée pour faciliter les tests. Pour la production, il faudra réactiver la sécurité Spring Security.

## Build et déploiement

```bash
# Build du projet
./mvnw clean package

# Exécution du jar
java -jar target/gestiondestock-*.jar
```

## Support

Pour toute question ou problème, consulter la documentation Swagger ou les logs de l'application. 