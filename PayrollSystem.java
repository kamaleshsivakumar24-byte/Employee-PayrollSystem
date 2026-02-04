import java.util.*;

// ---------- Abstract Employee Class ----------
abstract class Employee {
    protected int id;
    protected String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Abstract method (Polymorphism)
    public abstract double calculateSalary();

    public void displayEmployee() {
        System.out.println("ID   : " + id);
        System.out.println("Name : " + name);
        System.out.println("Salary: ₹" + calculateSalary());
    }
}

// ---------- Full Time Employee ----------
class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(int id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

// ---------- Part Time Employee ----------
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

// ---------- Payroll System ----------
class PayrollService {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
        System.out.println("Employee added successfully!\n");
    }

    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.\n");
            return;
        }

        for (Employee emp : employees) {
            System.out.println("----------------------------");
            emp.displayEmployee();
        }
        System.out.println();
    }

    public void removeEmployee(int id) {
        Iterator<Employee> iterator = employees.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.id == id) {
                iterator.remove();
                found = true;
                System.out.println("Employee removed successfully!\n");
                break;
            }
        }

        if (!found) {
            System.out.println("Employee not found.\n");
        }
    }
}

// ---------- Main Class ----------
public class PayrollSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollService payroll = new PayrollService();

        while (true) {
            System.out.println("===== EMPLOYEE PAYROLL SYSTEM =====");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Display All Employees");
            System.out.println("4. Remove Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int ftId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String ftName = sc.nextLine();

                    System.out.print("Enter Monthly Salary: ");
                    double salary = sc.nextDouble();

                    payroll.addEmployee(
                            new FullTimeEmployee(ftId, ftName, salary)
                    );
                    break;

                case 2:
                    System.out.print("Enter ID: ");
                    int ptId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String ptName = sc.nextLine();

                    System.out.print("Enter Hours Worked: ");
                    int hours = sc.nextInt();

                    System.out.print("Enter Hourly Rate: ");
                    double rate = sc.nextDouble();

                    payroll.addEmployee(
                            new PartTimeEmployee(ptId, ptName, hours, rate)
                    );
                    break;

                case 3:
                    payroll.displayAllEmployees();
                    break;

                case 4:
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = sc.nextInt();
                    payroll.removeEmployee(removeId);
                    break;

                case 5:
                    System.out.println("Thank you for using Payroll System!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }
}
