package se.lexicon.lambda;

@FunctionalInterface
public interface PersonRule {

    boolean matches(Person person);
}
