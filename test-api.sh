#!/bin/bash

echo "=== Test de l'API Gestion de Stock ==="
echo "Assurez-vous que l'application est démarrée sur le port 8081"
echo ""

BASE_URL="http://localhost:8081"

# Test 1: Vérifier que l'application répond
echo "1. Test de connectivité..."
if curl -s "$BASE_URL" > /dev/null; then
    echo "✅ Application accessible"
else
    echo "❌ Application non accessible"
    exit 1
fi

# Test 2: Créer une catégorie
echo ""
echo "2. Test création de catégorie..."
CATEGORY_RESPONSE=$(curl -s -X POST "$BASE_URL/api/v1/categories" \
  -H "Content-Type: application/json" \
  -d '{
    "code": "TEST",
    "designation": "Catégorie de test"
  }')

if echo "$CATEGORY_RESPONSE" | grep -q "id"; then
    echo "✅ Catégorie créée avec succès"
    CATEGORY_ID=$(echo "$CATEGORY_RESPONSE" | grep -o '"id":[0-9]*' | cut -d':' -f2)
    echo "   ID de la catégorie: $CATEGORY_ID"
else
    echo "❌ Erreur lors de la création de la catégorie"
    echo "   Réponse: $CATEGORY_RESPONSE"
fi

# Test 3: Lister les catégories
echo ""
echo "3. Test liste des catégories..."
CATEGORIES_RESPONSE=$(curl -s "$BASE_URL/api/v1/categories")
if echo "$CATEGORIES_RESPONSE" | grep -q "id"; then
    echo "✅ Liste des catégories récupérée"
    echo "   Nombre de catégories: $(echo "$CATEGORIES_RESPONSE" | grep -o '"id"' | wc -l)"
else
    echo "❌ Erreur lors de la récupération des catégories"
fi

# Test 4: Créer un article (si une catégorie existe)
if [ ! -z "$CATEGORY_ID" ]; then
    echo ""
    echo "4. Test création d'article..."
    ARTICLE_RESPONSE=$(curl -s -X POST "$BASE_URL/api/articles" \
      -H "Content-Type: application/json" \
      -d "{
        \"codeArticle\": \"ART001\",
        \"designation\": \"Article de test\",
        \"prixUnitaireHt\": 1000,
        \"tauxTva\": 0,
        \"prixUnitaireTtc\": 1000,
        \"photo\": \"\",
        \"category\": {
          \"id\": $CATEGORY_ID
        }
      }")

    if echo "$ARTICLE_RESPONSE" | grep -q "id"; then
        echo "✅ Article créé avec succès"
        ARTICLE_ID=$(echo "$ARTICLE_RESPONSE" | grep -o '"id":[0-9]*' | cut -d':' -f2)
        echo "   ID de l'article: $ARTICLE_ID"
    else
        echo "❌ Erreur lors de la création de l'article"
        echo "   Réponse: $ARTICLE_RESPONSE"
    fi
else
    echo ""
    echo "4. Test création d'article... (ignoré - pas de catégorie disponible)"
fi

# Test 5: Lister les articles
echo ""
echo "5. Test liste des articles..."
ARTICLES_RESPONSE=$(curl -s "$BASE_URL/api/articles")
if echo "$ARTICLES_RESPONSE" | grep -q "id"; then
    echo "✅ Liste des articles récupérée"
    echo "   Nombre d'articles: $(echo "$ARTICLES_RESPONSE" | grep -o '"id"' | wc -l)"
else
    echo "✅ Liste des articles récupérée (vide)"
fi

# Test 6: Vérifier Swagger
echo ""
echo "6. Test accès à Swagger..."
if curl -s "$BASE_URL/swagger-ui.html" | grep -q "Swagger"; then
    echo "✅ Swagger UI accessible"
else
    echo "❌ Swagger UI non accessible"
fi

echo ""
echo "=== Tests terminés ==="
echo "Pour plus de tests, utilisez Swagger UI: $BASE_URL/swagger-ui.html" 