#!/bin/bash

# =====================================================
# SCRIPT D'INSTALLATION DE LA BASE DE DONNÉES
# API GESTION DE STOCK
# =====================================================

echo "====================================================="
echo "INSTALLATION DE LA BASE DE DONNÉES GESTION DE STOCK"
echo "====================================================="

# Configuration
DB_NAME="gestiondestock_db"
DB_USER="gestiondestock"
DB_PASSWORD="gestiondestock123"
DB_HOST="localhost"

# Couleurs pour les messages
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Fonction pour afficher les messages
print_message() {
    echo -e "${GREEN}[INFO]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Vérifier si MySQL est installé
if ! command -v mysql &> /dev/null; then
    print_error "MySQL n'est pas installé. Veuillez l'installer d'abord."
    exit 1
fi

print_message "MySQL est installé."

# Vérifier si les fichiers SQL existent
if [ ! -f "database-schema.sql" ]; then
    print_error "Le fichier database-schema.sql n'existe pas."
    exit 1
fi

if [ ! -f "database-sample-data.sql" ]; then
    print_error "Le fichier database-sample-data.sql n'existe pas."
    exit 1
fi

print_message "Fichiers SQL trouvés."

# Demander les informations de connexion MySQL root
echo ""
print_warning "Vous devez avoir les privilèges root MySQL pour créer la base de données."
read -p "Nom d'utilisateur MySQL root (par défaut: root): " MYSQL_ROOT_USER
MYSQL_ROOT_USER=${MYSQL_ROOT_USER:-root}

read -s -p "Mot de passe MySQL root: " MYSQL_ROOT_PASSWORD
echo ""

# Tester la connexion MySQL
print_message "Test de connexion à MySQL..."
if ! mysql -u "$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "SELECT 1;" &> /dev/null; then
    print_error "Impossible de se connecter à MySQL. Vérifiez vos identifiants."
    exit 1
fi

print_message "Connexion MySQL réussie."

# Créer la base de données si elle n'existe pas
print_message "Création de la base de données '$DB_NAME'..."
mysql -u "$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "CREATE DATABASE IF NOT EXISTS $DB_NAME CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

if [ $? -eq 0 ]; then
    print_message "Base de données créée avec succès."
else
    print_error "Erreur lors de la création de la base de données."
    exit 1
fi

# Créer l'utilisateur si il n'existe pas
print_message "Création de l'utilisateur '$DB_USER'..."
mysql -u "$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "CREATE USER IF NOT EXISTS '$DB_USER'@'$DB_HOST' IDENTIFIED BY '$DB_PASSWORD';"

if [ $? -eq 0 ]; then
    print_message "Utilisateur créé avec succès."
else
    print_error "Erreur lors de la création de l'utilisateur."
    exit 1
fi

# Accorder les privilèges
print_message "Attribution des privilèges..."
mysql -u "$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "GRANT ALL PRIVILEGES ON $DB_NAME.* TO '$DB_USER'@'$DB_HOST';"
mysql -u "$MYSQL_ROOT_USER" -p"$MYSQL_ROOT_PASSWORD" -e "FLUSH PRIVILEGES;"

if [ $? -eq 0 ]; then
    print_message "Privilèges accordés avec succès."
else
    print_error "Erreur lors de l'attribution des privilèges."
    exit 1
fi

# Importer le schéma de la base de données
print_message "Import du schéma de la base de données..."
mysql -u "$DB_USER" -p"$DB_PASSWORD" "$DB_NAME" < database-schema.sql

if [ $? -eq 0 ]; then
    print_message "Schéma importé avec succès."
else
    print_error "Erreur lors de l'import du schéma."
    exit 1
fi

# Importer les données d'exemple
print_message "Import des données d'exemple..."
mysql -u "$DB_USER" -p"$DB_PASSWORD" "$DB_NAME" < database-sample-data.sql

if [ $? -eq 0 ]; then
    print_message "Données d'exemple importées avec succès."
else
    print_warning "Erreur lors de l'import des données d'exemple (peut être normal si les tables n'existent pas encore)."
fi

# Créer le fichier de configuration
print_message "Création du fichier de configuration..."
cat > application-database.properties << EOF
# Configuration de la base de données
spring.datasource.url=jdbc:mysql://$DB_HOST:3306/$DB_NAME?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=$DB_USER
spring.datasource.password=$DB_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuration JPA/Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Configuration de la connexion
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.connection-timeout=20000
EOF

print_message "Fichier de configuration créé: application-database.properties"

echo ""
echo "====================================================="
print_message "INSTALLATION TERMINÉE AVEC SUCCÈS !"
echo "====================================================="
echo ""
print_message "Base de données: $DB_NAME"
print_message "Utilisateur: $DB_USER"
print_message "Hôte: $DB_HOST"
echo ""
print_message "Pour utiliser cette configuration, ajoutez dans votre application.properties:"
print_message "spring.profiles.active=database"
echo ""
print_message "Ou copiez le contenu de application-database.properties dans votre application.properties"
echo ""
print_message "Vous pouvez maintenant démarrer votre application Spring Boot !"
echo "=====================================================" 