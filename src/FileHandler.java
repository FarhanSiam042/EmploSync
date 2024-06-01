import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private File employeeFile;
    private File feedbackFile;

    public FileHandler() {
        employeeFile = new File("employeedetails.txt");
        feedbackFile = new File("feedback.txt");
    }

    public List<String> readEmployeeDetails() throws IOException {
        List<String> employeeDetails = new ArrayList<>();
        if (employeeFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    employeeDetails.add(line);
                }
            }
        }
        return employeeDetails;
    }

    public void addEmployeeToFile(String name, String id, String position, String salary, String daysPresent, String daysAbsent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(employeeFile, true))) {
            writer.write(name + "," + id + "," + position + "," + salary + "," + daysPresent + "," + daysAbsent + "\n");
        }
    }

    public boolean removeEmployeeFromFile(String id) throws IOException {
        File tempFile = new File("employeedetails_temp.txt");
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (!details[1].equals(id)) {
                    writer.write(line + "\n");
                } else {
                    found = true;
                }
            }
        }

        if (found) {
            // Close readers/writers before attempting to delete the file
            System.gc();  // Suggest garbage collection to close any lingering file handles
            if (!employeeFile.delete()) {
                throw new IOException("Could not delete file");
            }

            // Add a retry mechanism for renaming the file
            for (int i = 0; i < 5; i++) {
                if (tempFile.renameTo(employeeFile)) {
                    return true;
                }
                try {
                    Thread.sleep(100);  // Wait a bit before retrying
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted while trying to rename file", e);
                }
            }

            throw new IOException("Could not rename file");
        } else {
            // Clean up temp file if not found
            tempFile.delete();
        }

        return found;
    }

    public void updateEmployeeAttendance(String id, int daysPresent, int daysAbsent) throws IOException {
        File tempFile = new File("employeedetails_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[1].equals(id)) {
                    details[4] = String.valueOf(daysPresent);
                    details[5] = String.valueOf(daysAbsent);
                    line = String.join(",", details);
                }
                writer.write(line + "\n");
            }
        }

        // Close readers/writers before attempting to delete the file
        System.gc();  // Suggest garbage collection to close any lingering file handles
        if (!employeeFile.delete()) {
            throw new IOException("Could not delete file");
        }

        // Add a retry mechanism for renaming the file
        for (int i = 0; i < 5; i++) {
            if (tempFile.renameTo(employeeFile)) {
                return;
            }
            try {
                Thread.sleep(100);  // Wait a bit before retrying
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IOException("Interrupted while trying to rename file", e);
            }
        }

        throw new IOException("Could not rename file");
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
