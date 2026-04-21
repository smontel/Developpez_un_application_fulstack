# MDD - Réseau Social Développeurs

## Prérequis

- **Node.js** v16+ et **npm**
- **Java 17**
- **Maven**
- **MySQL 8**

---

## 1. Base de données

### Créer la base et importer les données

Connecte-toi à MySQL et exécute le script SQL fourni :

```bash
mysql -u root -p < back/ressources/mdd_db.sql
```

Cela va créer la base `mdd_db`, toutes les tables, et insérer des données de test.

**Comptes de test disponibles après import :**

| Email | Mot de passe |
|---|---|
| alice@example.com | password123 |
| bob@example.com | password123 |
| charlie@example.com | password123 |

---

## 2. Backend (Spring Boot)

### Prérequis : définir les variables d'environnement

Avant de lancer le back, définis ces variables dans ton terminal :

```bash
export DB_PASSWORD=root
export JWT_SECRET=une_clé_secrète_longue_et_aléatoire
```

> Sur Windows (PowerShell) :
> ```powershell
> $env:DB_PASSWORD="root"
> $env:JWT_SECRET="une_clé_secrète_longue_et_aléatoire"
> ```

**Description des variables :**

| Variable | Description | Valeur par défaut |
|---|---|---|
| `DB_PASSWORD` | Mot de passe MySQL | aucune (obligatoire) |
| `JWT_SECRET` | Clé secrète pour signer les tokens JWT | aucune (obligatoire) |
| `DB_URL` | URL de la base de données | `jdbc:mysql://localhost:3306/mdd_db` |
| `DB_USERNAME` | Nom d'utilisateur MySQL | `root` |

### Lancer le backend

```bash
cd back
mvn spring-boot:run
```

L'API est disponible sur : **http://localhost:3001/api**

La documentation Swagger est accessible sur : **http://localhost:3001/api/swagger-ui/index.html**

---

## 3. Frontend (Angular)

### Installer les dépendances

```bash
cd front
npm install
```

### Lancer le frontend

```bash
ng serve
```

L'application est disponible sur : **http://localhost:4200**

---

## Récapitulatif — lancer le projet complet

```bash
# Terminal 1 — Backend
cd back
export DB_PASSWORD=root
export JWT_SECRET=une_clé_secrète_longue_et_aléatoire
mvn spring-boot:run

# Terminal 2 — Frontend
cd front
npm install
ng serve
```