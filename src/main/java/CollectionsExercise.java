import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.addAll;

public class CollectionsExercise {

    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();

//        Collection<String> populatedList = readInValues(list);
//        System.out.println(populatedList);
//        Collection<String> populatedSet = readInUnique(set);
//        System.out.println(populatedSet);

        Path path = Paths.get("pesele.i.nazwiska.txt");
        Path path2 = Paths.get("pesele.i.powtorzone.nazwiska.txt");

        Map<String,String> peopleMap = readInPersonsFromFile(path);

        System.out.println(peopleMap);

        Map<String,String> peopleRepeatMap = readInPersonsFromFile(path2);

        System.out.println(getPersonByPesel("85063077831",peopleMap));
        System.out.println(getPeselsByName("Sara Lewandowska",peopleRepeatMap));


        System.out.println("\n_____________Nazwy Kraju | Kod Kraju__________________");
        Path krajKodPath = Paths.get("kraje.txt");

        BiMap<String,String> krajKodBiDir = HashBiMap.create();
        krajKodBiDir.putAll(readInPairFromFile(krajKodPath));

        System.out.println(krajKodBiDir.get("Poland"));
        System.out.println(krajKodBiDir.inverse().get("GB"));


        //todo finish homework




    }

    public static Map<String,String> readInPairFromFile(Path path) throws IOException {
        Map<String,String> pairsMap;

        pairsMap = Files.lines(path)
                .map(l-> l.split(" "))
                .collect(Collectors.toMap(t -> t[0], t->t[1]));

        return pairsMap;
    }

    public static Map<String,String> getPeselsByName(String name, Map<String,String> personMap){
        Map<String,String> sameNameMap = new HashMap<>();
        for(String pesel : personMap.keySet()){
            if(personMap.get(pesel).equals(name)){
                sameNameMap.put(pesel, name);
                System.out.println("Inside getPeselsByName : " + sameNameMap.put(pesel, name));
            }
        }
        return sameNameMap;
    }



    public static String getPersonByPesel(String pesel ,Map<String,String> personMap){
        return personMap.get(pesel);
    }

    public static Map<String,String> readInPersonsFromFile(Path path) throws IOException {
        Map<String,String> namesAndPesel = new HashMap<>();

        namesAndPesel = Files.lines(path)
                .map(l-> l.split(" "))
                .collect(Collectors.toMap(t -> t[0], t->t[1] + " " + t[2]));

        return namesAndPesel;
    }

    public static Collection<String> readInValues(Collection<String> collection){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imie: ('-' aby zakonczyc)");
        String input;

        while(!(input = scanner.nextLine()).equals("-")){
            collection.add(input);
        }

        return collection;
    }

    public static Collection<String> readInUnique(Collection<String> set){
        return readInValues(set);
    }




}
