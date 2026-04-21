# ThousandsOfCourses

## used:
## 1) Architecture:
###       1.1) MVI;
###       1.2) Clean arch;
###       1.3) Usecase; 
###       1.4) Repository; 
###       1.5) Singl activity;
## 2) Technology 
###      2.1) DI: Hilt;
###      2.2) Database: Room;
###      2.4) Image: Coil3;
###      2.5) Retrofit
###      2.5) UI: Compose
## 3) Asynchrony
###    3.1) Coroutines;
###    3.2) Arrow;
---

![Image alt](https://github.com/PrilepskiyAE/ThousandsOfCourses/blob/master/screenshot/Screenshot_4.jpg)
![Image alt](https://github.com/PrilepskiyAE/ThousandsOfCourses/blob/master/screenshot/Screenshot_3.jpg)
![Image alt](https://github.com/PrilepskiyAE/ThousandsOfCourses/blob/master/screenshot/Screenshot_2.jpg)
![Image alt](https://github.com/PrilepskiyAE/ThousandsOfCourses/blob/master/screenshot/Screenshot_1.jpg)

## 1. `:app`

**Назначение:** главный модуль приложения — точка входа и сборка всех компонентов.


**Содержимое:**
* класс `Application` с инициализацией Hilt;
* `MainActivity` (единственная активность в Single Activity архитектуре);
* навигация (NavGraph в Compose);
* корневые Compose‑компоненты;
* настройки Hilt (модули, компоненты);
* манифест (`AndroidManifest.xml`);
* ресурсы (ресурсы, стили, темы).


**Зависимости:** зависит от всех остальных модулей — собирает их вместе.


**Ключевые технологии:**
* **Hilt** — DI;
* **Compose** — UI;
* **Navigation Component** (для Compose) — навигация.

## 2. `:common`

**Назначение:** общие утилиты и компоненты, переиспользуемые во всём проекте.

**Содержимое:**
* базовые классы для работы с `Flow`/`State` (например, `UiState`, `Resource`);
* расширения (`extensions`) для стандартных классов Kotlin/Android;
* утилиты для работы с датами, строками, коллекциями;
* базовые абстрактные классы для `ViewModel`;
* константы приложения;


**Зависимости:** не зависит от других модулей проекта — максимально автономный.

**Ключевые технологии:** Kotlin, Compose (для общих UI‑компонентов).

## 3. `:data`

**Назначение:** работа с источниками данных (локальная БД, сеть, файлы).

**Содержимое:**
* **Room DAO‑интерфейсы** — доступ к локальной БД;
* **модели Room** (`@Entity`) — сущности БД;
* **Retrofit‑сервисы** — API‑запросы;
* **DTO‑модели** — данные из API;
* **реализации репозиториев** (`CourseRepositoryImpl`) — объединение источников данных;
* мапперы (конвертация DTO ↔ Entity, Entity ↔ Domain);
* настройки Room‑базы данных.

**Зависимости:**
* зависит от `:domain` (использует доменные модели и интерфейсы репозиториев);
* не зависит от `:presentation` и `:app`.

**Ключевые технологии:**
* **Room** — локальная БД;
* **Retrofit** — сеть;
* **Coroutines** — асинхронность.

## 4. `:domain`

**Назначение:** бизнес‑логика приложения, независимая от платформы и источников данных.

**Содержимое:**
* **доменные модели** (`Course`, `User`) — чистые Kotlin‑классы без Android‑зависимостей;
* **Use Cases** (`GetCoursesUseCase`) — сценарии бизнес‑логики;
* бизнес‑правила и валидации.


**Зависимости:** не зависит ни от каких модулей проекта — самый нижний слой в Clean Architecture.

**Ключевые принципы:**
* чистая бизнес‑логика без Android‑специфичных классов;
* интерфейсы репозиториев определяют, *что* нужно, а не *как* это получить.

## 5. `:presentation`

**Назначение:** отображение данных и обработка пользовательского ввода (UI + логика UI).

**Содержимое:**
* **ViewModel‑классы** — хранение состояния экрана и бизнес‑логика UI;
* **Compose‑экраны и компоненты** — UI‑элементы;
* **MVI‑State‑классы** (`CoursesScreenState`, `ProfileScreenState`) — описание состояний UI;
* **Intent‑классы** (`CoursesIntent`, `ProfileIntent`) — действия пользователя;
* обработчики навигации;
* UI‑эффекты (Snackbar, навигация, диалоги).

**Зависимости:**
* зависит от `:domain` (использует Use Cases и доменные модели);
* зависит от `:common` (использует общие утилиты и UI‑компоненты);
* не зависит напрямую от `:data`.

**Ключевые технологии и паттерны:**
* **Compose** — декларативный UI;
* **MVI** — архитектура UI;
* **Flow/State** — реактивное обновление UI;
* **Hilt** — внедрение зависимостей в `ViewModel`.

---
