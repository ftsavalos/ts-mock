package gr.eurobank.transactions.esbmock;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        MyFunction myFunction = function -> function.apply(5);
        Integer method = myFunction.method(x -> x + 6);


        MyFunctionalInterface myFunctionalInterface = x -> integer -> x + integer;
        Integer result = myFunctionalInterface.method(10).apply(15);

        Function<Function<Integer, Integer>, Integer> resultXY = integerIntegerFunction -> integerIntegerFunction.apply(6);

        System.out.println(result);
        System.out.println(method);
        System.out.println(resultXY.apply(integer -> integer + 4));

        Function<Integer, Integer> integerIntegerFunction = integer -> integer + 6;

        Integer apply = integerIntegerFunction.andThen(i -> i + 4).apply(3);
        System.out.println(apply);

        Date from = Date.from(LocalDate.of(2014, 2, 11).atStartOfDay(ZoneId.of("Europe/Athens")).toInstant());

        System.out.println(from);


        Function<Integer, Integer> function = (x) -> x + 7;

        System.out.println(function.andThen(t -> t + 5).apply(6));

    }
}

interface MyFunction {

    Integer method(Function<Integer, Integer> function);
}

interface MyFunctionalInterface {
    Function<Integer, Integer> method(Integer x);
}
