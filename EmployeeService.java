import java.io.*;
import java.util.*;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private static final String FILE_NAME = "employees.dat";

    public EmployeeService() {
        loadFromFile();
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
        saveToFile();
        System.out.println("✅ Employee added successfully.");
    }

    public void viewAll() {
        if (employees.isEmpty()) {
            System.out.println("⚠️ No employees found.");
            return;
        }
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public void updateEmployee(int id, String name, String dept, double salary) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                e.setName(name);
                e.setDepartment(dept);
                e.setSalary(salary);
                saveToFile();
                System.out.println("✅ Employee updated.");
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    public void deleteEmployee(int id) {
        Iterator<Employee> it = employees.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                saveToFile();
                System.out.println("🗑️ Employee deleted.");
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    public void searchById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.println("🔎 Found: " + e);
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            employees = (List<Employee>) ois.readObject();
        } catch (Exception e) {
            employees = new ArrayList<>(); // first time file not found
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("❌ Error saving data.");
        }
    }
}
