# US 05 - List the arrival of a SNS User to take the vaccine

## 1. Requirements Engineering

### 1.1. User Story Description

- As a nurse, I intend to consult the users in the waiting room of a vacination center.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>The Center Coordinator wants to monitor the vaccination process, to see
statistics and charts, to evaluate the performance of the vaccination process, generate reports and
analyze data from other centers, including data from legacy systems. The goal of the performance
analysis is to decrease the number of clients in the center, from the moment they register at the
arrival, until the moment they receive the SMS informing they can leave the vaccination center. To
evaluate this, it proceeds as follows: for any time interval on one day, the difference between the
number of new clients arrival and the number of clients leaving the center every five-minute period
is computed.

>Now, the problem consists in determining what the contiguous subsequence of the initial sequence
is, whose sum of their entries is maximum. This will show the time interval, in such a day, when the
vaccination center was less effective in responding. So, the application should implement a brute-
force algorithm (an algorithm which examines all the contiguous subsequences) to determine the
contiguous subsequence with maximum sum. The implemented algorithm should be analyzed in
terms of its worst-case time complexity, and it should be compared to a benchmark algorithm
provided. The computational complexity analysis (of the brute-force algorithm and any sorting
algorithms implemented within this application), must be accompanied by the observation of the
execution time of the algorithms for inputs of variable size, in order to observe the asymptotic
behavior. The worst-case time complexity analysis of the algorithms should be properly
documented in the user manual of the application (in the annexes). The user manual must be
delivered with the application
>
**From the client clarifications:**

>**Question:**
>The file loaded in US17 have only one day to analyse, or it could have more than one day(?) and in US16 we need to select the day to analyse from 8:00 to 20:00
>
>**Answer:**
>The file can have data from more than one day. In US16 the center coordinator should select the day for which he wants to analyse the performance of the vaccination center.



>**Question:**
>Is the time of departure of an SNS user the time he got vaccinated plus the recovery time or do we have another way of knowing it?
>
**Answer:**
>The time of departure of an SNS user is the time he got vaccinated plus the recovery time



>**Questions:**
>In US 16, should the coordinator have the option to choose which algorithm to run (e.g. via a configuration file or while running the application) in order to determine the goal sublist, or is the Benchmark Algorithm strictly for drawing comparisons with the Bruteforce one?
>
> **Answer:** The algorithm to run should be defined in a configuration file.



>**Question:**
>I would like to ask that if to analyse the performance of a center, we can assume (as a pre requirement) that the center coordinator was already attributed to a specific vaccination center and proceed with the US as so (like the center coordinator does not have to choose at a certain point where he is working. This is already treated before this US happens). Could you clarify this?
>
>**Answer:** A center coordinator can only coordinate one vaccination center. The center coordinator can only analyze the performance of the center that he coordinates.



>**Question**
>I would like to know if we could be strict the user to pick only those intervals (m) (i.e. 1, 5, 10, 20, 30) as options for analyzing the performance of a center, since picking intervals is dependent on the list which is 720/m (which the length is an integer result). If we let the user pick an interval that results in a non-integer result, this will result in an invalid list since some data for the performance analysis will be lost. Can you provide a clarification on this situation?
>
> **Answer**  The user can introduce any interval value. The system should validate the interval value introduced by the user.



>**Question**
> From the Sprint D requirements it is possible to understand that we ought to implement a procedure that creates a list with the differences between the number of new clients arriving and the number of leaving clients for each time interval. My question then is, should this list strictly data from the legacy system (csv file from moodle which is loaded in US17), or should it also include data from our system?
>
> **Answer** US 16 is for all the data that exists in the system.

### 1.3. Acceptance Criteria

* **AC1:** SNS Users’ list should be presented by order of arrival.

### 1.4. Found out Dependencies

* There is a dependency for "US04 - As a receptionist at a vaccination post, I want to register the arrival of an SNS
  user to take the vaccine", to be able to list it is necessary that the users are registered.

### 1.5 Input and Output Data

*Input Data:*

* Typed data:
  - The name of the file intended to export the vaccination statistics.
  - The time interval

* Selected data:
  - The time interval.
  - Between the options of checking or exporting the vaccination statistics.
  - The date to analyze

*Output Data:*

- The input list (with the differences of arrivals and departures for each time interval);
- The Maximum Sum Sublist;
- The Max Sum.
### 1.6. System Sequence Diagram (SSD)

![US05_SSD](US05_SSD.svg)

### 1.7 Other Relevant Remarks

(none)

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US05_MD](US05_MD.svg)

### 2.2. Other Remarks

