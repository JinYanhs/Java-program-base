##Generics Usage##
----------------------------
1、泛型三种使用方式：泛型类、泛型接口、泛型方法

## [泛型类（实例化时候指定泛型）](/src/com/glod/generics/Generic.java)
    泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口。最典型的就是各种容器类，如：List、Set、Map。

    泛型类的最基本写法（这么看可能会有点晕，会在下面的例子中详解）：
    
 ```
    class 类名称 <泛型标识：可以随便写任意标识号，标识指定的泛型的类型>{
      private 泛型标识 /*（成员变量类型）*/ var; 
      .....
    
      }
    }
``` 
## [泛型接口](/src/com/glod/generics/Generator.java)
泛型接口与泛型类的定义及使用基本相同。泛型接口常被用在各种类的生产器中

可以解决当具体类型不确定的时候，这个通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。

## [泛型方法（调用时候指定泛型）](/src/com/glod/generics/GenericTest.java)
泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型

## [类中的泛型方法](/src/com/glod/generics/GenericFruit.java)

> [泛型方法与可变参数](/src/com/glod/generics/GenericMethodAndArgsTest.java)

> [静态方法与泛型](/src/com/glod/generics/StaticGenerator.java)

静态方法有一种情况需要注意一下，那就是在类中的静态方法使用泛型：静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
即：如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。

>> 泛型方法总结

泛型方法能使方法独立于类而产生变化，以下是一个基本的指导原则：
```
无论何时，如果你能做到，你就该尽量使用泛型方法。也就是说，如果使用泛型方法将整个类泛型化，
那么就应该使用泛型方法。另外对于一个static的方法而已，无法访问泛型类型的参数。
所以如果static方法要使用泛型能力，就必须使其成为泛型方法。
```
##  泛型上下边界
在使用泛型的时候，我们还可以为传入的泛型类型实参进行上下边界的限制，如：类型实参只准传入某种类型的父类或某种类型的子类。

为泛型添加上边界，即传入的类型实参必须是指定类型的子类型< ? extends XXClass>

泛型的上下边界添加，必须与泛型的声明在一起 。

## 泛型数组
在java中是”不能创建一个确切的泛型类型的数组”的。

也就是说下面的这个例子是不可以的：
```
List<String>[] ls = new ArrayList<String>[10];  
```
而使用通配符创建泛型数组是可以的，如下面这个例子：
```
List<?>[] ls = new ArrayList<?>[10]; 
```
这样也是可以的：
```
List<String>[] ls = new ArrayList[10];
```
>example
```
List<String>[] lsa = new List<String>[10]; // Not really allowed.    
Object o = lsa;    
Object[] oa = (Object[]) o;    
List<Integer> li = new ArrayList<Integer>();    
li.add(new Integer(3));    
oa[1] = li; // Unsound, but passes run time store check    
String s = lsa[1].get(0); // Run-time error: ClassCastException.
```

>
 这种情况下，由于JVM泛型的擦除机制，在运行时JVM是不知道泛型信息的，所以可以给oa[1]赋上一个ArrayList而不会出现异常，
 但是在取出数据的时候却要做一次类型转换，所以就会出现ClassCastException，如果可以进行泛型数组的声明，
 上面说的这种情况在编译期将不会出现任何的警告和错误，只有在运行时才会出错。
 而对泛型数组的声明进行限制，对于这样的情况，可以在编译期提示代码有类型安全问题，比没有任何提示要强很多。
 
 >> 采用通配符的方式是被允许的:数组的类型不可以是类型变量，除非是采用通配符的方式，因为对于通配符的方式，最后取出数据是要做显式的类型转换的。
