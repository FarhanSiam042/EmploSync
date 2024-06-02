public class Employee {
    private String name;
    private String id;
    private String position;
    private double salary;
    private int daysPresent;
    private int daysAbsent;

    public Employee(String name, String id, String position, double salary, int daysPresent, int daysAbsent) {
        this.name = name;
        this.id = id;
        this.position = position;
        this.salary = salary;
        this.daysPresent = daysPresent;
        this.daysAbsent = daysAbsent;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDaysPresent() {
        return daysPresent;
    }

    public void setDaysPresent(int daysPresent) {
        this.daysPresent = daysPresent;
    }

    public int getDaysAbsent() {
        return daysAbsent;
    }

    public void setDaysAbsent(int daysAbsent) {
        this.daysAbsent = daysAbsent;
    }
}