>The Performance Analyzer is the tool that every Vaccination Center has available to help the Center Coordinator to analyze the performance of the center.
>The Information to analyze is a list with the difference of the number of new clients arriving and the number of clients leaving the center, at a given time interval.


## 3. Design - User Story Realization

### 3.1. Rationale

**SSD adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1         |    ...showing and requesting the selection of a date to be analyzed  | AnalyzeCenterPerformanceGUI |  **Pure Fabrication:** there is no reason to assign this responsibility to any existing class in the Domain Model. Using a Class for the interactions of the User with the System promotes the **HCLC** principle.   |
| Step 2         | ...requesting the input of a time interval | AnalyzeCenterPerformanceGUI | **Pure Fabrication:** there is no reason to assign this responsibility to any existing class in the Domain Model. Using a Class for the interactions of the User with the System promotes the **HCLC** principle. |
| Step 3         | ... checking if the inputted time interval is valid | PerformanceAnalyzer | **IE:** The PerformanceAnalyzer is responsible for analyzing the performance of a VaccinationCenter, therefore it knows what time intervals are valid for such operation.  |
| | ... sending the inputted time interval to the PerformanceAnalyzer to be validated  | AnalyzeCenterPerformanceController | **Controller:** act as a mediator between the UI and the Model. Has the responsibility of controlling the data transmission between both, which contributes to the use of the **HCLC** principle, as we are not assigning this responsibility to any other Domain class. |
| | ... disponibilize the PerformanceAnalyzer to the AnalyzeCenterPerformanceController  | VaccinationCenter | **IE:** The VaccinationCenter knows its own PerformanceAnalyzer. |
| | ... disponibilize the Arrivals and Departures of a given day to the PerformanceAnalyzer | VaccinationCenter | **IE:** The VaccinationCenter knows its Arrivals and its Departures. |
| | ... disponibilize the VaccinationCenter to the AnalyzeCenterPerformanceController  | VaccinationCentersStore | **IE:** The VaccinationCentersStore knows all the Vaccination Centers of the Company.  |
| | ... disponibilize the previous VaccinationCentersStore to the AnalyzeCenterPerformanceController  | Company | **IE:** The Company knows all of its own stores, therefore it knows the Store related to the Vaccination Centers. The VaccinationCentersStore is created by **Pure Fabrication**, promoting the **HCLC**. |
| Step 4         | ...requesting the confirmation of the selected date and the inserted time interval | AnalyzeCenterPerformanceGUI | **Pure Fabrication:** there is no reason to assign this responsibility to any existing class in the Domain Model. Using a Class for the interactions of the User with the System promotes the **HCLC** principle. |
| Step 5         | ... showing the "input" list, the sublist with the maximum sum and the maximum sum | AnalyzeCenterPerformanceGUI |  **Pure Fabrication:** there is no reason to assign this responsibility to any existing class in the Domain Model. Using a Class for the interactions of the User with the System promotes the **HCLC** principle.  |
| | ... disponibilize the previous information to the AnalyzeCenterPerformanceGUI  | AnalyzeCenterPerformanceController | **Controller:** act as a mediator between the UI and the Model. Has the responsibility of controlling the data transmission between both, which contributes to the use of the **HCLC** principle, as we are not assigning this responsibility to any other Domain class. |
| | ... disponibilize the previous information to the AnalyzeCenterPerformanceController | PerformanceAnalyzer | **IE:** The PerformanceAnalyzer of a center analyzes its performance, it has the results of such analysis. Using a different Class for the analysis of a Center performance, also follows the **HCLC** principle since we don't assign that responsibility to the Vaccination Center.  |
| | ... disponibilize the PerformanceAnalyzer to the AnalyzeCenterPerformanceController  | VaccinationCenter | **IE:** The VaccinationCenter knows its own PerformanceAnalyzer. |
| | ... disponibilize the Arrivals and Departures of a given day to the PerformanceAnalyzer | VaccinationCenter | **IE:** The VaccinationCenter knows its Arrivals and its Departures. |
| | ... disponibilize the VaccinationCenter to the AnalyzeCenterPerformanceController  | VaccinationCentersStore | **IE:** The VaccinationCentersStore knows all the Vaccination Centers of the Company.  |
| | ... disponibilize the previous VaccinationCentersStore to the AnalyzeCenterPerformanceController  | Company | **IE:** The Company knows all of its own stores, therefore it knows the Store related to the Vaccination Centers. The VaccinationCentersStore is created by **Pure Fabrication**, promoting the **HCLC**. |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* VaccinationCenter
* Company
* Arrival
* Departure
* PerformanceAnalyzer


Other software classes (i.e. Pure Fabrication) identified:

* VaccinationCentersStore
* AnalyzeCenterPerformanceController
* AnalyzeCenterPerformanceGUI

