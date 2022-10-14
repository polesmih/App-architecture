import adapter.AfricanLion;
import adapter.Hunter;
import adapter.WildDog;
import adapter.WildDogAdapter;
import bridge.pages.About;
import bridge.pages.Careers;
import bridge.themes.DarkTheme;
import composite.Designer;
import composite.Developer;
import composite.Organization;
import decorator.MilkCoffee;
import decorator.SimpleCoffee;
import decorator.VanillaCoffee;
import decorator.WhipCoffee;
import facade.Computer;
import facade.ComputerFacade;
import proxy.LabDoor;
import proxy.SecuredDoor;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. Adapter:\n");
        WildDog wildDog = new WildDog();
        WildDogAdapter wildDogAdapter = new WildDogAdapter(wildDog);
        AfricanLion africanLion = new AfricanLion();
        Hunter hunter = new Hunter();
        hunter.hunt(africanLion);
        hunter.hunt(wildDogAdapter);

        System.out.println("\n2. Bridge:\n");
        DarkTheme darkTheme = new DarkTheme();
        About about = new About(darkTheme);
        Careers careers = new Careers(darkTheme);
        System.out.println(about.getContent());
        System.out.println(careers.getContent());

        System.out.println("\n3. Composite:\n");
        Developer john = new Developer("John Doe", 12000);
        Designer jane  = new Designer("Jane  Doe", 15000);
        Organization organization = new Organization();
        organization.addEmployee(jane);
        organization.addEmployee(john);
        System.out.println("Net salary: " + organization.getNetSalaries());

        System.out.println("\n4. Decorator:\n");
        SimpleCoffee coffee = new SimpleCoffee();
        System.out.println(coffee.getCost());
        System.out.println(coffee.getDescription());

        MilkCoffee milkCoffee = new MilkCoffee(coffee);
        System.out.println(milkCoffee.getCost());
        System.out.println(milkCoffee.getDescription());

        WhipCoffee whipCoffee = new WhipCoffee(milkCoffee);
        System.out.println(whipCoffee.getCost());
        System.out.println(whipCoffee.getDescription());

        VanillaCoffee vanillaCoffee = new VanillaCoffee(whipCoffee);
        System.out.println(vanillaCoffee.getCost());
        System.out.println(vanillaCoffee.getDescription());

        System.out.println("\n5. Facade:\n");
        ComputerFacade computer = new ComputerFacade(new Computer());
        computer.turnOn();
        computer.turnOff();

        System.out.println("\n6. Proxy\n");
        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("invalid");
        door.open("$ecr@t");
        door.close();
    }
}
