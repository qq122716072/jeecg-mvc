package com.emotte.eserver.core.helper;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang.StringUtils;

public class ClassHelper {

	public static Class<?> getSubClass(Class<?> clazz, ClassLoader classLoader) {
		ArrayList<Class<?>> list = getAllClass(clazz.getPackage().getName(), classLoader);
		for (Class<?> class1 : list) {
			if (clazz.isAssignableFrom(class1)) {
                if (!clazz.equals(class1)) {
                    return class1;
                }
            }
		}
		return null;
	}
	
	private static ArrayList<Class<?>> getAllClass(String packagename, ClassLoader classLoader) {
        ArrayList<Class<?>> classList = new ArrayList<>();
        // 返回对当前正在执行的线程对象的引用。
        // 返回该线程的上下文 ClassLoader。
        if (null == classLoader) {
        	classLoader = Thread.currentThread().getContextClassLoader();
        }
        String path = packagename.replace('.', '/');
        try {
            ArrayList<File> fileList = new ArrayList<>();
            /**
             * 这里面的路径使用的是相对路径 如果大家在测试的时候获取不到，请理清目前工程所在的路径 使用相对路径更加稳定！
             * 另外，路径中切不可包含空格、特殊字符等！
             */
            // getResources:查找所有给定名称的资源
            // 获取jar包中的实现类:Enumeration<URL> enumeration =
            // classLoader.getResources(path);
            Enumeration<URL> enumeration = classLoader.getResources(path);
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                String protocol = url.getProtocol();  
                String pkgPath = url.getPath(); 
                if ("file".equals(protocol)) {  
                    // 本地自己可见的代码  
                    findClassName(classList, packagename, pkgPath, classLoader);  
                } else if ("jar".equals(protocol)) {  
                    // 引用第三方jar的代码  
                    findClassName(classList, packagename, url, classLoader);  
                }
                fileList.add(new File(url.getFile()));
            }
//            for (int i = 0; i < fileList.size(); i++) {
//                list.addAll(findClass(fileList.get(i), packagename));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classList;
    }
	
	
	private static void findClassName(List<Class<?>> clazzList, String pkgName, String pkgPath, ClassLoader classLoader) {  
        if(clazzList == null || pkgPath == null){  
            return;  
        }  
        // 过滤出.class文件及文件夹 
        File[] files = new File(pkgPath).listFiles(new FileFilter() {  
            @Override  
            public boolean accept(File file) {  
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();  
            }  
        });
        if(files != null){  
            for (File f : files) {  
                String fileName = f.getName();  
                if (f.isFile()) {  
                    // .class 文件的情况  
                    String clazzName = getClassName(pkgName, fileName);
                    addClassName(clazzList, clazzName, classLoader);
                } else {  
                    // 需要继续查找该文件夹/包名下的类
                    String subPkgName = pkgName +"."+ fileName;  
                    String subPkgPath = pkgPath +"/"+ fileName;  
                    findClassName(clazzList, subPkgName, subPkgPath, classLoader);  
                }  
            }  
        }  
    }
	
	private static void findClassName(List<Class<?>> clazzList, String pkgName, URL url, ClassLoader classLoader) throws IOException {  
        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();  
        JarFile jarFile = jarURLConnection.getJarFile();  
        Enumeration<JarEntry> jarEntries = jarFile.entries();  
        while (jarEntries.hasMoreElements()) {  
            JarEntry jarEntry = jarEntries.nextElement();  
            String jarEntryName = jarEntry.getName(); // 类似：sun/security/internal/interfaces/TlsMasterSecret.class  
            String clazzName = jarEntryName.replace("/", ".");  
            int endIndex = clazzName.lastIndexOf(".");  
            String prefix = null;  
            if (endIndex > 0) {  
                String prefix_name = clazzName.substring(0, endIndex);  
                endIndex = prefix_name.lastIndexOf(".");  
                if(endIndex > 0){  
                    prefix = prefix_name.substring(0, endIndex);  
                }  
            }  
            if (prefix != null && jarEntryName.endsWith(".class")) {  
            	clazzName = getClassName("", clazzName);
                if(prefix.equals(pkgName)){
                    addClassName(clazzList, clazzName, classLoader);  
                } else if(prefix.startsWith(pkgName)){  
                    // 遍历子包名：子类  
                    addClassName(clazzList, clazzName, classLoader);  
                }  
            }  
        }  
    }  
	
	private static String getClassName(String pkgName, String fileName) {  
        int endIndex = fileName.lastIndexOf(".");  
        String clazz = null;  
        if (endIndex >= 0) {  
            clazz = fileName.substring(0, endIndex);  
        }  
        String clazzName = null;  
        if (clazz != null) {
        	if (!StringUtils.isEmpty(pkgName)) {
        		clazzName = pkgName + "." + clazz;  
        	} else {
        		clazzName = clazz;  
        	}
        }  
        return clazzName;  
    }  
    
	private static void addClassName(List<Class<?>> clazzList, String clazzName, ClassLoader classLoader) {  
        if (clazzList != null && clazzName != null) {  
            Class<?> clazz = null;  
            try {  
        		if (null == classLoader) {
        			clazz = Class.forName(clazzName);
        		} else {
        			clazz = Class.forName(clazzName, true, classLoader);
        		}
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            }
            if (clazz != null) {  
            	clazzList.add(clazz);
            }  
        }  
    }
	@Deprecated
	private static ArrayList<Class<?>> findClass(File file, String packagename) {
        ArrayList<Class<?>> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        // 返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件。
        File[] files = file.listFiles();
        for (File file2 : files) {
            if (file2.isDirectory()) {
                // assert !file2.getName().contains(".");// 添加断言用于判断
                if (!file2.getName().contains(".")) {
                    ArrayList<Class<?>> arrayList = findClass(file2, packagename + "." + file2.getName());
                    list.addAll(arrayList);
                }
            } else if (file2.getName().endsWith(".class")) {
                try {
                    // 保存的类文件不需要后缀.class
                    list.add(Class.forName(packagename + '.' + file2.getName().substring(0, file2.getName().length() - 6)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
