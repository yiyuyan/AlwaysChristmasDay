package org.ksmc_brigade;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Agent {
    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new Transformer());
    }
    public static class Transformer implements ClassFileTransformer {
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            if("java/util/Calendar".equalsIgnoreCase(className)){
                try {
                    ClassPool pool = ClassPool.getDefault();
                    CtClass cc = pool.get("java.util.Calendar");
                    CtMethod declaredMethod = cc.getDeclaredMethod("get", new CtClass[]{CtClass.intType});
                    declaredMethod.insertBefore("{if($1==2) return 11;\n" +
                            "if($1==5) return 25;}");
                    cc.detach();
                    System.out.println("ACD Agent loaded.");
                    return cc.toBytecode();
                } catch (NotFoundException | CannotCompileException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return classfileBuffer;
        }
    }
}