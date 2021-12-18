### Тестирование онлайн калькулятора по расчету ожидаемого объема поражения легких у пациентов с инфекцией COVID-19. (сайт гомельского государственного медицинского университета).
#### гомельский мед.университет https://gsmu.by/    
сам калькулятор https://gsmu.by/upload/calcForGSMU/calcGSMU.html
 
#### Краткое описание   
Фреймворк тестирования калькулятора был написан на языке **Java**, с использованием библиотек **Atlas + TestNG**.
Класс для запуска процесса тестирования располагается по данному пути [src/test/java/TestInjuredLungCalculator.java](src/test/java/TestInjuredLungCalculator.java)),   
[pom.xml](pom.xml) содержит необходимые зависимости.    
При падении тестов, создается снимок(и) экрана, сохраняющиеся в директории target/screens/
Параметры для тестирования прописаны в тестовом классе, см.аннотации
Отчет процесса тестирования, времени прохождения тестов находится в разделе target/surefire-reports/index.html
#### Скриншоты:   
* Отчет:

![target/surefire-reports/index.html](img/report.jpg)   
 
![target/surefire-reports/index.html](img/report_timing.jpg)   
  
![target/surefire-reports/index.html](img/report_timing_proc.jpg)
* Образцы скриншотов. Создаются при падении тестов в директории target/screens:   

![target/screens](img/screenshots2021-12-18_13-25-29.png)   

![target/screens](img/screenshots2021-12-18_13-25-34.png)   

![target/screens](img/screenshots2021-12-18_13-25-39.png)   

![target/screens](img/screenshots2021-12-18_13-25-47.png)   




