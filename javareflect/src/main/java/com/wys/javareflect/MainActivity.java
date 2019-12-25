package com.wys.javareflect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void printFileds() {
        Class mClass = Student.class;
        // 获取类名
        System.out.println("类名：" + mClass.getName());
        // 获取public 访问权限的变量
        Field[] fields = mClass.getFields();
        // 获取所有声明的变量
        Field[] declaredFields = mClass.getDeclaredFields();

        for (Field field : declaredFields) {
            int modifiers = field.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");
            System.out.println(field.getType().getName() + "  " + field.getName());
        }
    }

    public void method(View view) {
        Class<Student> studentClass = Student.class;
        System.out.println("类名：" + studentClass.getSimpleName());
        Method[] methods = studentClass.getMethods();
        Method[] declaredMethods = studentClass.getDeclaredMethods();

        for (Method method : declaredMethods) {
            // 获取方法的修饰符
            int modifiers = method.getModifiers();
            String s = Modifier.toString(modifiers);
            System.out.println("方法修饰符：" + s);
            // 获取方法名和返回值类型
            Class<?> returnType = method.getReturnType();
            String name = method.getName();
            System.out.println("return type:" + returnType.getSimpleName());
            System.out.println("method name:" + name);
            // 获取方法参数
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            for (Type genericParameterType : genericParameterTypes) {
                System.out.println(genericParameterType.getTypeName());
            }
            //            for (Parameter parameter : parameters) {
            //                String name1 = parameter.getName();
            //                Class<?> type = parameter.getType();
            //                System.out.println("参数类型：" + type.getSimpleName() + "  方法名：" + name1);
            //            }
        }


    }

    public void reflect(View view) {
        printFileds();
    }


    public void accessPriMethod(View view) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        Class<? extends Student> aClass = student.getClass();
        Method eat = aClass.getDeclaredMethod("eat", null);
        eat.setAccessible(true);
        Object invoke = eat.invoke(student);
    }

}
