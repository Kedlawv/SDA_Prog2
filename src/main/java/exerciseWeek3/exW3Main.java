package exerciseWeek3;

import org.apache.commons.math3.primes.Primes;

import java.util.stream.IntStream;

public class exW3Main {
    public static void main(String[] args) {
        System.out.println(getSquareOfPrimes(200,1200,3));

    }

    public static long getSquareOfPrimes(int lower, int upper, int endingWith){
        long result = IntStream
                .rangeClosed(lower,upper)
                .filter(Primes::isPrime)
                .filter(i -> i % 10 == endingWith)
                .mapToLong(i -> i)
                .reduce(0,(a,b) -> a + b*b);
        return result;
    }
}


