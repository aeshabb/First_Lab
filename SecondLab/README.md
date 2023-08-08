# __Как создать Project.jar и запустить его__
## ___Введите данные команды в консоль по очереди___
1. _javac -d bin -sourcepath src -cp lib/Pokemon.jar src/**/*.java src/*.java_
2. _cd bin_  
3. _touch manifest.txt_  
4. _echo 'Main-Class: Main_  
5. _Class-Path: ../lib/Pokemon.jar' >> Manifest.txt_   
6. _jar cvfm Project.jar Manifest.txt ._  
7. _java -jar Project.jar_

