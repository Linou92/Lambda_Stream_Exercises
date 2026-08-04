package se.lexicon;

import se.lexicon.lambda.Person;
import se.lexicon.lambda.PersonAction;
import se.lexicon.lambda.PersonRule;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true)
        );

        IO.println("\n --- All Persons ---");

        people.forEach(person -> IO.println("Person : " + person));

        // Rules (lambdas) for: active, adult (age 18 or above), and lives in Stockholm
        PersonRule isActive = Person::isActive;
        PersonRule age = person -> person.getAge() >= 18;
        PersonRule city = person -> person.getCity().equals("Stockholm");

        // Filtered people
        IO.println("\n --- Filter persons ---");
        IO.println("Active people: " + filterPeople(people, isActive));
        IO.println("Adult people: " + filterPeople(people, age));
        IO.println("People living in Stockholm: " + filterPeople(people, city));

        // Combine rules
        /*PersonRule isActiveAndAdultAndStockholm = isActive.and(age).and(city);

        IO.println("\n --- Combined filters persons ---");
        IO.println("Filtered people: " + filterPeople(people, isActiveAndAdultAndStockholm));*/

        // Print name and send email
        PersonAction printName = person -> IO.println(person.getName());
        PersonAction sendEmail = person -> IO.println("Send email to " + person.getName());
        List<Person> activePeople = filterPeople(people, isActive);
        IO.println("\n --- Send email to active people ---");
        for (Person person : activePeople) {
            sendEmail.perform(person);
        }
    }

    public static List<Person> filterPeople(List<Person> people, PersonRule rule) {
        List<Person> filteredPeople = new ArrayList<>();
        for (Person person : people) {
            if(rule.matches(person)) {
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }

}
