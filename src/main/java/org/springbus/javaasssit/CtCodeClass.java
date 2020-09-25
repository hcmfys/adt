package org.springbus.javaasssit;

import javassist.CannotCompileException;
import javassist.bytecode.*;

public class CtCodeClass {

    /**
     *
     * @param methodInfo
     * @param index
     * @param name
     * @param type
     * @throws CannotCompileException
     */
    public static void updateLocalVariable(  MethodInfo methodInfo,int index,
                                             String name, javassist.CtClass type) throws CannotCompileException {

        ConstPool cp = methodInfo.getConstPool();
        CodeAttribute ca = methodInfo.getCodeAttribute();
        if (ca == null)
            ca =new CodeAttribute(cp, 1, 1, null, null);

        LocalVariableAttribute va = (LocalVariableAttribute) ca.getAttribute(
                LocalVariableAttribute.tag);
        if (va == null) {
            va = new LocalVariableAttribute(cp);
            ca.getAttributes().add(va);
        }

        int maxLocals = ca.getMaxLocals();
        String desc = Descriptor.of(type);
        va.addEntry(0, ca.getCodeLength(),
                cp.addUtf8Info(name), cp.addUtf8Info(desc), index);
        ca.setMaxLocals(maxLocals);
    }

}
