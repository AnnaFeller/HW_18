import java.util.*;
import java.util.function.BiFunction;

public class MapAdvAppl {

	public static void main(String[] args) {
		// map.compute(K key, BiFunction<K,V,V> changeMapFunction)
		// if k== null -> new entry
		// k exist and vNew!=null-> vOld-> vNew (like put)
		// vOld!=null vNew==null-> entry delete (like remove)
		// if exception - no changes

		Map<String, Integer> months = new HashMap<>();
		fillMap(months);
		System.out.println(months);
//		months.compute("jan", new BiFunction<String, Integer, Integer>() {
//
//			@Override
//			public Integer apply(String t, Integer u) {
//
//				return u * 100;
//			}
//		});
//		System.out.println(months);
//		months.compute("dec", new BiFunction<String, Integer, Integer>() {
//
//			@Override
//			public Integer apply(String t, Integer u) {
//
//				return 100;
//			}
//		});
//		System.out.println(months);
//		months.compute("dec", new BiFunction<String, Integer, Integer>() {
//
//			@Override
//			public Integer apply(String t, Integer u) {
//
//				return null;
//			}
//		});
//		System.out.println(months);

		// map.computeIfPresent(K key, BiFunction<K,V,V> changeMapFunction)
		// if k== null -> new entry
		// k exist and vNew!=null-> vOld-> vNew no change
		// vOld!=null vNew==null-> entry delete (like remove)
		// if exception - no changes
		months.computeIfPresent("jan", new BiFunction<String, Integer, Integer>() {

			@Override
			public Integer apply(String t, Integer u) {

				return u * 100;
			}
		});
		System.out.println(months);
		months.computeIfPresent("dec", new BiFunction<String, Integer, Integer>() {

			@Override
			public Integer apply(String t, Integer u) {

				return 100;
			}
		});
		System.out.println(months);
		months.computeIfPresent("jan", new BiFunction<String, Integer, Integer>() {

			@Override
			public Integer apply(String t, Integer u) {

				return null;
			}
		});
		System.out.println(months);

        //computeIfAbsent
        //Если ключа нет → создать значение
        //Если ключ есть → ничего не делать
        Map<String, Integer> map = new HashMap<>();
        map.computeIfAbsent("A", key -> 100);
        System.out.println(map);
        //Ключа "A" нет
        //Функция вызывается
        //В map кладётся: A → 100
        map.computeIfAbsent("A", key -> 500);
        System.out.println(map);
        //"A" уже есть
        //Функция НЕ вызывается
        //Значение остаётся прежним

        //bifunction new and old
        //function new

        System.out.println(monthReverse(months));
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("meat",10);
        map1.put("bread",10);
        map1.put("milk",5);
        map1.put("cheese",3);
        map1.put("apple",3);
        System.out.println(monthReverse(map1)); //integer->collection<string>


    }





	private static void fillMap(Map<String, Integer> months) {
		months.put("jan", 1);
		months.put("feb", 2);
		months.put("march", 3);
		months.put("april", 4);
	}

//public static Map<Integer,String> monthReverse(Map<String,Integer> months ) {
// Map<Integer,String> reverese = new HashMap<>();
// for(String month : months.keySet()){
//     Integer number = months.get(month);
//     reverese.put(number,month);
// }
// return reverese;
//}


    public static Map<Integer, Collection<String>> monthReverse(Map<String,Integer> map ) {
        Map<Integer , Collection<String>> res = new HashMap<>();
        Set<String> keys = map.keySet();
        for(String key : keys){
            Integer v = map.get(key);
           res.compute(v, new BiFunction<Integer, Collection<String>, Collection<String>>() {
               @Override
               public Collection<String> apply(Integer integer, Collection<String> u) {
                   if (u == null)
                       u = new HashSet<>();
                   u.add(key);
                   return u;
               }
           });
        }
        return res;
    }

}
