package se.lexicon.lambda;

@FunctionalInterface
public interface PersonRule {

    boolean matches(Person person);

    /*default PersonRule and(PersonRule other) {
        return person -> this.matches(person) && other.matches(person);
    }*/
}
