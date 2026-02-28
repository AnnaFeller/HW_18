package HW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Calculator {
    public static void main(String[] args) {
        System.out.println(mapOperation.get("-").apply(-8,6));
    }
    public static Map<String, BinaryOperator<Integer>> mapOperation;
    static {
        mapOperation = new HashMap<>();
        mapOperation.put("+", (a, b) -> a + b);
        mapOperation.put("-", (a, b) -> a - b);
        mapOperation.put("*", (a, b) -> a * b);
        mapOperation.put("/", (a, b) -> a / b);
    }

    public static Integer computeExpression(String str) {

        str = str.replaceAll("\\s+", "");

        if (!str.matches("-?\\d+([+\\-*/]-?\\d+)*")) {
            throw new IllegalArgumentException("error");
        }

        String[] numbers = str.split("[+\\-*/]");

        List<String> operators = new ArrayList<>();
        for (char c : str.toCharArray()) {
            if ("+-*/".indexOf(c) >= 0) {
                operators.add(String.valueOf(c));
            }
        }

        int res = Integer.parseInt(numbers[0]);

        for (int i = 0; i < operators.size(); i++) {
            String operator = operators.get(i);
            int nextNumber = Integer.parseInt(numbers[i + 1]);

            if (operator.equals("/") && nextNumber == 0) {
                throw new ArithmeticException("division by 0");
            }

            BinaryOperator<Integer> op = mapOperation.get(operator);
            res = op.apply(res, nextNumber);
        }

        return res;
    }

}
