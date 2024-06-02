import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private File employeeFile;
    private File feedbackFile;
    private List<Employee> employees;

    public FileHandler() {
        employeeFile = new File("employeedetails.txt");
        feedbackFile = new File("feedback.txt");
        employees = new ArrayList<>();
        loadEmployeesFromFile();
    }

    private void loadEmployeesFromFile() {
        if (employeeFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue; // Skip empty lines
                    }
                    String[] details = line.split(",");
                    if (details.length == 6) {
                        try {
                            String name = details[0];
                            String id = details[1];
                            String position = details[2];
                            double salary = Double.parseDouble(details[3]);
                            int daysPresent = Integer.parseInt(details[4]);
                            int daysAbsent = Integer.parseInt(details[5]);
                            employees.add(new Employee(name, id, position, salary, daysPresent, daysAbsent));
                        } catch (NumberFormatException e) {
                            System.err.println("Skipping malformed line: " + line);
                        }
                    } else {
                        System.err.println("Skipping malformed line: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployeeById(String id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public void addEmployeeToFile(Employee employee) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(employeeFile, true))) {
            writer.write(employee.getName() + "," + employee.getId() + "," + employee.getPosition() + "," + employee.getSalary() + "," + employee.getDaysPresent() + "," + employee.getDaysAbsent() + "\n");
        }
        employees.add(employee);
    }

    public boolean removeEmployeeFromFile(String id) throws IOException {
        File tempFile = new File("employeedetails_temp.txt");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length > 1 && !details[1].equals(id)) {
                    writer.write(line + "\n");
                } else if (details.length > 1 && details[1].equals(id)) {
                    found = true;
                }
            }
        }

        if (found) {
            if (!employeeFile.delete()) {
                throw new IOException("Could not delete file");
            }
            if (!tempFile.renameTo(employeeFile)) {
                throw new IOException("Could not rename file");
            }
        } else {
            tempFile.delete();
        }

        employees.removeIf(emp -> emp.getId().equals(id));
        return found;
    }

    public void updateEmployeeAttendance(String id, int daysPresent, int daysAbsent) throws IOException {
        File tempFile = new File("employeedetails_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length > 1 && details[1].equals(id)) {
                    details[4] = String.valueOf(daysPresent);
                    details[5] = String.valueOf(daysAbsent);
                    line = String.join(",", details);
                }
                writer.write(line + "\n");
            }
        }

        if (!employeeFile.delete()) {
            throw new IOException("Could not delete file");
        }
        if (!tempFile.renameTo(employeeFile)) {
            throw new IOException("Could not rename file");
        }

        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                emp.setDaysPresent(daysPresent);
                emp.setDaysAbsent(daysAbsent);
                break;
            }
        }
    }

    public void addCommentsToFile(String id, String comments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFile, true))) {
            writer.write("ID: " + id + "\nComments: " + comments + "\n\n");
        }
    }

    public String viewComments(String id) throws IOException {
        StringBuilder comments = new StringBuilder();
        boolean found = false;

        if (feedbackFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(feedbackFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("ID: " + id)) {
                        found = true;
                        comments.append(line).append("\n");
                        while ((line = reader.readLine()) != null && !line.startsWith("ID: ")) {
                            comments.append(line).append("\n");
                        }
                    }
                }
            }
        }

        return found ? comments.toString() : "No comments found for ID: " + id;
    }
}