# Diagramme UML - API Gestion de Stock

## 📊 Diagramme de Classes

```mermaid
classDiagram
    %% Classe abstraite de base
    class AbstractEntity {
        +Integer id
        +LocalDateTime creationDate
        +LocalDateTime lastModifiedDate
    }

    %% Entités principales
    class Category {
        +String code
        +String designation
    }

    class Article {
        +String codeArticle
        +String designation
        +BigDecimal prixUnitaireHt
        +BigDecimal tauxTva
        +BigDecimal prixUnitaireTtc
        +String photo
    }

    class Client {
        +String nom
        +String prenom
        +String mail
        +String numTel
    }

    class Fournisseur {
        +String nom
        +String prenom
        +String mail
        +String numTel
    }

    class Utilisateur {
        +String nom
        +String prenom
        +String email
        +String motDePasse
        +String photo
        +LocalDate dateDeNaissance
    }

    class Adresse {
        +String adresse1
        +String adresse2
        +String ville
        +String codePostale
        +String pays
    }

    %% Entités de commande
    class CommandeClient {
        +String code
        +LocalDateTime dateCommande
        +StatutCommande statut
    }

    class CommandeFournisseur {
        +String code
        +LocalDateTime dateCommande
        +StatutCommande statut
    }

    class LigneCommandeClient {
        +BigDecimal quantite
        +BigDecimal prixUnitaire
    }

    class LigneCommandeFournisseur {
        +BigDecimal quantite
        +BigDecimal prixUnitaire
    }

    %% Entités de stock
    class MouvementStock {
        +LocalDateTime dateMouvement
        +BigDecimal quantite
        +TypeMouvementStock typeMouvement
        +String sourceMouvement
    }

    class Ventes {
        +String code
        +LocalDateTime dateVente
        +String commentaire
    }

    class LigneVente {
        +BigDecimal quantite
        +BigDecimal prixUnitaire
    }

    %% Entités supplémentaires
    class Entreprise {
        +String nom
        +String description
        +String codeFiscal
        +String photo
        +String email
        +String numTel
        +String siteWeb
    }

    class Roles {
        +String roleName
    }

    %% Énumérations
    class StatutCommande {
        <<enumeration>>
        EN_PREPARATION
        COMMANDEE
        LIVREE
        EN_COURS
        RECUE
    }

    class TypeMouvementStock {
        <<enumeration>>
        ENTREE
        SORTIE
        CORRECTION_POS
        CORRECTION_NEG
    }

    %% Relations d'héritage
    AbstractEntity <|-- Category
    AbstractEntity <|-- Article
    AbstractEntity <|-- Client
    AbstractEntity <|-- Fournisseur
    AbstractEntity <|-- Utilisateur
    AbstractEntity <|-- Adresse
    AbstractEntity <|-- CommandeClient
    AbstractEntity <|-- CommandeFournisseur
    AbstractEntity <|-- LigneCommandeClient
    AbstractEntity <|-- LigneCommandeFournisseur
    AbstractEntity <|-- MouvementStock
    AbstractEntity <|-- Ventes
    AbstractEntity <|-- LigneVente
    AbstractEntity <|-- Entreprise
    AbstractEntity <|-- Roles

    %% Relations entre entités
    Article ||--o{ Category : "appartient à"
    
    CommandeClient ||--o{ Client : "appartient à"
    CommandeClient ||--o{ LigneCommandeClient : "contient"
    LigneCommandeClient ||--o{ Article : "réfère"
    
    CommandeFournisseur ||--o{ Fournisseur : "appartient à"
    CommandeFournisseur ||--o{ LigneCommandeFournisseur : "contient"
    LigneCommandeFournisseur ||--o{ Article : "réfère"
    
    MouvementStock ||--o{ Article : "concerne"
    
    Ventes ||--o{ LigneVente : "contient"
    LigneVente ||--o{ Article : "réfère"
    
    Utilisateur ||--o{ Adresse : "a"
    Utilisateur ||--o{ Entreprise : "appartient à"
    Utilisateur ||--o{ Roles : "a"
    
    Client ||--o{ Adresse : "a"
    Fournisseur ||--o{ Adresse : "a"
    
    Entreprise ||--o{ Adresse : "a"
    
    CommandeClient ||--o{ StatutCommande : "a"
    CommandeFournisseur ||--o{ StatutCommande : "a"
    
    MouvementStock ||--o{ TypeMouvementStock : "est de type"
```

## 📋 Description des Entités

### 🏢 **Entités Principales**

#### **AbstractEntity**
- Classe abstraite contenant les champs communs à toutes les entités
- `id` : Identifiant unique auto-généré
- `creationDate` : Date de création de l'entité
- `lastModifiedDate` : Date de dernière modification

#### **Category (Catégorie)**
- Gestion des catégories d'articles
- `code` : Code unique de la catégorie (ex: INFO, BUREAU)
- `designation` : Nom de la catégorie (ex: Informatique, Matériel de bureau)

