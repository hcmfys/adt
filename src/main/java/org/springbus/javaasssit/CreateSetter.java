package org.springbus.javaasssit;

import javassist.*;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.MethodInfo;

import java.util.List;

public class CreateSetter {
    public static void main(String[] args) throws Exception {

        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("User");
        pool.importPackage("java.lang.reflect.Method");
        pool.importPackage("java.util.List");
        //创建属性
        CtField field01 = CtField.make("private int id;", cc);
        CtField field02 = CtField.make("private String name;", cc);
        cc.addField(field01);
        cc.addField(field02);

        //创建方法
        CtMethod method01 = CtMethod.make(" public String getName(){return name;} ", cc);

        CtMethod method02 = new CtMethod(CtClass.voidType, "setName",
                new CtClass[]{}, cc);
        method02.addParameter(pool.get("java.lang.String"));
        method02.setBody("{this.name=$1;}");
        //method02.addLocalVariable("name", pool.get("java.lang.String"));
        // method02.addLocalVariable("ex",pool.get("java.lang.Exception"));
        //method02.setGenericSignature("(Ljava/lang/String;)V");

        // method02.setBody("{ this.name=name;}");


        method01.addCatch("{ System.out.println($e);throw $e;}", pool.get("java.lang.Exception"));
        // method02.addCatch("{ System.out.println($e); throw $e; }", pool.get("java.lang.Exception"));

        MethodInfo methodInfo = method01.getMethodInfo();
        //ConstPool cp = methodInfo.getConstPool();


        List<AttributeInfo> attributeInfos = methodInfo.getCodeAttribute().getAttributes();
        cc.addMethod(method01);
        cc.addMethod(method02);




        CtMethod mainMethod = CtMethod.make("public static void main(String[] args){  " +
                "        java.lang.Class c=java.lang.Class.forName(\"User\");\n" +
                "        java.lang.Object o= c.newInstance();\n" +
                "        java.lang.reflect.Method nameMethod= c.getDeclaredMethod(\"setName\", new Class[] {java.lang.String.class});\n" +
                "        java.lang.reflect.Method nameGetMethod= c.getDeclaredMethod(\"getName\",new Class[] {});\n" +
                "        nameMethod.invoke(o,new Object[]{ \"java\"  } );\n" +
                "        java.lang.Object v= nameGetMethod.invoke(o, null);\n" +
                "        System.out.println(\"usr name=\" + v);  }  ", cc);
        cc.addMethod(mainMethod);

        //添加有参构造器
        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, cc);

        constructor.setBody("{this.id=$1;this.name=$2;}");
        cc.addConstructor(constructor);
        //无参构造器
        CtConstructor cons = new CtConstructor(null, cc);
        cons.setBody("{}");
        cc.addConstructor(cons);

        cc.writeFile("d:/123/");


    }
}
