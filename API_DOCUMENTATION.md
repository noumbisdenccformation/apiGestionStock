# Documentation API Gestion de Stock

## Vue d'ensemble

L'API de Gestion de Stock est une API REST complète développée avec Spring Boot qui permet de gérer les articles, catégories, utilisateurs et l'authentification JWT.

## Base URL

```
http://localhost:8081
```

## Authentification

L'API utilise l'authentification JWT (JSON Web Token). Pour accéder aux endpoints protégés, vous devez :

1. Vous inscrire ou vous connecter
2. Récupérer le token JWT
3. Inclure le token dans l'en-tête `Authorization: Bearer <token>`

## Endpoints

### 1. Authentification

#### Inscription d'un utilisateur
```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "nom": "Doe",
  "prenom": "John",
  "email": "john.doe@example.com",
  "motDePasse": "password123",
  "adresse": {
    "adresse1": "123 Rue de la Paix",
    "ville": "Paris",
    "codePostale": "75001",
    "pays": "France"
  }
}
```

**Réponse :**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "message": "User registered successfully"
}
```

#### Connexion utilisateur
```http
POST /api/v1/auth/authenticate
Content-Type: application/json

{
  "email": "john.doe@example.com",
  "password": "password123"
}
```

**Réponse :**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "message": "User authenticated successfully"
}
```

### 2. Catégories

#### Récupérer toutes les catégories
```http
GET /api/v1/categories
Authorization: Bearer <token>
```

#### Récupérer une catégorie par ID
```http
GET /api/v1/categories/{id}
Authorization: Bearer <token>
```

#### Récupérer une catégorie par code
```http
GET /api/v1/categories/code/{code}
Authorization: Bearer <token>
```

#### Créer une nouvelle catégorie
```http
POST /api/v1/categories
Authorization: Bearer <token>
Content-Type: application/json

{
  "code": "CAT006",
  "designation": "Nouvelle catégorie"
}
```

#### Supprimer une catégorie
```http
DELETE /api/v1/categories/{id}
Authorization: Bearer <token>
```

### 3. Articles

#### Récupérer tous les articles
```http
GET /api/v1/articles
Authorization: Bearer <token>
```

#### Récupérer un article par ID
```http
GET /api/v1/articles/{id}
Authorization: Bearer <token>
```

#### Récupérer un article par code
```http
GET /api/v1/articles/code/{codeArticle}
Authorization: Bearer <token>
```

#### Récupérer les articles d'une catégorie
```http
GET /api/v1/articles/category/{categoryId}
Authorization: Bearer <token>
```

#### Rechercher des articles par désignation
```http
GET /api/v1/articles/search?designation=ordinateur
Authorization: Bearer <token>
```

#### Créer un nouvel article
```http
POST /api/v1/articles
Authorization: Bearer <token>
Content-Type: application/json

{
  "codeArticle": "ART006",
  "designation": "Nouveau produit",
  "prixUnitaireHt": 99.99,
  "tauxTva": 20.0,
  "prixUnitaireTtc": 119.99,
  "photo": "product.jpg",
  "category": {
    "id": 1
  }
}
```

#### Supprimer un article
```http
DELETE /api/v1/articles/{id}
Authorization: Bearer <token>
```

## Codes de statut HTTP

- `200 OK` : Requête réussie
- `201 Created` : Ressource créée avec succès
- `400 Bad Request` : Requête malformée
- `401 Unauthorized` : Authentification requise
- `403 Forbidden` : Accès interdit
- `404 Not Found` : Ressource non trouvée
- `500 Internal Server Error` : Erreur serveur

## Exemples d'utilisation avec cURL

### 1. Inscription
```bash
curl -X POST http://localhost:8081/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Doe",
    "prenom": "John",
    "email": "john.doe@example.com",
    "motDePasse": "password123",
    "adresse": {
      "adresse1": "123 Rue de la Paix",
      "ville": "Paris",
      "codePostale": "75001",
      "pays": "France"
    }
  }'
```

### 2. Connexion
```bash
curl -X POST http://localhost:8081/api/v1/auth/authenticate \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "password123"
  }'
```

### 3. Récupérer tous les articles (avec token)
```bash
curl -X GET http://localhost:8081/api/v1/articles \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

### 4. Créer un article
```bash
curl -X POST http://localhost:8081/api/v1/articles \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE" \
  -d '{
    "codeArticle": "ART007",
    "designation": "Écran 24 pouces",
    "prixUnitaireHt": 199.99,
    "tauxTva": 20.0,
    "prixUnitaireTtc": 239.99,
    "category": {
      "id": 1
    }
  }'
```

## Gestion des erreurs

L'API retourne des messages d'erreur détaillés en cas de problème :

```json
{
  "timestamp": "2024-01-15T10:30:00.000+00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/v1/articles"
}
```

## Sécurité

- Tous les endpoints (sauf authentification) nécessitent un token JWT valide
- Les mots de passe sont chiffrés avec BCrypt
- Les tokens JWT expirent après 24 heures
- Protection CSRF désactivée pour l'API REST

## Limites et bonnes pratiques

- Utilisez toujours l'en-tête `Content-Type: application/json` pour les requêtes POST/PUT
- Incluez le token JWT dans l'en-tête `Authorization: Bearer <token>`
- Gérez les erreurs côté client
- Validez les données avant envoi
- Utilisez HTTPS en production

## Support

Pour toute question ou problème, consultez le README.md du projet ou ouvrez une issue sur GitHub. 