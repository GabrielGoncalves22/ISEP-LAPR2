# Supplementary Specification (FURPS+)

## Functionality

| **_Function_** (EN)  | **_Função_** (PT) | **_Description/Example_** (EN)                                           |                                       
|:------------------------|:-----------------|:--------------------------------------------|
| **Auditing** | **Auditoria** | **・** "(...) for any time interval on one day, the difference between the number of new clients arrival and the number of clients leaving the center every five-minute period is computed." <br><br> **・**  "In the case of a working day, with a center open from 8 a.m. until 8 p.m., a list with 144 integers is obtained, where a positive integer means that in such a five-minute slot more clients arrive at the center for vaccination than clients leave with the vaccination process completed. A negative integer means the opposite." |
| **Email** | **Email** | **・** "The system should be able to notify (e.g.: SMS or email) the user that his/her recovery period has ended." |
| **Help** | **Ajuda** | **・** "The worst-case time complexity analysis of the algorithms should be properly documented in the user manual of the application (in the annexes)." <br><br> **・** "The user manual must be delivered with the application." |
| **Localization** | **Localização** | **・** "The application must support, at least, the Portuguese and the English languages."|
| **Reporting** | **Relatórios** | **・** "The Center Coordinator wants to monitor the vaccination process, to see statistics and charts, to evaluate the performance of the vaccination process, generate reports and analyze data from other centers, including data from law systems."|
| **Security** | **Segurança** | **・** "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits." <br><br> **・** "Only the nurses are allowed to access all user’s health data."|
| **System Workflow** | **Fluxo de trabalho** | **・** "If the information is correct, the receptionist acknowledges the system that the user is ready to take the vaccine." |
| **System management** | **Gestão do sistema** | **・** "An Administrator is responsible for properly configuring and managing the core information (e.g.: type of vaccines, vaccines, vaccination centers, employees) required for this application to be operated daily by SNS users, nurses, receptionists, etc." <br><br> **・** "The DGS has Administrators who administer the application." <br><br> **・** Any Administrator uses the application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in the vaccination process." |


## Usability

- "The user manual must be delivered with the application."


- "The application graphical interface is to be developed in JavaFX 11".


- "All the images/figures produced during the software development process should be recorded in SVG format."


- "Use javadoc to generate useful documentation for java code."


- "Adopt recognized coding standards."


## Reliability

- "So, the application should implement a bruteforce algorithm (an algorithm which examines all the contiguous subsequences) to determine the contiguous subsequence with maximum sum."


- "The implemented algorithm should be analyzed in terms of its worst-case time complexity, and it should be compared to a benchmark algorithm provided."


- "The computational complexity analysis (of the brute-force algorithm and any sorting algorithms implemented within this application), must be accompanied by the observation of the execution time of the algorithms for inputs of variable size, in order to observe the asymptotic behavior."


- "The application should use object serialization to ensure data persistence between two runs of the application."


## Performance

- "(...) where a positive integer means that in such a five-minute slot more clients arrive at the center for vaccination than clients leave with the vaccination process completed. A negative integer means the opposite."


- "(...) whose sum of their entries is maximum. This will show the time interval, in such a day, when the vaccination center was less effective in responding."


## Supportability

- "(...) the application should be designed to easily support managing other future pandemic events requiring a massive vaccination of the population."


- "The software application should also be conceived having in mind that it can be further commercialized to other companies and/or organizations and/or healthcare systems besides DGS."


- "If the user authorizes the sending of the SMS, the application should send an SMS message when the vaccination event is scheduled and registered in the system."


- "The system should be able to notify (e.g.: SMS or email) the user that his/her recovery period has ended."


- "An Administrator is responsible for properly configuring and managing the core information (e.g.: type of vaccines, vaccines, vaccination centers, employees) required for this application to be operated daily by SNS users, nurses, receptionists, etc."


- "As the allocation of receptionists and nurses to vaccination centers might be complex, by now, the system might assume that receptionists and nurses can work on any vaccination center."


- "(...) the application should check the vaccination center capacity for that day/time and, if possible, confirm that the vaccination is scheduled and inform the user that (s)he should be at the selected vaccination center at the scheduled day and time."


- "If the information is correct, the receptionist acknowledges the system that the user is ready to take the vaccine."


- "The development team must implement unit tests for all methods, except for methods that implement Input/Output operations."


- "The unit tests should be implemented using the JUnit 5 framework."


- "The JaCoCo plugin should be used to generate the coverage report."


## +

### Design Constraints


### Implementation Constraints

- "The application must implement a brute force algorithm (an algorithm that examines all contiguous substrings) to determine the contiguous substring with maximum sum. The implemented algorithm must be analyzed in terms of its worst-case time complexity and must be compared to a given reference algorithm."


- "The application must be developed in Java language using the IntelliJ IDE or NetBeans."


- "The unit tests should be implemented using the JUnit 5 framework."


- "During the system development, the team must: (i) adopt best practices for identifying requirements, and for OO software analysis and design; (ii) adopt recognized coding standards (e.g., CamelCase); (iii) use Javadoc to generate useful documentation for Java code."


- **"The application should run on Microsoft Windows, macOS and several Unix-like OSs."**

### Interface Constraints

- "The application graphical interface is to be developed in JavaFX 11."


### Physical Constraints

- "The application should use object serialization to ensure data persistence between two runs of the application."
# Supplementary Specification (FURPS+)

