package composite;

public class Developer implements Employee{
    float salary;
    String name;
    String[] roles;

    public Developer(String name, float salary) {
        this.salary = salary;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getSalary() {
        return salary;
    }

    @Override
    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String[] getRoles() {
        return roles;
    }
}
