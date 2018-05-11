package com.alipay.jarslink.api.impl.other;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义classload加载class文件，重写classload的findClass方法
 * @author LINGZHIYONG764
 * @date 2018/5/10 14:52
 */
public class ClassLoadTest extends ClassLoader {

    private String mLibPath;

    public ClassLoadTest(String path) {
        mLibPath = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String fileName = getFileName(name);

        File file = new File(mLibPath,fileName);

        try {
            FileInputStream is = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            try {
                while ((len = is.read()) != -1) {
                    bos.write(len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = bos.toByteArray();
            is.close();
            bos.close();

            return defineClass(name,data,0,data.length);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    //获取要加载 的class文件名
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name+".class";
        }else{
            return name.substring(index+1)+".class";
        }
    }

    public static void main(String[] args) {
        int i = "com.whut.test.Test".lastIndexOf('.');
        String s = "com.whut.test.Test".substring(i+1);
        System.out.println(i);
        System.out.println(s);

        //创建自定义classloader对象。
        ClassLoadTest diskLoader = new ClassLoadTest("D:\\websphere\\testclass");
        try {
            //加载class文件
            Class c = diskLoader.loadClass("com.whut.test.Test");
            System.out.println(c.getClassLoader().toString());

            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say",null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
