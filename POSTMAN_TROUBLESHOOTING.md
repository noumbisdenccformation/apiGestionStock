# Guide de dépannage Postman - API Gestion de Stock

## Problème : Postman ne retourne rien alors que curl fonctionne

### ✅ Vérifications préliminaires

1. **L'API fonctionne avec curl** ✅
   ```bash
   curl -v http://localhost:8081/api/v1/articles
   ```

2. **Configuration CORS active** ✅
   - Headers CORS présents dans la réponse
   - Endpoint OPTIONS fonctionne

### 🔧 Solutions pour Postman

#### 1. Vérifiez la configuration de la requête Postman

**URL :** `http://localhost:8081/api/v1/articles`
**Méthode :** `GET`

#### 2. Headers recommandés

Dans l'onglet "Headers" de Postman, ajoutez :

```
Accept: application/json
Content-Type: application/json
```

#### 3. Configuration des paramètres Postman

**Onglet "Params" :** Laissez vide
**Onglet "Body" :** Laissez vide (pour GET)

#### 4. Vérifiez les paramètres de requête

- **Timeout :** Augmentez à 30 secondes
- **SSL Verification :** Désactivé (pour localhost)
- **Follow redirects :** Activé

#### 5. Test avec différents User-Agent

Ajoutez ce header :
```
User-Agent: PostmanRuntime/7.32.3
```

#### 6. Vérifiez les paramètres réseau Postman

1. Ouvrez **Settings** (⚙️) dans Postman
2. Allez dans **General**
3. Vérifiez :
   - **Request timeout :** 30000ms
   - **Max response size :** 50MB
   - **Send no-cache header :** Activé
   - **Send Postman token header :** Activé

#### 7. Test avec une nouvelle collection

1. Créez une nouvelle collection
2. Ajoutez une nouvelle requête
3. Configurez :
   - URL : `http://localhost:8081/api/v1/articles`
   - Méthode : `GET`
   - Headers : `Accept: application/json`

#### 8. Vérifiez les logs de l'application

Si Postman ne fonctionne toujours pas, vérifiez les logs de l'application Spring Boot pour voir si la requête arrive :

```bash
# Dans les logs de l'application, cherchez :
# - "GET /api/v1/articles"
# - Erreurs CORS
# - Erreurs de sécurité
```

#### 9. Test avec un autre client HTTP

Essayez avec un autre client pour confirmer que le problème est spécifique à Postman :

```bash
# Avec wget
wget -qO- http://localhost:8081/api/v1/articles

# Avec httpie (si installé)
http GET localhost:8081/api/v1/articles
```

#### 10. Configuration alternative Postman

Si rien ne fonctionne, essayez cette configuration complète :

**Headers :**
```
Accept: application/json
Content-Type: application/json
Cache-Control: no-cache
User-Agent: PostmanRuntime/7.32.3
```

**Settings Postman :**
- Désactivez le proxy
- Désactivez SSL verification
- Timeout : 60 secondes

### 🚨 Problèmes courants

1. **Cache Postman :** Videz le cache (Ctrl+Shift+R)
2. **Version Postman :** Mettez à jour vers la dernière version
3. **Conflit de port :** Vérifiez qu'aucune autre application n'utilise le port 8081
4. **Firewall :** Vérifiez que le firewall n'empêche pas Postman

### 📋 Checklist de diagnostic

- [ ] L'application Spring Boot fonctionne
- [ ] curl retourne des données
- [ ] URL correcte dans Postman
- [ ] Méthode HTTP correcte
- [ ] Headers appropriés
- [ ] Pas d'erreurs dans les logs
- [ ] Postman à jour
- [ ] Pas de conflit de port

### 🔍 Debug avancé

Si le problème persiste, activez le debug dans l'application :

```properties
# Dans application.properties
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.security=DEBUG
```

Puis vérifiez les logs pour voir si les requêtes Postman arrivent jusqu'à l'application. 