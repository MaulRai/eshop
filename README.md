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

# Reflection 3

1. Explain what principles you apply to your project!
   > **Single Responsibility Principle**
   > - CarController bukanlah ProductController, jadi inheritance kurang tepat. 
   > Instead, CarController seharusnya independent.
   > - Pada CarRepository, harusnya menghandle data persistence saja, tetapi class ini juga handle UUID generation,
   > yang seharusnya tugas service. Solusinya dengan memindahkan UUID generation ke CarServiceImpl.
   > - Pindahkan class CarController ke Java file tersendiri. Menulisnya pada satu file yang sama akan
   > mencampur responsibility, membuat kode menjadi lebih sulit di maintain.
   >
   > **Open-Closed Principle***
   > Tidak ada modifikasi lanjutan selain implementasi default pada module menurut prinsip ini, karena kodenya 
   > sudah terbuka untuk ekstensi namun tertutup untuk modifikasi
   > 
   > **Liskov Substitution Principle**
   > Tidak ada modifikasi lanjutan selain implementasi default pada module menurut prinsip ini, karena semua
   > subclass yang ada sudah berfungsionalitas dengan baik untuk menggantikan parent class
   > 
   > **Interface Segregation Principle**
   > Tidak ada modifikasi lanjutan selain implementasi default pada module menurut prinsip ini, karena semua
   > class yang meng-implement interface sudah cocok secara funsionalitas interface nya.
   > 
   > **Dependency Inversion Principle**
   > - Menggunakan kelas abstrak sebisa mungkin, yaitu pada CarController saya mengganti `private CarServiceImpl
   > - carService;` menjadi `private CarService carService;`

2. Explain the advantages of applying SOLID principles to your project with examples.
   > Secara keseluruhan pengaplikasian prinsip SOLID, akan membuat kode mudah dipelihara dan dikembangkan.
   > Kode menjadi bisa lebih fleksibel dan reusable. Selain itu kode akan menjadi lebih mudah diubah dan
   > dites.
   > 
   > Adapun poin spesifik yang saya implementasikan, yang pertama Single Responsibility Principle (SRP),
   > Dengan memisahkan CarController ke dalam file terpisah dan menghindari pewarisan dari ProductController, 
   > kode menjadi lebih "modular" dan lebih mudah dipahami. Dengan memindahkan UUID generation dari repository 
   > ke service layer, CarRepository hanya fokus pada data persistence, sedangkan CarService bertanggung jawab 
   > atas logika bisnis, sehingga memudahkan perubahan pada logika bisnis tanpa mempengaruhi repository.
   > 
   > Selanjutnya pada poin Dependency Inversion Principle (DIP), Dengan mengubah private CarServiceImpl 
   > carService; menjadi private CarService carService;, saya mengurangi ketergantungan terhadap implementasi 
   > konkret. Hal ini memungkinkan fleksibilitas untuk mengganti CarServiceImpl dengan implementasi lain 
   > (misalnya CarServiceMock untuk unit testing)

3. Explain the disadvantages of not applying SOLID principles to your project with examples.
   > Secara keseluruhan jika tanpa pengaplikasian prinsip SOLID, Kelas menjadi terlalu besar dan kompleks karena 
   > menangani banyak tanggung jawab. Perubahan di satu bagian kode bisa menyebabkan efek samping pada bagian 
   > lain, sehingga debugging menjadi sulit. Kode menjadi lebih sulit untuk dipelihara dan dikembangkan, 
   > karena satu kelas memiliki terlalu banyak fungsi.
   > 
   > Secara spesifik, jika saya tidak mengimplementasikan SRP, jika satu class memiliki banyak tanggung jawab, 
   > setiap perubahan kecil bisa berdampak besar pada bagian lain yang tidak berhubungan, sehingga meningkatkan 
   > kompleksitas debugging dan maintenance. Sedangkan jika tidak menerapkan DIP, kode akan bergantung langsung 
   > pada implementasi (CarServiceImpl) dan setiap perubahan dalam implementasi akan memaksa perubahan pada 
   > seluruh kode yang menggunakannya.