## 3.2. Sequence Diagram (SD)

![US05_SD](US05_SD.svg)

## 3.3. Class Diagram (CD)

![US05_CD](US05_CD.svg)

# 4. Tests

**Test 1:** Check if it lists arrival SNS user.

	public void getListArrivalSNSUserTest() {
		
		...
		List<ArrivalSNSUserDTO> expectedArivalSNSUserList = controller.getListArrivalSNSUser();

        ArrivalSNSUserDTO arrivalSNSUserDTO = new ArrivalSNSUserDTO(snsUser);
        ArrivalSNSUserDTO arrivalSNSUserDTO1 = new ArrivalSNSUserDTO(snsUser1);

        List<ArrivalSNSUserDTO> resultArrivalSNSUserList = new ArrayList<>();

        resultArrivalSNSUserList.add(arrivalSNSUserDTO);
        resultArrivalSNSUserList.add(arrivalSNSUserDTO1);
        int n = 0;
        while (n < resultArrivalSNSUserList.size()) {
            boolean result = resultArrivalSNSUserList.get(n).equals(resultArrivalSNSUserList.get(n));
            Assert.assertTrue(result);
            n++;
        }
    }

# 5. Construction (Implementation)

## Class ArrivalSNSUserMapper

    public static ArrivalSNSUser toModel(ArrivalSNSUserDTO auDTO) {
        SNSUser snsUser = auDTO.getSnsUser();
        Date arrivalDate = auDTO.getArrivalDate();
        Time arrivalTime = auDTO.getArrivalTime();
        VaccinationCenter vaccinationCenter = auDTO.getVaccinationCenter();

        return new ArrivalSNSUser(snsUser, arrivalDate, arrivalTime, vaccinationCenter);
    }

    public static List<SNSUserDTO> toListDTO(List<ArrivalSNSUser> listArrivalSNSUser) {
        List<SNSUserDTO> listSNSUserDto = new ArrayList<>();

        for (int i = 0; i < listArrivalSNSUser.size(); i++) {
            ArrivalSNSUser au = listArrivalSNSUser.get(i);

            String name = au.getSnsUser().getName();
            String sex = au.getSnsUser().getSex();
            String birthdate = au.getSnsUser().getBirthdate();
            String phoneNumber = au.getSnsUser().getPhoneNumber();
            String snsUserNumber = au.getSnsUser().getSnsUserNumber();

            SNSUserDTO userDTO= new SNSUserDTO(name,sex,birthdate,phoneNumber,snsUserNumber);
            listSNSUserDto.add(userDTO);
        }

        return listSNSUserDto;

    }

## Class ListArrivalSNSUserController

    private Company company;

    private ArrivalSNSUser arrivalSNSUser;

    private ArrivalSNSUserStore store;

    private VaccinationCenter vaccinationCenter;

    public ListArrivalSNSUserController(/*VaccinationCenter vaccinationCenter*/) {
        //this.vaccinationCenter = vaccinationCenter;
        this(App.getInstance().getCompany());
    }

    public ListArrivalSNSUserController(Company company) {
        this.company = company;
        this.arrivalSNSUser = null;
        this.store = this.company.getArrivalSNSUserStore();
    }

    public List<SNSUserDTO> getListArrivalSNSUser( ){
        List<ArrivalSNSUser> arrivalSNSUserList = store.getListArrivalSNSUser(vaccinationCenter);
        return ArrivalSNSUserMapper.toListDTO(arrivalSNSUserList);

    }

