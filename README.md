# ARCADIA ASCENSION - RPG Game

## Deskripsi
**Arcadia Ascension** adalah sebuah game RPG berbasis teks yang dibangun menggunakan bahasa Java. Game ini menggabungkan konsep pembelajaran Algoritma dan Struktur Data melalui gameplay yang interaktif dan menarik.

## Fitur Utama

### 📊 Struktur Data yang Diimplementasikan

1. **Level 1: Linked List - Chain Forest**
   - Inventory berbasis Linked List untuk mengumpulkan item
   - Traversal, Insert, Search, dan Delete operations
   - Battle dengan Goblin

2. **Level 2: Stack - Memory Puzzle Room**
   - Game puzzle memory dengan undo functionality
   - LIFO (Last In First Out) principle
   - Battle dengan Illusionist

3. **Level 3: Queue - Potion Brewing**
   - Sistem brewing potion berbasis Queue
   - FIFO (First In First Out) principle
   - Mini game brewing dengan sequence input
   - Battle dengan Fire Elemental

4. **Level 4: Sorting & Searching - Magic Library**
   - Bubble Sort untuk mengurutkan spell berdasarkan damage
   - Linear Search untuk mencari spell
   - Battle dengan Ancient Librarian

5. **Level 5: Tree - Dungeon Maze**
   - Binary Tree untuk navigasi dungeon
   - Tree traversal untuk eksplorasi
   - Battle dengan Ancient Dragon atau Black Knight

6. **Final Boss Fight**
   - Pertarungan epik melawan Dark Lord
   - Sistem battle yang kompleks dengan berbagai action
   - Mana dan HP management

## 🎮 Game Mechanics

### Player Stats
- **Level**: Tingkat pemain (naik seiring progression)
- **HP/MaxHP**: Health Point
- **Mana/MaxMana**: Magic Point untuk casting spell
- **Attack**: Damage yang diberikan
- **Defense**: Mitigasi damage yang diterima
- **EXP/MaxEXP**: Experience untuk level up

### Battle System
- Turn-based combat
- Multiple action choices (Attack, Spell, Heal, Defend)
- Enemy AI dengan berbagai attack patterns
- Damage calculation dengan formula: `damage = attack + random(0-range) - enemy_defense`

### Progression
Setiap level memberikan:
- Rewards dan EXP
- Stat boosts
- Items untuk inventory
- Persiapan untuk level selanjutnya

## 📁 Struktur Project

```
Arcadia-Ascension/
│
├── src/
│   ├── ArcadiaAscension.java          (Main Game File)
│   │
│   ├── models/                         (Data Model Package)
│   │   ├── NodePlayer.java            (Player class dengan stats)
│   │   ├── ItemNode.java              (Node untuk Linked List item)
│   │   └── Inventory.java             (Inventory berbasis Linked List)
│   │
│   ├── levels/                         (Level Package)
│   │   ├── Level1_LinkedListForest.java
│   │   ├── Level2_StackMemoryPuzzle.java
│   │   ├── Level3_QueuePotionBrewing.java
│   │   ├── Level4_SortingSearchingLibrary.java
│   │   ├── Level5_TreeDungeonMaze.java
│   │   └── BossFight.java              (Final Boss Fight)
│   │
│   └── utils/                          (Utility Package)
│       ├── Stack.java                 (Generic Stack implementation)
│       └── Queue.java                 (Generic Queue implementation)
│
└── README.md                          (Documentation)
```

## 🚀 Cara Menjalankan

### Prerequisites
- Java JDK 8 atau lebih tinggi
- Terminal/Command Prompt

### Compile
```bash
cd "D:\File Kuliah\Semester3\ALgodat\Praktikum\Project\Arcadia-Ascension"
javac -d bin src/*.java src/**/*.java
```

### Run
```bash
java -cp bin ArcadiaAscension
```

### Atau langsung compile dan run:
```bash
javac src/ArcadiaAscension.java src/models/*.java src/utils/*.java src/levels/*.java
java -cp src ArcadiaAscension
```

## 🎯 Gameplay Guide

1. **Mulai Game**: Masukkan nama karakter
2. **Setiap Level**: 
   - Selesaikan challenge yang diberikan
   - Battle dengan enemies
   - Kumpulkan rewards
3. **Progression**: Stat meningkat seiring level up
4. **Final Boss**: Gunakan semua kekuatan yang sudah dikumpulkan
5. **Victory**: Selamatkan dunia dari kegelapan!

## 📚 Konsep Algoritma yang Dipelajari

### 1. Linked List (Level 1)
- **Operasi**: Insert at end, Traversal, Search, Delete
- **Kompleksitas**: O(n) untuk insertion dan deletion, O(n) untuk search
- **Gunakan**: Inventory management yang dinamis

### 2. Stack (Level 2)
- **Operasi**: Push, Pop, Peek
- **Kompleksitas**: O(1) untuk semua operasi
- **Gunakan**: Undo functionality pada puzzle

### 3. Queue (Level 3)
- **Operasi**: Enqueue, Dequeue, Peek
- **Kompleksitas**: O(1) untuk semua operasi
- **Gunakan**: Task management dengan FIFO principle

### 4. Sorting & Searching (Level 4)
- **Sorting**: Bubble Sort - O(n²)
- **Searching**: Linear Search - O(n)
- **Gunakan**: Spell organization dan lookup

### 5. Tree (Level 5)
- **Struktur**: Binary Tree untuk maze navigation
- **Operasi**: Tree traversal (Preorder, Inorder, Postorder)
- **Gunakan**: Dungeon exploration dan decision tree

## 💾 Fitur Khusus

- ✅ **No External Libraries**: Hanya menggunakan Scanner dari java.util
- ✅ **Generic Implementation**: Stack dan Queue menggunakan Java Generics
- ✅ **Interactive Combat**: Real-time battle dengan player input
- ✅ **Progressive Difficulty**: Setiap level lebih challenging
- ✅ **Stat Tracking**: Akurat tracking player progression
- ✅ **Story-Driven**: Game flow yang mengikuti cerita

## 🎓 Learning Outcomes

Setelah bermain game ini, player akan memahami:
- Implementasi berbagai struktur data
- Trade-offs antara berbagai data structure
- Time dan space complexity analysis
- Practical application dari algoritma
- Game development basics

## 🔧 Teknologi

- **Bahasa**: Java (JDK 8+)
- **Paradigm**: Object-Oriented Programming
- **Design Patterns**: Factory (untuk level creation), Strategy (untuk enemy AI)
- **I/O**: Console-based dengan Scanner

## 📝 Notes

- Game ini fully playable dari start hingga finish
- Setiap level dapat diikuti dengan berbagai strategi
- Battle outcome tergantung pada pilihan player dan randomization
- Statistik player persisten selama satu game session

## 👨‍💻 Pengembang

Dibuat sebagai project pembelajaran untuk mata kuliah **Algoritma dan Struktur Data**

---

**Selamat bermain Arcadia Ascension dan semoga Anda dapat menyelamatkan dunia dari kegelapan!** 🌟
