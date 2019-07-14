import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExerciseMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] splitInput = new String[0];
        String regex = new String("([A-Z][a-z]*) ([A-Z][a-z]*)");
        // todo implement regex for any number of names
        // ([A-Z][a-z])+ // like this ?

        System.out.print("Podaj imie i nazwisko:");
        input = scanner.nextLine();
        System.out.println("Input check : " + input);

        if (input.matches(regex)) {
            splitInput = input.split(" ");
        } else {
            System.out.println("No match");
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        System.out.print("regex on string :");
        printName(splitInput);

        if(matcher.lookingAt()) {
            String name = matcher.group(1);
            String surname = matcher.group(2);

            System.out.println("regex on Pattern/Matcher");
            System.out.println(name + " " + surname);
        }

    }

    public static void printName(String[] names) {
        for (String item : names) {
            System.out.print(item + " ");

        }
        System.out.println();
    }


}
