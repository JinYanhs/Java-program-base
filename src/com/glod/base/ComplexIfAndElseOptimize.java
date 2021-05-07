package com.glod.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**


/**
 * @Description 复杂if/else优化
 *
 * @Author glod
 * @Date 2021/5/7 
 * @Modifier
 * @Modified Date
 * @Version 1.0
 */
public class ComplexIfAndElseOptimize {
    public static void main(String[] args) {
        System.out.println(optimizeChooseFruit(2));
        System.out.println( rawChooseFruit(2));

    }

    /**
     *  优化后/滑稽
     * @param choosenFruitId
     * @return
     */
    private static String optimizeChooseFruit(Integer choosenFruitId) {
        Map<Integer,Supplier<String>> supplierMap = new HashMap<>();
        /**
         *  需求是所有不满足1和2的都要走melonSupplier这个逻辑，于是需要Optional的orElse
         */
        choosenFruitId =  Optional.ofNullable(choosenFruitId).filter(c->c==1||c==2).orElse(3);
        /**
         * @FunctionalInterface
         * public interface Supplier<T> {
         *
         *      * Gets a result.
         *      *
         *      * @return a result
         *      T get ();
         *}
         *  suppler正好是一个供给型接口，刚好满足我们的业务需求，然后我们就可以把我们的代码做如下改动
         **/
        Supplier<String> bananaSupplier = ()-> "banana";
        Supplier<String> appleSupplier = ()-> "apple";
        Supplier<String> melonSupplier = ()-> "melon";

        supplierMap.put(1,bananaSupplier);
        supplierMap.put(2,appleSupplier);
        supplierMap.put(3,melonSupplier);

        return supplierMap.get(choosenFruitId).get();
    }
    /**
     *  原始if/else
     * @param choosenFruitId
     * @return
     */
    private static String rawChooseFruit(Integer choosenFruitId){
        if(choosenFruitId==1){
            return "banana";
        }else if(choosenFruitId==2){
            return "apple";
        }else {
            return "melon";
        }
    }


}
