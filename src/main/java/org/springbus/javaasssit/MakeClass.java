package org.springbus.javaasssit;

import javassist.*;

public class MakeClass {


     static class MyTranslator implements Translator {
         public void start(ClassPool pool)
                 throws NotFoundException, CannotCompileException {

             System.out.println( pool + " start  "  );
         }

         public void onLoad(ClassPool pool, String classname)
                 throws NotFoundException, CannotCompileException {
             CtClass cc = pool.get(classname);
             cc.setModifiers(Modifier.PUBLIC);
             System.out.println( pool + " classname="+classname);
         }
     }

    public static void main(String[] args) throws Exception {

        ClassPool cp = ClassPool.getDefault();
        Loader cl = new Loader();
        MyTranslator t=new MyTranslator();
        cl.addTranslator(cp, t);

        CtClass cc = cp.get("org.springbus.javaasssit.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        //m.setName("sayNo");

        m.addLocalVariable("s1", CtClass.longType);
        m.addLocalVariable("s2", CtClass.longType);
        m.insertBefore(" {  long s1=System.currentTimeMillis();\r\n  } ");
        m.insertAfter("{ long s2=System.currentTimeMillis();\r\n System.out.println( \"cost time = \"+  (s2-s1) +\" ms\");\r\n  } ");


        //CtMethod mm = new CtMethod(CtClass.intType, "move", new CtClass[] { CtClass.intType }, cc);
        //mm.addLocalVariable("x", CtClass.intType);

        CtField f = new CtField(CtClass.intType, "z", cc);
        f.setModifiers(Modifier.PRIVATE | Modifier.STATIC );
        cc.addField(f);

        CtField dy= CtField.make("private int dy=0; ", cc);
        cc.addField(dy);


        CtMethod mm = CtNewMethod.make(
                "  public int xMove(int dx) { int x=0; x += dx; return x; }",
                cc);

        cc.addMethod(mm);

        Class c = cc.toClass();
        cc.writeFile("d:/123/");

        Hello h = (Hello) c.newInstance();
        h.say();

    }
}
