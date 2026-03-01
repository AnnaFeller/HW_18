package HW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

public class Calculator {
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
        if (str.startsWith("-")) {
            str = "0" + str;}

        String[] parts = str.split("[+\\-*/]");
        List<Integer> num = new ArrayList<>();
        for (String p : parts) {
            if (!p.isEmpty()) {
                num.add(Integer.parseInt(p));
            }
        }
        List<String> operators = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ("+-*/".indexOf(c) >= 0 && i > 0) {
                operators.add(String.valueOf(c));
            }
        }

        for (int i = 0; i < operators.size(); i++) {
            String op = operators.get(i);
            if (op.equals("*") || op.equals("/")) {
                int res = mapOperation.get(op).apply(num.get(i), num.get(i + 1));
                num.set(i, res);
                num.remove(i + 1);
                operators.remove(i);
                i--;
            }
        }

        // Теперь в списках остались только + и -
        int result = num.get(0);
        for (int i = 0; i < operators.size(); i++) {
            result  = mapOperation.get(operators.get(i)).apply(result, num.get(i + 1));
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(computeExpression("-10-9+3"));
    }
}