## Functionality

| **_Function_** (EN)  | **_Função_** (PT) | **_Description/Example_** (EN)                                           |                                       
|:------------------------|:-----------------|:--------------------------------------------|
| **Auditing** | **Auditoria** | **・** "(...) for any time interval on one day, the difference between the number of new clients arrival and the number of clients leaving the center every five-minute period is computed." <br><br> **・**  "In the case of a working day, with a center open from 8 a.m. until 8 p.m., a list with 144 integers is obtained, where a positive integer means that in such a five-minute slot more clients arrive at the center for vaccination than clients leave with the vaccination process completed. A negative integer means the opposite." |
| **Email** | **Email** | **・** "The system should be able to notify (e.g.: SMS or email) the user that his/her recovery period has ended." |
| **Help** | **Ajuda** | **・** "The worst-case time complexity analysis of the algorithms should be properly documented in the user manual of the application (in the annexes)." <br><br> **・** "The user manual must be delivered with the application." |
| **Localization** | **Localização** | **・** "The application must support, at least, the Portuguese and the English languages."|
| **Reporting** | **Relatórios** | **・** "The Center Coordinator wants to monitor the vaccination process, to see statistics and charts, to evaluate the performance of the vaccination process, generate reports and analyze data from other centers, including data from law systems."|
| **Security** | **Segurança** | **・** "All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits." <br><br> **・** "Only the nurses are allowed to access all user’s health data."|
| **System Workflow** | **Fluxo de trabalho** | **・** "If the information is correct, the receptionist acknowledges the system that the user is ready to take the vaccine." |
| **System management** | **Gestão do sistema** | **・** "An Administrator is responsible for properly configuring and managing the core information (e.g.: type of vaccines, vaccines, vaccination centers, employees) required for this application to be operated daily by SNS users, nurses, receptionists, etc." <br><br> **・** "The DGS has Administrators who administer the application." <br><br> **・** Any Administrator uses the application to register centers, SNS users, center coordinators, receptionists, and nurses enrolled in the vaccination process." |


## Usability

- "The user manual must be delivered with the application."


- "The application graphical interface is to be developed in JavaFX 11".


- "All the images/figures produced during the software development process should be recorded in SVG format."


- "Use javadoc to generate useful documentation for java code."


- "Adopt recognized coding standards."


## Reliability

- "So, the application should implement a bruteforce algorithm (an algorithm which examines all the contiguous subsequences) to determine the contiguous subsequence with maximum sum."


- "The implemented algorithm should be analyzed in terms of its worst-case time complexity, and it should be compared to a benchmark algorithm provided."


- "The computational complexity analysis (of the brute-force algorithm and any sorting algorithms implemented within this application), must be accompanied by the observation of the execution time of the algorithms for inputs of variable size, in order to observe the asymptotic behavior."


- "The application should use object serialization to ensure data persistence between two runs of the application."


## Performance

- "(...) where a positive integer means that in such a five-minute slot more clients arrive at the center for vaccination than clients leave with the vaccination process completed. A negative integer means the opposite."


- "(...) whose sum of their entries is maximum. This will show the time interval, in such a day, when the vaccination center was less effective in responding."


## Supportability

- "(...) the application should be designed to easily support managing other future pandemic events requiring a massive vaccination of the population."


- "The software application should also be conceived having in mind that it can be further commercialized to other companies and/or organizations and/or healthcare systems besides DGS."


- "If the user authorizes the sending of the SMS, the application should send an SMS message when the vaccination event is scheduled and registered in the system."


- "The system should be able to notify (e.g.: SMS or email) the user that his/her recovery period has ended."


- "An Administrator is responsible for properly configuring and managing the core information (e.g.: type of vaccines, vaccines, vaccination centers, employees) required for this application to be operated daily by SNS users, nurses, receptionists, etc."


- "As the allocation of receptionists and nurses to vaccination centers might be complex, by now, the system might assume that receptionists and nurses can work on any vaccination center."


- "(...) the application should check the vaccination center capacity for that day/time and, if possible, confirm that the vaccination is scheduled and inform the user that (s)he should be at the selected vaccination center at the scheduled day and time."


- "If the information is correct, the receptionist acknowledges the system that the user is ready to take the vaccine."


- "The development team must implement unit tests for all methods, except for methods that implement Input/Output operations."


- "The unit tests should be implemented using the JUnit 5 framework."


- "The JaCoCo plugin should be used to generate the coverage report."


## +

### Design Constraints


### Implementation Constraints

- "The application must implement a brute force algorithm (an algorithm that examines all contiguous substrings) to determine the contiguous substring with maximum sum. The implemented algorithm must be analyzed in terms of its worst-case time complexity and must be compared to a given reference algorithm."


- "The application must be developed in Java language using the IntelliJ IDE or NetBeans."


- "The unit tests should be implemented using the JUnit 5 framework."


- "During the system development, the team must: (i) adopt best practices for identifying requirements, and for OO software analysis and design; (ii) adopt recognized coding standards (e.g., CamelCase); (iii) use Javadoc to generate useful documentation for Java code."


- **"The application should run on Microsoft Windows, macOS and several Unix-like OSs."**

### Interface Constraints

- "The application graphical interface is to be developed in JavaFX 11."


### Physical Constraints

- "The application should use object serialization to ensure data persistence between two runs of the application."
