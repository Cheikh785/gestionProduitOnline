# API REST Documentation

## Introduction

Cette API REST, développée avec Spring Boot, est conçue pour gérer un système de produits en ligne. Les utilisateurs peuvent consulter des produits, ajouter des commentaires, noter les produits et gérer leurs informations personnelles.

Prérequis

Java : Version 17 

Maven : Version 3.8 

Base de données : MySQL 


## Lancement de l'application

Clonez le projet : `git clone https://github.com/Cheikh785/gestionProduitOnline.git`

Accédez au dossier du projet : `cd gestionProduitOnline`

Configurez la base de données dans le fichier application.properties :

```
spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_la_base

spring.datasource.username=utilisateur

spring.datasource.password=mot_de_passe
```

Lancez l'application avec Maven : `mvn spring-boot:run`

## Endpoints

### Gestion des utilisateurs

-  Créer un utilisateur :

```
POST /api/v1/users

Body : { "nom": "string", "email": "string", "motDePasse": "string" }

```

- Obtenir tous les utilisateurs :

```
GET /api/v1/users
```

- Obtenir un utilisateur par ID :
```
GET /api/v1/users/{id}
```

- Mettre à jour un utilisateur :
```
PUT /api/v1/users/{id}

Body : { "nom": "string", "email": "string", "motDePasse": "string" }
```

- Supprimer un utilisateur :
```
DELETE /api/v1/users/{id}
```

### Gestion des produits

- Créer un produit :
```
POST /api/v1/products

Body : { "nom": "string", "description": "string", "prix": 0.0, "quantiteStock": 0, "categorie": "string" }
```
- Obtenir tous les produits :
```
GET /api/v1/products
```
- Rechercher un produit par nom :
```
GET /api/v1/products/name/{name}
```
- Obtenir un produit par ID :
```
GET /api/v1/products/{id}
```
- Mettre à jour un produit :
```
PUT /api/v1/products/{id}

Body : { "nom": "string", "description": "string", "prix": 0.0, "quantiteStock": 0, "categorie": "string" }
```
- Supprimer un produit :
```
DELETE /api/v1/products/{id}
```
### Gestion des commentaires

- Ajouter un commentaire :
```
POST /api/v1/commentaires

Body : { "contenu": "string", "produitId": 0, "utilisateurId": 0 }
```
- Obtenir les commentaires d'un produit :
```
GET /api/v1/commentaires/product/{productId}
```
- Supprimer un commentaire (par son auteur) :
```
DELETE /api/v1/commentaires/{commentaireId}/{userId}
```
### Gestion des notes

- Ajouter une note à un produit :
```
POST /api/v1/ratings

Body : { "score": 1, "produitId": 0, "utilisateurId": 0 }
```
- Nombre de produits par catégorie :
```
GET /api/v1/ratings/category/{category}/count
```
- Moyenne des notes pour un produit :
```
GET /api/v1/ratings/product/{produitId}/average
```
# Auteurs

Cheikh Oumar Ba

cheikhomar.cof@gmail.com