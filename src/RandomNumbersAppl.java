
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class RandomNumbersAppl {
	private static final int N_NUMBERS = 100;
	private static final int MAX_RANDOM = 10;
 //merge
	public static void main(String[] args) {
		Map<Integer, Integer> randomNumbers = new TreeMap<>();
		Random r = new Random();

		for (int i = 0; i < N_NUMBERS; i++) {
			int value = r.nextInt(1, MAX_RANDOM + 1); // число от 1 до MAX_RANDOM
			int count = randomNumbers.getOrDefault(value, 0);
			randomNumbers.put(value, count + 1); // только put
		}
		for (int i = 1; i <= MAX_RANDOM; i++) {
		    randomNumbers.putIfAbsent(i, 0);
		}

		System.out.println(randomNumbers);
	}

}