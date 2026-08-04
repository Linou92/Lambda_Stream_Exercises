package se.lexicon.lambda;

import java.util.ArrayList;
import java.util.List;

public class PersonProcessor {

    public static List<Person> findPeople(List<Person> people, PersonRule rule) {
        List<Person> result = new ArrayList<>();

        people.forEach(person -> {
            if (rule.matches(person)) {
                result.add(person);
            }
        });
        return result;
    }

    public static void applyToMatching(List<Person> people, PersonRule rule, PersonAction action) {
        people.forEach(person -> {
            if (rule.matches(person)) {
                action.perform(person);
            }
        });
    }
}
