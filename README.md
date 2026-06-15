# AI Study Assistant

An Android-based study companion application designed to help students organize notes, manage study tasks, review flashcards, and utilize AI-powered learning tools.

This project is being developed using **Java** in **Android Studio** as part of a software development and research initiative focused on improving students' learning experiences through mobile technology.

---

## Features

### Current Features

* User Login Screen
* User Registration Screen
* Dashboard Navigation
* Notes Module
* Add Note Functionality
* RecyclerView-based Note Display
* SQLite Database Integration
* Material Design User Interface
* Persistent Local Data Storage

### Planned Features

#### Notes Management

* Create Notes
* View Notes
* Update Notes
* Delete Notes
* Search Notes

#### Flashcards Module

* Create Flashcards
* Review Flashcards
* Edit Flashcards
* Delete Flashcards

#### Study Planner

* Add Tasks
* Set Deadlines
* Mark Tasks as Completed
* Progress Tracking

#### Dashboard Analytics

* Total Notes
* Total Flashcards
* Pending Tasks
* Completed Tasks
* Study Statistics

#### AI Features

* AI Note Summarization
* AI Quiz Generator
* AI Study Guide Generator
* AI Chat Tutor

---

## Tech Stack

### Frontend

* Java
* XML Layouts
* Material Design Components

### Backend

* SQLite Database
* SQLiteOpenHelper

### Development Tools

* Android Studio
* Git
* GitHub

---

## Project Structure

```text
com.example.aistudyassistant

├── activities
│   ├── LoginActivity
│   ├── RegisterActivity
│   ├── DashboardActivity
│   ├── NotesActivity
│   ├── AddNoteActivity
│   ├── FlashcardsActivity
│   └── PlannerActivity
│
├── adapters
│   └── NotesAdapter
│
├── database
│   ├── DatabaseHelper
│   └── NotesRepository
│
├── models
│   └── Note
│
└── utils
```

---

## Database Schema

### Notes Table

| Column  | Type                              |
| ------- | --------------------------------- |
| id      | INTEGER PRIMARY KEY AUTOINCREMENT |
| title   | TEXT                              |
| content | TEXT                              |

---

## Installation

1. Clone the repository:

```bash
git clone https://github.com/jasterdmndl/AIStudyAssistant.git
```

2. Open the project in Android Studio.

3. Allow Gradle to sync.

4. Run the application on:

   * Android Emulator
   * Physical Android Device

---

## Development Roadmap

### Phase 1: Foundation

* [x] Project Setup
* [x] GitHub Repository
* [x] Login Screen
* [x] Register Screen
* [x] Dashboard Navigation

### Phase 2: Notes Module

* [x] Note Model
* [x] RecyclerView
* [x] Add Note
* [x] SQLite Integration
* [ ] Edit Note
* [ ] Delete Note

### Phase 3: Flashcards Module

* [ ] Create Flashcards
* [ ] Review Flashcards
* [ ] CRUD Operations

### Phase 4: Study Planner

* [ ] Task Management
* [ ] Progress Tracking
* [ ] Study Scheduling

### Phase 5: AI Integration

* [ ] AI Summarizer
* [ ] AI Quiz Generator
* [ ] AI Study Guide Generator
* [ ] AI Tutor

---

## Research Context

### Proposed Research Title

**Development and Evaluation of an AI-Powered Mobile Study Assistant for Enhancing Students' Learning Experience**

### General Objective

To develop and evaluate a mobile-based AI Study Assistant that supports students in note management, study planning, knowledge retention, and personalized learning assistance.

---

## Author

Developed by jasterdmndl

---

## License

This project is currently developed for educational, research, and portfolio purposes.
