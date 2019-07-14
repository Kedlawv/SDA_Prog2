import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilePathExercise {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String userInput;

        Path path = Paths.get("test.txt");
        try {
            Files.write(path, "test".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // reading files from a working directory(where you are , relative path)
        path = Paths.get(".");

        try {
            Files.list(path).forEach(f -> System.out.println(f.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Enter String to search: ");

        path = Paths.get("LICENSE");

        //userInput = scanner.nextLine();

        String fileContents = new String(Files.readAllBytes(path));

        System.out.println(fileContents);

        findInFileAndWriteResultsInToFile(Paths.get("LICENSE"),
                Paths.get("searchResult.txt"), "Public");
        readFileAndPrintToConsole(Paths.get("searchResult.txt"));

    }

    public static void findInAllFilesInFolder(Path folder, String searchItem){

    }

    public static void findInFileAndWriteResultsInToFile(Path pathIn, Path pathOut,
                                                         String searchItem) throws IOException {
        if (!Files.exists(pathIn)) {
            System.out.println("No such file found");
            return;
        }
        String fileContents = new String(Files.readAllBytes(pathIn));

        Pattern pattern = Pattern.compile(searchItem);
        Matcher matcher = pattern.matcher(fileContents);

        if (!Files.exists(pathOut)) {
            Files.createFile(pathOut);
        }

        while (matcher.find()) {
            String inputString = String.format("I have found %s starting at %d and ending at %d%n",
                    matcher.group(), matcher.start(), matcher.end());
            Files.write(pathOut, inputString.getBytes(), StandardOpenOption.APPEND);

        }
    }

    public static void readFileAndPrintToConsole(Path path) {
        try {
            System.out.println("File: " + path);
            System.out.println(new String(Files.readAllBytes(path)));
        }catch(IOException e){

    }

}
}
