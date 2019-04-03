import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Temp {

    public static void main(String[] args) {
        List<Double> doubles = new ArrayList<>();
        doubles.add(10.0);
        doubles.add(20.0);
        doubles.add(30.0);
        doubles.add(5.0);
        doubles.add(15.0);

        System.out.println(getMinValue(doubles));


    }

    public static Double getMinValue(List<Double> list) {
        OptionalDouble result = list.stream()
                .mapToDouble(a -> a)
                .reduce((a, b) -> {
                    if (a < b) {
                        return a;
                    }
                    return b;
                });
        return result.getAsDouble();
    }

}
