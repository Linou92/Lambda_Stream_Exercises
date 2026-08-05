package se.lexicon;

import se.lexicon.lambda.Person;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static se.lexicon.lambda.PersonProcessor.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    void main() {

        List<Person> people = List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true)
        );

        IO.println("\n --- All Persons ---");

        people.forEach(IO::println);

        // Rules (lambdas) for: active, adult (age 18 or above), and lives in Stockholm
        /*PersonRule isActive = Person::isActive;
        PersonRule isAdult = person -> person.getAge() >= 18;
        PersonRule livesInStockholm = person -> person.getCity().equals("Stockholm");*/
        Predicate<Person> isActive = Person::isActive;
        Predicate<Person> isAdult = person -> person.getAge() >= 18;
        Predicate<Person> livesInStockholm = person -> person.getCity().equals("Stockholm");

        // Filtered people
        IO.println("\n --- Filter persons ---");
        IO.println("\nActive people: ");
        findPeople(people, isActive).forEach(IO::println);
        IO.println("\nAdult people: ");
        findPeople(people, isAdult).forEach(IO::println);
        IO.println("\nPeople living in Stockholm: ");
        findPeople(people, livesInStockholm).forEach(IO::println);

        // Combine rules
        /*PersonRule isActiveAndIsAdult = isActive.and(isAdult);
        PersonRule isAdultOrLivesInStockholm = isAdult.or(livesInStockholm);
        PersonRule notActive = isActive.negate();*/
        Predicate<Person> isActiveAndIsAdult = isActive.and(isAdult);
        Predicate<Person> isAdultOrLivesInStockholm = isAdult.or(livesInStockholm);
        Predicate<Person> notActive = isActive.negate();

        IO.println("\n --- Combined filters persons ---");
        IO.println("\nActive and adult : ");
        findPeople(people, isActiveAndIsAdult).forEach(IO::println);
        IO.println("\nAdult or lives in Stockholm : ");
        findPeople(people, isAdultOrLivesInStockholm).forEach(IO::println);
        IO.println("\nNot active : ");
        findPeople(people, notActive).forEach(IO::println);

        // Print name and send email
        /*PersonAction printName = person -> IO.println(person.getName());
        PersonAction sendEmail = person -> IO.println("Send email to " + person.getName());*/
        Consumer<Person> printName = person -> IO.println(person.getName());
        Consumer<Person> sendEmail = person -> IO.println("Send email to " + person.getName());
        List<Person> activePeople = findPeople(people, isActive);
        IO.println("\n --- Send email to active people ---");
        activePeople.forEach(sendEmail);

        IO.println("\n --- Print name of active and adult people ---");
        applyToMatching(people,isActiveAndIsAdult,printName);

        // Stream API
        List<Person> activePeople2 = people.stream()
                .filter(Person::isActive)
                .toList();
        IO.println("\n --- Print active people ---\n" + activePeople2);

        List<String> names = people.stream()
                .map(Person::getName)
                .toList();
        IO.println("\n --- Print all names ---\n" + names);

        long adultCount = people.stream()
                .filter(person -> person.getAge()>=18)
                .count();
        IO.println("\n --- Number of adults ---\n" + adultCount);

    }
}
