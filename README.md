# Reflection 1

You already implemented two new features using Spring Boot. Check again your source code 
and evaluate the coding standards that you have learned in this module. Write clean code 
principles and secure coding practices that have been applied to your code.  If you find 
any mistake in your source code, please explain how to improve your code.

*Source code/Inspo:* [Building and Deploying a Java CRUD Rest API with Spring Boot, Hibernate, MySQL, Docker, Docker Compose, and Kubernetes](https://medium.com/@midejoseph24/building-and-deploying-a-java-crud-rest-api-with-spring-boot-hibernate-mysql-docker-docker-5b7d1847345b)

> Evaluasi yang saya dapat selama mengimplementasi modul edit dan delete adalah bahwa dengan
menerapkan code principle dan clean code, bagian-bagian dan fungsionalitas menjadi sangat
jelas dan teratur. Aturan ini membuat segalanya menjadi terorganisir, mulai dari method,
class, sampai struktur file nya. Modul-modul tersebut di rancang mulai dari membuat 
repositori dari masing-masing method dengan implementasi backend tersendiri. Lalu, interface
pada bagian service beserta implementasinya pada bagian sevice implementation. Pada bagian ini,
method overriding cukup memanggil method handler pada repositori yang bersesuaian. Kemudian
method-method itu juga akan dipanggil lewat controller yang juga akan meng-handle routing
sekaligus. Selain model dan controller, saya juga menambahkan aspek view, yakni interface
untuk meng-update atribut produk dan tombol edit dan delete pada file di templates. 
> 
> Terdapat beberapa perbaikan dari source code yang menyebabkan kode saya yang sebelumnya tidak
berfungsi (whitelabel error page, no static resource) menjadi berfungsi, yaitu dengan mengubah 
String productId menjadi UUID productId. Tentunya perubahan ini juga berpengaruh kepada file-file 
dependen lain, sehingga perlu disesuaikan lagi.

# Reflection 2

1. After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage.  
   > Perasaan saya setelah membuat unit test, saya sadar bahwa unit test itu penting dalam 
   > pengembangan perangkat lunak. Bahkan fungsi-fungsi sederhana sekalipun. Pada materi di kelas
   > sempat dijelaskan bahwa fungsi tidak boleh ditulis terlalu panjang dan rumit. Maka dari itu
   > fungsi mau tidak mau harus dibuat dengan memanggil fungsi lain yang sederhana. Oleh karena itu,
   > peran unit test sangat penting. Tidak mungkin sebuah fungsi yang rumit berjalan dengan baik,
   > jika pada level rendahnya diimplementasi dengan keliru. 
   >
   > Jumlah unit test yang harus dibuat bergantung pada kompleksitas kelasnya dan bagaimana behavior-nya.
   > Tidak ada ketetapan yang berlaku, tetapi kembali ke tujuan dari unit test itu sendiri, tes ini
   > berfungsi untuk men-cover segala kemungkinan/senario yang akan memverifikasi correctness kode kita.
   > Dengan menargetkan *high coverage* yaitu di angka 80%, kita bisa cukup yakin akan correctness pada
   > kode kita. Namun, perlu diingat kita harus memprioritaskan untuk mengetes bagian kode kita yang 
   > perannya kritis atau kompleks.

2. Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.

   What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!

   > Dengan memilih untuk membuat file java baru lagi hanya untuk mengetes masalah tersebut, kualitas 
   > kode akan berkurang. Hal ini karena adanya masalah clean code, yakni **redudansi** atau duplikasi kode. 
   > Kita akan kerepotan untuk men-track jika ada perubahan logic pada bagian ini (karena kurang lebih logic-nya 
   > sama) jika ada perubahan. Yang mana akan memunculkan **ketidakkonsisten** kode pula. Jika kita perhatikan, bisa 
   > saja dibuat sebuah lewat refactor yang berisi atribut yang sama dan kemudian cukup inherit kelas tersebut
   > untuk masing-masing suite. Atau bahkan dibuat menjadi satu kelas saja dan menjadi suite utuh.

