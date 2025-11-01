import java.util.*;
import java.util.stream.*;
import java.util.Map.Entry;

// ======= MODEL CLASSES =======

// Employee Class (for Part A)
class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + "\t" + age + "\t" + salary;
    }
}

// Student Class (for Part B)
class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() { return name; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return name + " - " + marks;
    }
}

// Product Class (for Part C)
class Product {
    String name;
    double price;
    String category;

    Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return name + " (" + category + ") - ₹" + price;
    }
}

// ======= MAIN DRIVER CLASS =======

public class LambdaAndStreamDemo {
    public static void main(String[] args) {

        System.out.println("\n===============================");
        System.out.println(" Part A: Sorting Employee Objects Using Lambda ");
        System.out.println("===============================");
        partA();

        System.out.println("\n===============================");
        System.out.println(" Part B: Filtering and Sorting Students Using Streams ");
        System.out.println("===============================");
        partB();

        System.out.println("\n===============================");
        System.out.println(" Part C: Stream Operations on Product Dataset ");
        System.out.println("===============================");
        partC();
    }

    // ---------- PART A ----------
    public static void partA() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Aman", 25, 50000));
        employees.add(new Employee("Riya", 22, 60000));
        employees.add(new Employee("Raj", 28, 55000));
        employees.add(new Employee("Neha", 26, 70000));

        System.out.println("Original List:");
        employees.forEach(System.out::println);

        // Sort by Name
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));
        System.out.println("\nSorted by Name:");
        employees.forEach(System.out::println);

        // Sort by Age
        employees.sort((e1, e2) -> Integer.compare(e1.age, e2.age));
        System.out.println("\nSorted by Age:");
        employees.forEach(System.out::println);

        // Sort by Salary (Descending)
        employees.sort((e1, e2) -> Double.compare(e2.salary, e1.salary));
        System.out.println("\nSorted by Salary (Descending):");
        employees.forEach(System.out::println);
    }

    // ---------- PART B ----------
    public static void partB() {
        List<Student> students = Arrays.asList(
                new Student("Aman", 80),
                new Student("Riya", 65),
                new Student("Raj", 90),
                new Student("Neha", 72),
                new Student("Karan", 88)
        );

        System.out.println("Students scoring above 75%, sorted by marks:");
        students.stream()
                .filter(s -> s.getMarks() > 75)
                .sorted(Comparator.comparingDouble(Student::getMarks))
                .map(Student::getName)
                .forEach(System.out::println);
    }

    // ---------- PART C ----------
    public static void partC() {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 80000, "Electronics"),
                new Product("Phone", 60000, "Electronics"),
                new Product("TV", 50000, "Electronics"),
                new Product("Shirt", 1500, "Clothing"),
                new Product("Jeans", 2000, "Clothing"),
                new Product("Refrigerator", 30000, "HomeAppliance"),
                new Product("Washing Machine", 25000, "HomeAppliance")
        );

        // 1️⃣ Group products by category
        System.out.println("\nGrouped by Category:");
        Map<String, List<Product>> groupByCategory =
                products.stream().collect(Collectors.groupingBy(Product::getCategory));
        groupByCategory.forEach((cat, list) -> {
            System.out.println(cat + ": " + list);
        });

        // 2️⃣ Most expensive product in each category
        System.out.println("\nMost Expensive Product per Category:");
        Map<String, Optional<Product>> maxPriceProduct =
                products.stream().collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
                ));
        maxPriceProduct.forEach((cat, prod) ->
                System.out.println(cat + ": " + prod.get().getName() + " - ₹" + prod.get().getPrice())
        );

        // 3️⃣ Average price of all products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        System.out.println("\nAverage Price of All Products: ₹" + avgPrice);
    }
}
