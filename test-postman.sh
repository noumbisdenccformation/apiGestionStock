#!/bin/bash

echo "=== Test de l'API avec différentes méthodes ==="
echo ""

echo "1. Test GET /api/v1/articles (sans headers spéciaux):"
curl -v http://localhost:8081/api/v1/articles
echo ""
echo ""

echo "2. Test GET /api/v1/articles (avec Accept: application/json):"
curl -v -H "Accept: application/json" http://localhost:8081/api/v1/articles
echo ""
echo ""

echo "3. Test GET /api/v1/articles (avec Content-Type: application/json):"
curl -v -H "Content-Type: application/json" http://localhost:8081/api/v1/articles
echo ""
echo ""

echo "4. Test OPTIONS /api/v1/articles (pour CORS):"
curl -v -X OPTIONS http://localhost:8081/api/v1/articles
echo ""
echo ""

echo "5. Test GET /api/v1/categories:"
curl -v http://localhost:8081/api/v1/categories
echo ""
echo ""

echo "6. Test de l'endpoint de santé:"
curl -v http://localhost:8081/actuator/health 2>/dev/null || echo "Endpoint /actuator/health non disponible"
echo ""
echo ""

echo "=== Tests terminés ===" 