import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapAdv {
    public static void main(String[] args) {
        //map.compute(K key , BiFunction<K,V,V>) V = значени и значение
        // change MapFunction
        //значение вычесляемое

        //Возьми старое значение, пересчитай его и положи новое обратно
        //(key, староеЗначение)
        //? : = if else
        //v == null ? 1 : v + 1
        //Если v == null, вернуть 1,иначе вернуть v + 1.
        //=======================================================

        Map<String , Integer> months = new HashMap<>();
        fillMap(months);
        System.out.println(months);
        months.compute("jan", new BiFunction<String, Integer, Integer>(){
            @Override
            public Integer apply(String t, Integer u){
                return u+100;
            }
        });
    }

    private static void fillMap(Map<String, Integer> months) {
        months.put("jan",1);
        months.put("feb",2);
        months.put("march",3);
        months.put("april",4);




    }
}
