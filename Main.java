package annot11;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        TestClass testClass = new TestClass();
        fillPrivateFieldTestClass(testClass);
        System.out.println(testClass.getRandomInt());
    }



    public static void fillPrivateFieldTestClass(Object o){
        Class clas = o.getClass();
        Field[] fields = clas.getDeclaredFields();
        Random random = new Random();
        for (Field field : fields){
            Annotation annotation = field.getAnnotation(TestAnnot.class);
            if ((annotation != null) && (field.getName().equals("randomInt"))){
                //System.out.println(field.getName());
                field.setAccessible(true);
                try {
                field.set(o,random.nextInt());
                } catch (IllegalAccessException e) {
                e.printStackTrace();
                }
                field.setAccessible(false);
            }
        }
    }
}
