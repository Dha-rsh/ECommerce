1. Project Folder: C:\apache-tomcat-10.1.43\webapps\Ecommerce

2. list of all Java files : dir /s /b *.java > sources.txt

3. Compile the Java files

javac -cp "C:\apache-tomcat-10.1.43\webapps\ECommerce\WEB-INF\classes*;C:\apache-tomcat-10.1.43\webapps\Ecommerce\WEB-INF\lib*;C:\apache-tomcat-10.1.43\lib*" -d "C:\apache-tomcat-10.1.43\webapps\ECommerce\WEB-INF\classes" @sources.txt 
This command compiles all Java files and puts the .class files in WEB-INF/classes.

4. Start the server using "C:\apache-tomcat-10.1.43\bin" then run startup.bat

http://localhost:8080/ECommerce

5. Required Library WEB-INF/lib/ -> mysql-connector-j-9.3.0.jar
Admin portal:
<img width="1911" height="829" alt="image" src="https://github.com/user-attachments/assets/73bcc599-5d5e-4b21-a928-e7642250721b" />

User portal: 
<img width="1889" height="840" alt="image" src="https://github.com/user-attachments/assets/9093de62-ed0b-44e4-832f-235fc018cc5c" />
