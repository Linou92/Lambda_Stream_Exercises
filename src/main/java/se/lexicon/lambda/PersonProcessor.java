package se.lexicon.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PersonProcessor {

    public static List<Person> findPeople(List<Person> people, Predicate<Person> rule) {
        List<Person> result = new ArrayList<>();

        people.forEach(person -> {
            if (rule.test(person)) {
                result.add(person);
            }
        });
        return result;
    }

    public static void applyToMatching(List<Person> people, Predicate<Person> rule, Consumer<Person> action) {
        people.forEach(person -> {
            if (rule.test(person)) {
                action.accept(person);
            }
        });
    }
}
