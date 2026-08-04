package se.lexicon;

import se.lexicon.lambda.Person;
import se.lexicon.lambda.PersonRule;

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
        PersonRule isActive = person -> person.isActive();
        PersonRule age = person -> person.getAge() >= 18;
        PersonRule city = person -> person.getCity().equals("Stockholm");

    }


}