## Class ArrivalSNSUserDTO

    private SNSUser snsUser;

    private Date arrivalDate;

    private Time arrivalTime;

    private VaccinationCenter vaccinationCenter;

    private boolean waitingRoom;

    public ArrivalSNSUserDTO(SNSUser snsUser, Date arrivalDate, Time arrivalTime, VaccinationCenter vaccinationCenter) {
        this.snsUser = snsUser;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.vaccinationCenter = vaccinationCenter;
        this.waitingRoom = true;
    }

    public SNSUser getSnsUser() {
        return snsUser;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public boolean getWaitingRoom() {
        return waitingRoom;
    }

## Class ArrivalSNSUser

	private SNSUser snsUser;

    private Date arrivalDate;

    private Time arrivalTime;

    private VaccinationCenter vaccinationCenter;

    private boolean waitingRoom;

    public ArrivalSNSUser(SNSUser snsUser, Date arrivalDate, Time arrivalTime, VaccinationCenter vaccinationCenter) {
        this.snsUser = snsUser;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.vaccinationCenter = vaccinationCenter;
        this.waitingRoom = true;
    }

    @Override
    public String toString() {
        return String.format("SNS User Number: %s \nArrival Date: %s \nArrival Time: %s",
                this.snsUser.getSnsUserNumber(), this.arrivalDate, this.arrivalTime);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        ArrivalSNSUser otherArrivalSNSUser = (ArrivalSNSUser) otherObject;
        return (this.snsUser.equals(otherArrivalSNSUser.snsUser)
                && (this.arrivalDate.equals(otherArrivalSNSUser.arrivalDate))
                && (this.vaccinationCenter.equals(otherArrivalSNSUser.vaccinationCenter)));
    }

    @Override
    public int compareTo(ArrivalSNSUser otherArrivalSNSUser) {
        if (!this.arrivalDate.equals(otherArrivalSNSUser.arrivalDate)) {
            return this.arrivalDate.compareTo(otherArrivalSNSUser.arrivalDate);
        } else {
            return this.arrivalTime.compareTo(otherArrivalSNSUser.arrivalTime);
        }
    }

    public SNSUser getSnsUser() {
        return snsUser;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public boolean getWaitingRoom() {
        return waitingRoom;
    }

## Class ArrivalSNSUserStore

    private List<ArrivalSNSUser> listAU = new ArrayList<>();

    public ArrivalSNSUser createArrivalSNSUser(ArrivalSNSUserDTO auDto) {
        ArrivalSNSUser au = ArrivalSNSUserMapper.toModel(auDto);
        return au;
    }

    public boolean validateArrivalSNSUser(ArrivalSNSUser au) {
        if (au == null) {
            return false;
        } else {
            return !this.listAU.contains(au);
        }
    }

    public boolean saveArrivalSNSUser(ArrivalSNSUser au) {
        if (!validateArrivalSNSUser(au)) {
            return false;
        } else {
            return addArrivalSNSUser(au);
        }
    }

    private boolean addArrivalSNSUser(ArrivalSNSUser arrivalSNSUser) {
        return this.listAU.add(arrivalSNSUser);
    }


    public List<ArrivalSNSUser> getArrivalSNSUserList() {
        return listAU;
    }

    public List<ArrivalSNSUser> getListArrivalSNSUser(VaccinationCenter vaccinationCenter) {
        List<ArrivalSNSUser> arrivalSNSUserList = new ArrayList<>();

        for (ArrivalSNSUser list : this.listAU) {
            if (list.getVaccinationCenter().equals(vaccinationCenter) && list.getWaitingRoom()) {

                arrivalSNSUserList.add(list);
            }
        }

        return arrivalSNSUserList;
    }

## Class RegisterArrivalSNSUserUI

	RegisterArrivalSNSUserController registerArrivalSNSUserController = new RegisterArrivalSNSUserController();

    @Override
    public void run() {
        //O vaccination center vai ser definido depois do login, ainda por configurar
        //VaccinationCenter vc = App.getInstance().getVaccinationCenter();

        if (registerArrivalSNSUserController.createSNSUser(readSNSUserNumber())) {

            List<ScheduleVaccineDTO> listSVDto = registerArrivalSNSUserController.getListSNSUserVaccinationSchedule();

            if (!listSVDto.isEmpty()) {
                Utils.showList(listSVDto, "Vaccination schedule list: ");

                if (Utils.confirm("Accept the arrival of the SNS user? (y/n)")) {
                    if (registerArrivalSNSUserController.createArrivalSNSUser()) {
                        showData();
                        confirmData();
                    } else {
                        System.out.println("No duplicate entry is possible for the same SNS user on the same day or vaccination period.");
                    }
                }

            } else {
                System.out.println("There is no schedule of vaccines for the indicated SNS user.");
            }

        } else {
            System.out.println("The SNS user number indicated does not correspond to any registered user.");
        }
    }

    private String readSNSUserNumber() {
        return Utils.readLineFromConsole("Insert SNS User Number: ");
    }

    private void confirmData() {
        if (Utils.confirm("Do you want to confirm the arrival of the sns user? (y/n)")) {
            registerArrivalSNSUserController.saveArrivalSNSUser();
            System.out.println("Successfully registered the arrival of the SNS user.");
        } else {
            System.out.println("The arrival of the SNS user was not successfully registered!");
        }
    }

    private void showData() {
        System.out.println("\n*** SNS User Arrival Data ***");
        System.out.println(registerArrivalSNSUserController.getArrivalSNSUser());
    }

# 6. Integration and Demo

A new option in the Nurse menu options has been added.

When using this new option in the nurse menu, it lists the SNS users who are in the waiting queue.

# 7. Observations

In order to carry out this User Story, the Company responsibilities were delegated to other classes. In this way, the
code is more structured and makes it easier to maintain.




