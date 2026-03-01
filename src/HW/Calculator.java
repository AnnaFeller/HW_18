package HW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static Map<String, BinaryOperator<Integer>> mapOperations;

    static {
        mapOperations = new HashMap<>();
        mapOperations.put("+", (a, b) -> a + b);
        mapOperations.put("-", (a, b) -> a - b);
        mapOperations.put("*", (a, b) -> a * b);
        mapOperations.put("/", (a, b) -> a / b);
    }

    public static Integer computeExpression(String str) {
        str = str.replaceAll("\\s+", "");

        if (!str.matches("-?\\d+([+\\-*/]-?\\d+)*")) {
            throw new IllegalArgumentException("error");
        }

        List<Integer> nums = new ArrayList<>();
        List<String> ops = new ArrayList<>();

        Matcher m = Pattern.compile("-?\\d+").matcher(str);
        while (m.find()) nums.add(Integer.parseInt(m.group()));

        Matcher mOp = Pattern.compile("(?<=\\d)[+\\-*/]").matcher(str);
        while (mOp.find()) ops.add(mOp.group());

        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if (op.equals("*") || op.equals("/")) {
                int res = mapOperations.get(op).apply(nums.get(i), nums.get(i + 1));
                nums.set(i, res);
                nums.remove(i + 1);
                ops.remove(i);
                i--;
            }
        }

        int finalRes = nums.get(0);
        for (int i = 0; i < ops.size(); i++) {
            finalRes = mapOperations.get(ops.get(i)).apply(finalRes, nums.get(i + 1));
        }

        return finalRes;
    }

    public static void main(String[] args) {
        System.out.println(computeExpression("34-2*5/4"));
    }
}