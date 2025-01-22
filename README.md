# 🏢 Emplosync  
**Employee Management and Payroll System**  

**Emplosync** is a comprehensive **Employee Management and Payroll System** designed to streamline employee management for companies. It features salary calculations, employee details management, attendance tracking, ID generation, and performance feedback. Built using **Java**, **Swing**, and **file manipulation**, Emplosync provides an intuitive interface and robust functionality for HR needs.

---
## 🚀 Features  

1. **Calculate Salary**  
   - Calculate monthly salary for employees based on attendance and absences.

2. **Add Employee Details**  
   - Add new employees by entering their name, position, salary, and attendance details.  
   - Automatically generates and assigns a unique employee ID.  

3. **Remove Employee Details**  
   - Remove employees from the system by entering their unique ID.  

4. **View Employee Details**  
   - Display all employee information, including name, position, salary, attendance, and auto-generated ID.  

5. **Calculate Bonus and Fine**  
   - Bonus: 2% bonus for employees with perfect attendance.  
   - Fine: 1% salary deduction for each day absent.  

6. **Calculate Attendance**  
   - Load attendance data from the database or manually calculate attendance by entering employee details.  

7. **Review & Feedback**  
   - Employers can provide and view feedback for employees based on their performance.

---

## 🛠️ Technologies Used  

- **Programming Language**: Java  
- **UI Framework**: Swing  
- **Data Storage**: File-based storage (for employee details, attendance, and feedback).  

---

## 📂 Project Structure  

```plaintext
Emplosync/
│
├── src/
│   ├── Main.java            # Entry point of the application
│   ├── Login.java           # Admin login panel
│   ├── AddEmployee.java     # Feature to add employee details
│   ├── RemoveEmployee.java  # Feature to remove employee details
│   ├── ViewEmployee.java    # Feature to view employee details
│   ├── CalculateSalary.java # Feature to calculate employee salary
│   ├── BonusFine.java       # Feature to calculate bonus and fine
│   ├── Attendance.java      # Feature to calculate attendance
│   └── Feedback.java        # Feature to manage feedback and reviews
│
├── data/
│   ├── employees.txt        # Stores employee details
│   ├── attendance.txt       # Stores attendance data
│   └── feedback.txt         # Stores employee feedback
│
└── README.md                # Project documentation

