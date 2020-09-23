package org.springbus.javaasssit;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class MakeClass {


    public static void main(String[] args) throws Exception {

        ClassPool cp = ClassPool.getDefault();

        CtClass cc = cp.get("org.springbus.javaasssit.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.addLocalVariable("s1", CtClass.longType);
        m.addLocalVariable("s2", CtClass.longType);
        m.insertBefore("{  long s1=System.currentTimeMillis();\n  } ");
        m.insertAfter("{ long s2=System.currentTimeMillis();\n System.out.println( \"cost time = \"+  (s2-s1) +\" ms\");\n  } ");

        Class c = cc.toClass();
        cc.writeFile("d:/123/");

        Hello h = (Hello) c.newInstance();
        h.say();

    }
}
