import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExerciseMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] splitInput = new String[0];
        String regexNameSurname = new String("([A-Z][a-z]*) ([A-Z][a-z]*)");
        String regexArbitrary = new String("([A-Z][a-z]* *){2,}");
        String regexSingleName = new String("([A-Z][a-z]*)");
        // todo implement regex for any number of names
        // ([A-Z][a-z])+ // like this ?

        System.out.print("Podaj imie i nazwisko:");
        input = scanner.nextLine();
        System.out.println("Input check : " + input);

        if (input.matches(regexArbitrary)) {
            splitInput = input.split(" ");
        } else {
            System.out.println("No match");
        }

        System.out.print("regex on string :");
        printName(splitInput);

        System.out.println("\n___________Using Pattern|Matcher_____________");

        Pattern patternArbitraryNoOffNames = Pattern.compile(regexArbitrary);
        Matcher matcherVerifier = patternArbitraryNoOffNames.matcher(input);

        Pattern patternSingleName = Pattern.compile(regexSingleName);
        Matcher matcherNameFinder = patternSingleName.matcher(input);


        List<String> namesList = new ArrayList<>();

        if(matcherVerifier.matches()) {
            while (matcherNameFinder.find()) { // find is an iterator
                namesList.add(matcherNameFinder.group()); // group returns last match
            }
        }else{
            System.out.println("Sequance is not a list of names");
            System.exit(0);
        }

        String firstName = "";
        String surname = "";
        List<String> middleNames = new ArrayList<>();

        if(namesList.size()>2) {
            for (int i = 0; i < namesList.size(); i++) {
                if(i==0){
                    firstName = namesList.get(i);
                }else if(i == namesList.size()-1){
                    surname = namesList.get(i);
                }else{
                    middleNames.add(namesList.get(i));
                }
            }
        }

        System.out.println(namesList);
        System.out.println("First Name: " + firstName + " ");
        System.out.print("Middle name(s): ");
        for (String middleName : middleNames) {
            System.out.print(middleName + " ");
        }
        System.out.println();
        System.out.println("Surname: " + surname);

    }



    public static void printName(String[] names) {
        for (String item : names) {
            System.out.print(item + " ");

        }
        System.out.println();
    }


}
