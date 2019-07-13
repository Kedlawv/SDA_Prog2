import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Exercise1 {
    public static void main(String[] args) {
        List<Integer> testList = removeEven(generateList(0, 99));
        List<Integer> squaredList = squareList(testList);
        List<Integer> cutOffList = cutOff(squaredList, 701);
        Map<Integer, String> mappedList = mapSquareToString(cutOffList);

        System.out.println(testList);
        System.out.println(squaredList);
        System.out.println(cutOffList);
        System.out.println(mappedList);

        List<Integer> streamList = testList.stream().filter(p -> p % 2 == 1)
                .map(p -> p * p).collect(Collectors.toList());


    }

    public static List<Integer> generateList(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            result.add(i);
        }

        return result;
    }

    public static List<Integer> removeEven(List<Integer> list) {
        List<Integer> result = new ArrayList<>();

        for (int num : list) {
            //num % 2 == 0 ? continue : result.add(num);
            if (num % 2 == 0) {
                continue;
            } else {
                result.add(num);
            }
        }

        return result;

    }

    public static List<Integer> squareList(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int num : list) {
            result.add(num * num);
        }
        return result;
    }

    public static List<Integer> cutOff(List<Integer> list, int cutOff) {
        List<Integer> result = new ArrayList<>();

        for (int num : list) {
            if (num > cutOff) {
                break;
            } else {
                result.add(num);
            }
        }
        return result;
    }

    public static Map<Integer, String> mapSquareToString(List<Integer> list) {
        Map<Integer, String> result = new HashMap<>();
        for (int num : list) {
            result.put(num, num + "");
        }

        return result;
    }
}
