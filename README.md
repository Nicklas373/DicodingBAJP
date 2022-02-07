# Dicoding Academy - Belajar Android Jetpack Pro

Pengembangan aplikasi "Movie Catalog" berdasarkan modul "Dicoding Academy - Belajar Android Jetpack Pro".

## Disclaimer
```
Repository ini hanya ditujukan untuk sebagai progress pengembangan aplikasi di setiap submissionnya, mengikuti standar
materi yang telah diberikan oleh Dicoding Academy. Repository ini tidak ada berisi materi pengajaran ataupun informasi
"How-To" dari Dicoding Academy atau dari manapun, hanya berisi ketentuan atau acuan apa saja yang harus dilakukan untuk
menempuh syarat untuk pengajuan submission yang telah ditentukan oleh pihak Dicoding.
```

## Branch Information
Setiap branch berisi nama submission dengan commit historynya masing - masing, dan merupakan folder androidstudioproject
dimana dapat digunakan untuk rebuild ataupun modifikasi project secara langsung dengan IDE yang compatible.

## Submission 1 [Architecture Component]

Pada submission 1 membuat aplikasi "Movie Catalog" dengan beberapa kriteria sebagai berikut:

- Menerapkan komponen ViewModel
- Menerapkan Unit dan Instrumentation Test pada ViewModel (Espresso & Mockito)

NOTE: Data yang diambil merupakan data statik array dengan resources dari data The Movie DB
- [Resources](https://github.com/Nicklas373/DicodingBAJP-Asset)

## Submission 2 [Repository dan LiveData]

Pada submission 2 membuat aplikasi "Movie Catalog" dengan beberapa kriteria sebagai berikut:
(Navigation dan API)

- Menggunakan Repository pattern di dalam sebuah aplikasi (Repository dan Injection dalam Proyek Academy).
- Menerapkan Unit testing pada kelas Repository (Pengujian Repository dan Injection dalam Proyek Academy).
- Menggunakan LiveData di dalam project aplikasi (LiveData dalam Proyek Academy).
- Menerapkan Unit testing pada ViewModel dan LiveData (Pengujian LiveData dalam Proyek Academy).
- Menerapkan Idling Resouces untuk menangani asynchronus proses saat Instrumental Testing (Pengujian dengan Idling Resource dalam Proyek Academy).

NOTE: Data yang diambil merupakan data yang di generate dari TheMovieDB REST API

## Submission 3

Pada submission 3 membuat aplikasi "Movie Catalog" dengan beberapa kriteria sebagai berikut:

- Menerapkan Room untuk transaksi database dengan cara yang sederhana (Room dalam Proyek Academy)
- Menerapkan Pagination untuk memetakkan data yang akan ditampilkan pada aplikasi (Pagination dalam Proyek Academy)

NOTE: Data yang diambil merupakan data yang di generate dari TheMovieDB REST API

## Application Information
Aplikasi Movie Catalog merupakan suatu aplikasi yang digunakan untuk menampilkan 10 data list movie dan tv show yang telah
di generate secara manual dari fitur list profile pada website TheMovieDB dan menggunakan TheMovieDB REST API untuk menampilkan
data movie dan tv show beserta beberapa data relasi lainnya. Aplikasi ini menerapkan beberapa konsep yang telah diberikan oleh modul Dicoding
Academy "Belajar Android Jetpack Pro"

Aplikasi ini memiliki beberapa fitur sebagai berikut:
- Fitur Movie & Tv Show List
  Menampilkan 5 data movie dan tv show yang dapat di urutkan berdasarkan nama dengan metode ascending dan descending,
  data yang ditampilkan adalah foto cover, judul, tanggal rilis, jumlah season dan episode dan rating dari movie dan tv show.
- Fitur Detail Movie & Tv Show
  Menampilkan informasi movie dan tv show dengan data yang ditampilkan adalah foto cover, judul, tanggal rilis, jumlah season dan episode, rating,
  revenue dan beberapa detail yang identik (termasuk interface menampilkan daftar perusahaan produksi dan genre dari movie dan tv show
  yang dipilih untuk dilihat informasinya)
- Fitur Favorit Movies dan Tv Show
  Menampilkan daftar Movies dan Tv Show yang difavoritkan dan menyimpan data Movies dan TvShow kedalam database dengan memanfaatkan DAO Room, PagedList dan Sort
- Fitur Settings
  Menampilkan pengaturan berupa pengubah tema (Dark mode / Light mode) dan pengaturan untuk fitur splashscreen aplikasi
  
## Application Method
Berikut adalah beberapa metode yang digunakan dalam pembuatan aplikasi ini;

- Idling Resource (Untuk skenario delay pada background thread API)
- LiveData dan Retrofit (Untuk skenario fetch data dari API)
- Paging (Untuk skenario tidak menampilkan keseluruhan data dalam adapter)
- RawQuery (Untuk skenario sort data pada saat menampilkan data dari database / room)
- Repository & Injection (Untuk skenario offline-online data)
- Room (Untuk skenario penyimpanan data yang di fetch dari API)
- Unit Testing & Instrumentation Testing (Espresso & Mockito)
- ViewModel

## Screenshoot:
<p align="center">
<img width="240" height="500" src="https://raw.githubusercontent.com/Nicklas373/DicodingBAJP/master/Screenshoot/photo_2022-02-07_11-40-42.jpg">&nbsp;&nbsp;&nbsp;
<img width="240" height="500" src="https://raw.githubusercontent.com/Nicklas373/DicodingBAJP/master/Screenshoot/photo_2022-02-07_11-40-40.jpg">&nbsp;&nbsp;&nbsp;
<img width="240" height="500" src="https://raw.githubusercontent.com/Nicklas373/DicodingBAJP/master/Screenshoot/photo_2022-02-07_11-40-43.jpg">
</p>


# HANA-CI Build Project || 2016-2022