#### **Article**
- Articles du stock avec gestion des prix
- `codeArticle` : Code unique de l'article
- `designation` : Description de l'article
- `prixUnitaireHt` : Prix hors taxes
- `tauxTva` : Taux de TVA applicable
- `prixUnitaireTtc` : Prix toutes taxes comprises
- `photo` : Chemin vers l'image de l'article

### 👥 **Entités Personnes**

#### **Client**
- Informations sur les clients
- `nom`, `prenom` : Nom et prénom du client
- `mail` : Adresse email
- `numTel` : Numéro de téléphone

#### **Fournisseur**
- Informations sur les fournisseurs
- `nom`, `prenom` : Nom et prénom/raison sociale
- `mail` : Adresse email
- `numTel` : Numéro de téléphone

#### **Utilisateur**
- Utilisateurs du système
- `nom`, `prenom` : Nom et prénom
- `email` : Adresse email (unique)
- `motDePasse` : Mot de passe hashé
- `photo` : Photo de profil
- `dateDeNaissance` : Date de naissance

### 📍 **Entités Adresse**

#### **Adresse**
- Adresses postales
- `adresse1`, `adresse2` : Lignes d'adresse
- `ville` : Ville
- `codePostale` : Code postal
- `pays` : Pays

### 📦 **Entités de Commande**

#### **CommandeClient**
- Commandes passées par les clients
- `code` : Code unique de la commande
- `dateCommande` : Date de la commande
- `statut` : Statut de la commande (enum)

#### **CommandeFournisseur**
- Commandes passées aux fournisseurs
- `code` : Code unique de la commande
- `dateCommande` : Date de la commande
- `statut` : Statut de la commande (enum)

#### **LigneCommandeClient**
- Lignes de commande client
- `quantite` : Quantité commandée
- `prixUnitaire` : Prix unitaire au moment de la commande

#### **LigneCommandeFournisseur**
- Lignes de commande fournisseur
- `quantite` : Quantité commandée
- `prixUnitaire` : Prix unitaire au moment de la commande

### 📊 **Entités de Stock**

#### **MouvementStock**
- Suivi des mouvements de stock
- `dateMouvement` : Date du mouvement
- `quantite` : Quantité déplacée
- `typeMouvement` : Type de mouvement (enum)
- `sourceMouvement` : Source du mouvement

#### **Ventes**
- Enregistrement des ventes
- `code` : Code de la vente
- `dateVente` : Date de la vente
- `commentaire` : Commentaire optionnel

#### **LigneVente**
- Lignes de vente
- `quantite` : Quantité vendue
- `prixUnitaire` : Prix unitaire de vente

### 🏢 **Entités Supplémentaires**

#### **Entreprise**
- Informations sur l'entreprise
- `nom` : Nom de l'entreprise
- `description` : Description
- `codeFiscal` : Code fiscal
- `photo` : Logo de l'entreprise
- `email`, `numTel`, `siteWeb` : Coordonnées

#### **Roles**
- Rôles des utilisateurs
- `roleName` : Nom du rôle

## 🔄 **Relations Principales**

### **Héritage**
- Toutes les entités héritent de `AbstractEntity`

### **Relations 1-N**
- **Article** → **Category** : Un article appartient à une catégorie
- **CommandeClient** → **Client** : Une commande appartient à un client
- **CommandeFournisseur** → **Fournisseur** : Une commande appartient à un fournisseur
- **MouvementStock** → **Article** : Un mouvement concerne un article

### **Relations N-N via tables de liaison**
- **CommandeClient** ↔ **Article** (via LigneCommandeClient)
- **CommandeFournisseur** ↔ **Article** (via LigneCommandeFournisseur)
- **Ventes** ↔ **Article** (via LigneVente)

### **Relations avec Adresse**
- **Client** → **Adresse** : Un client peut avoir une adresse
- **Fournisseur** → **Adresse** : Un fournisseur peut avoir une adresse
- **Utilisateur** → **Adresse** : Un utilisateur peut avoir une adresse
- **Entreprise** → **Adresse** : Une entreprise peut avoir une adresse

## 📈 **Énumérations**

### **StatutCommande**
- `EN_PREPARATION` : Commande en cours de préparation
- `COMMANDEE` : Commande passée
- `LIVREE` : Commande livrée
- `EN_COURS` : Commande en cours de livraison
- `RECUE` : Commande reçue

### **TypeMouvementStock**
- `ENTREE` : Entrée en stock
- `SORTIE` : Sortie de stock
- `CORRECTION_POS` : Correction positive
- `CORRECTION_NEG` : Correction négative

## 🎯 **Points Clés de l'Architecture**

1. **Héritage commun** : Toutes les entités héritent d'`AbstractEntity`
2. **Gestion des prix** : Articles avec prix HT/TTC et TVA
3. **Traçabilité** : Mouvements de stock avec historique
4. **Flexibilité** : Système de rôles pour les utilisateurs
5. **Complétude** : Gestion clients/fournisseurs avec adresses
6. **Audit** : Dates de création et modification automatiques 