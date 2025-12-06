## **ARCADIA ASCENSION**

---

**Arcadia Ascension** adalah sebuah game RPG berbasis teks yang dikembangkan menggunakan bahasa **Java**. Game ini mengintegrasikan konsep **Algoritma dan Struktur Data** ke dalam alur permainan yang interaktif dan edukatif.

---

## **Fitur Utama**

- [x]  Linked List
- [x]  Stack
- [x]  Queue
- [x]  Graph
- [x]  Searching
- [x]  Sorting

---

## **Detail Fitur**

### **Inventory (siapa yang jadi pertama)**

- Menggunakan struktur data **Stack**.
- Inventory dapat digunakan pada setiap level.
- Pemain dapat mengambil atau menggunakan item berdasarkan prinsip **LIFO**.

### **Player**

- **Stats:**
    - HP: 100
    - XP/level

### **NPC**

- NPC akan memberikan dialog panjang.
- Setelah dialog, NPC memberikan pertanyaan.
- Jawaban yang benar memberikan **reward** kepada pemain.

### Item

- Heal Potion = untuk tambah darah
- Damage Potion = untuk mengurangi darah musuh
- XP Potion = player bisa menambah xp jika menggunakan potion ini

---

# **Level 1 — Linked List (kevin)**

### **Puzzle Ingatan**

- Pemain akan diberikan **5 angka acak**.
- Angka harus dihafal dalam waktu **5 detik**.
- Setelah waktu habis, pemain harus memasukkan kembali angka tersebut secara berurutan.
- Jika terdapat **satu saja angka yang salah**, HP pemain berkurang dan angka akan digenerate ulang.

---

# **Level 2 — Queue (halis)**

- Pemain menghadapi beberapa **monster** dalam antrean (queue).
- Serangan dilakukan secara bergiliran antara pemain dan monster.
- Pada tiap turn, pemain dapat memilih:
    - **Attack**
    - **Gunakan potion**
        - list potion
- Level selesai ketika seluruh monster dalam antrean berhasil dikalahkan.

---

# **Level 3 — Stack (komang)**

- Jalan pemain terhalang oleh tumpukan batu.
- Pemain harus memindahkan batu dengan ketentuan:
    - Batu hanya dapat diambil dari **bagian paling atas**.
- Pemain harus menyingkirkan batu secara berurutan untuk membuka jalan.

---

# **Level 4 — Searching & Sorting (marsha & echa)**

- Pemain dihadapkan pada sebuah **pintu tertutup**.
- Untuk membukanya, pemain harus menebak **password 5 digit**.
- Pemain diberikan **5 kesempatan** untuk menebak.
- Setiap tebakan akan menghasilkan umpan balik:
    - **Benar** → Angka dan posisi benar
    - **Benar tapi salah posisi** → Angka benar, posisi salah
    - **Tidak ada dalam password** → Angka salah sepenuhnya

---

# **Level Check (yola)**

- Sistem akan memeriksa apakah pemain telah menyelesaikan seluruh level sebelumnya.
- Jika ada level yang belum pernah dijalani, **Boss Level tidak akan terbuka**.
- Pemain harus kembali dan menelusuri level yang belum selesai.

---

# **Boss Level (sagos & kevin)**

- Pemain menghadapi **Boss** dalam pertarungan turn-based.
- Pemain dapat:
    - **Menyerang**
    - **Dodge**
    - Menggunakan **Potion** dari inventory (Stack) untuk menambah HP
- Boss memiliki perilaku dinamis:
    - Dapat **melarikan diri** jika HP tersisa sangat sedikit
- Pertarungan berakhir ketika Boss dikalahkan atau melarikan diri.
