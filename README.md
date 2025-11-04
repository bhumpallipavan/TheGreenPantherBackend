# 🌿 The Green Panthers - Backend API (Spring Boot + MongoDB)

The Green Panthers is a web-based gardening and nature learning platform designed to help users grow plants, purchase gardening supplies, explore garden-to-table recipes, and learn from inspiring natural agriculturists.  
This repository contains the **Spring Boot REST API backend**, connected to **MongoDB**, implementing clean, scalable, and modular services across four major application modules.

---

## 🏗️ Tech Stack

| Layer | Technology |
|------|------------|
| Backend Framework | Spring Boot (v3.x) |
| Build Tool | Maven |
| Language | Java 20 |
| Database | MongoDB Atlas / Local Mongo |
| API Protocol | REST |
| JSON Parsing | Jackson |
| Testing | Postman / cURL |

---

## 📥 How to Run the Project (Locally)

1. **Clone the repository**
   ```bash
   git clone https://github.com/bhumpallipavan/TheGreenPantherBackend.git
   ```

2. **Open the project in IDE**
   - Eclipse / IntelliJ / VS Code

3. **Ensure MongoDB is running**
   - If using Cloud MongoDB (Atlas) → update `application.properties`
   - If using local MongoDB → default port `27017` works

4. **Build using Maven**
   ```bash
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

> Backend runs on: **http://localhost:8080/**

---

## 🗂️ Project Modules Overview

| Module | Base API Path | Description |
|--------|--------------|-------------|
| Grow & Nurture | `/growandnurture` | Plant learning guides |
| E-Nursery | `/enursery` | Gardening products store |
| Garden to Table | `/gardentotable` | Recipes and DIY art |
| Articles | `/articles` | Inspirational farming stories |

---
## 📦 Sample Dataset

We have included **4 JSON dataset files** in this repository, one for each module:
- **Grow & Nurture**
- **E-Nursery**
- **Garden to Table**
- **Articles**

You can **download these files and import them directly into your MongoDB database** to instantly preload sample content for testing and development.


---

## 🆔 Custom ID Format

| Module | Prefix Examples | Meaning |
|--------|----------------|---------|
| Grow & Nurture | GNV / GNF / GNL / GNP | Category-based |
| E-Nursery | ENS / ENP / ENT / ENM / ENF / ENA | Product category-based |
| Garden to Table | GTC / GTR / GTV / GTP / GTB / GTM / GTL | Category-based|
| Articles | APN / APL | Personality or Plant knowledge |

---

## 🌱 Grow & Nurture (`/growandnurture`)

Guides for growing vegetables, fruits, flowers, leafy greens.

### 📌 API Endpoints

| Method | Endpoint | Description |
|-------|----------|-------------|
| POST | `/item` | Add one item |
| POST | `/items` | Add multiple items |
| GET | `/{id}` | Get by MongoDB ID |
| GET | `/customid/{customId}` | Get by customId |
| GET | `/search?query=` | Search in searchWords |
| GET | `/category/{category}` | Filter by category |
| GET | `/featured?n=&category=` | First n items of category |
| GET | `/featuredall?n=` | n items from each category |
| GET | `/pagination?size=&page=` | Page-based listing |
| GET | `/random?n=` | Random items |
| GET | `/searchcustom` | Light-weight projection result |
| PUT | `/update/{id}` | Update item |
| DELETE | `/delete/{id}` | Delete item |

---

## 🛒 E-Nursery (`/enursery`)

Store for Seeds, Plants, Tools, Soil Mix, Fertilizers, Accessories.

APIs are **same as Grow & Nurture** for consistency.

---

## 🍽️ Garden To Table (`/gardentotable`)

Contains recipes, DIY projects, craft videos.

Category is determined using **3rd character** of customId.

Example:
| Code | Meaning |
|------|---------|
| GTC | Cooking |
| GTR | Resin Art |
| GTB | Bouquet Art |

API structure matches Grow & Nurture.

---

## 📰 Articles (`/articles`)

Contains plant knowledge & farmer stories.

| Code | Meaning |
|------|---------|
| APN | Personalities |
| APL | Plant Knowledge |

API structure matches Grow & Nurture.

---

## 🗃️ Database Enhancements

| Feature | Description |
|--------|-------------|
| Projections | Lightweight API responses |
| Regex Search | Partial flexible matching |
| Aggregation `$sample` | Random item selection |
| Pagination using Pageable | Infinite scroll support |

---

## 👨‍💻 Author

**Bhumpalli Pavan Tirupathi Reddy**  
B.Tech CSE, GVP College of Engineering

