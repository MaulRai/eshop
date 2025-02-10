# Reflection 1

You already implemented two new features using Spring Boot. Check again your source code 
and evaluate the coding standards that you have learned in this module. Write clean code 
principles and secure coding practices that have been applied to your code.  If you find 
any mistake in your source code, please explain how to improve your code.

*Source code/Inspo:* [Building and Deploying a Java CRUD Rest API with Spring Boot, Hibernate, MySQL, Docker, Docker Compose, and Kubernetes](https://medium.com/@midejoseph24/building-and-deploying-a-java-crud-rest-api-with-spring-boot-hibernate-mysql-docker-docker-5b7d1847345b)

Evaluasi yang saya dapat selama mengimplementasi modul edit dan delete adalah bahwa dengan
menerapkan code principle dan clean code, bagian-bagian dan fungsionalitas menjadi sangat
jelas dan teratur. Aturan ini membuat segalanya menjadi terorganisir, mulai dari method,
class, sampai struktur file nya. Modul-modul tersebut di rancang mulai dari membuat 
repositori dari masing-masing method dengan implementasi backend tersendiri. Lalu, interface
pada bagian service beserta implementasinya pada bagian sevice implementation. Pada bagian ini,
method overriding cukup memanggil method handler pada repositori yang bersesuaian. Kemudian
method-method itu juga akan dipanggil lewat controller yang juga akan meng-handle routing
sekaligus. Selain model dan controller, saya juga menambahkan aspek view, yakni interface
untuk meng-update atribut produk dan tombol edit dan delete pada file di templates. 

Terdapat beberapa perbaikan dari source code yang menyebabkan kode saya yang sebelumnya tidak
berfungsi (whitelabel error page, no static resource) menjadi berfungsi, yaitu dengan mengubah 
String productId menjadi UUID productId. Tentunya perubahan ini juga berpengaruh kepada file-file 
dependen lain, sehingga perlu disesuaikan lagi.
