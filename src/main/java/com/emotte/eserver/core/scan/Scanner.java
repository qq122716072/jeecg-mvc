package com.emotte.eserver.core.scan;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.emotte.eserver.core.exception.ScanException;

/**
 * 扫描器
 * @author ChengJing
 * 2017年3月22日
 */
public abstract class Scanner {
	private String rootPath;
	private ClassLoader classLoader;
	public Scanner(String rootPath, ClassLoader classLoader) {
		this.rootPath = rootPath;
		this.classLoader = classLoader;
	}
	
	public void scanJarLib (String libPath, String scanRegex) throws ScanException {
		File libFile = new File(rootPath + libPath);
		String[] list = libFile.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jar") || name.endsWith(".zip");
			}
		});
		for (String filePath : list) {
			scanJar("/" + rootPath + libPath + "/" + filePath, scanRegex);
		}
	}
	/** 
     * 从jar包中读取符合正则表达式的类
     * @param jarPath jar文件路径
     * @param scanRegex 扫描正则表达式
     * @return 类的完整名称 
     */  
    public void scanJar(String jarPath, String scanRegex) throws ScanException {
    	scanRegex = optimizeRegex(scanRegex);
        try {  
            @SuppressWarnings("resource")
			JarFile jarFile = new JarFile(jarPath);  
            Enumeration<JarEntry> entrys = jarFile.entries();  
            while (entrys.hasMoreElements()) {  
                JarEntry jarEntry = entrys.nextElement();  
                String entryName = jarEntry.getName();  
            	if(Pattern.matches(scanRegex, entryName)) {
            		entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
            		process(entryName);
            	}
            }  
        } catch (Exception e) {  
        	throw new ScanException(e);  
        }  
    }
	
    /**
     * 从jar包中读取符合正则表达式的类
     * @param jarFile jar文件
     * @param scanRegex 扫描正则表达式
     * 2017年3月23日
     */
    public void scanJar(JarFile jarFile, String scanRegex) throws ScanException {
    	scanRegex = optimizeRegex(scanRegex);
        try {  
            Enumeration<JarEntry> entrys = jarFile.entries();  
            while (entrys.hasMoreElements()) {  
                JarEntry jarEntry = entrys.nextElement();  
                String entryName = jarEntry.getName();  
            	if(Pattern.matches(scanRegex, entryName)) {
            		entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
            		process(entryName);
            	}
            }  
        } catch (Exception e) {  
        	throw new ScanException(e);
        }  
    }
    
    /**
     * TODO
     * @param classPath
     * @param scanRegex
     * 2017年4月12日
     * @throws Exception 
     */
    public void scanClassPath (String classPath, String scanRegex) throws ScanException {
    	scanRegex = optimizeRegex(scanRegex);
		scanClasses(rootPath + classPath, "", scanRegex);
    }
    
    private void scanClasses (String rootPath, String pack, String scanRegex) throws ScanException {
    	File libFile = new File(rootPath+ File.separator + pack);
    	String[] list = libFile.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".class") || !name.contains(".");
			}
		});
		try {
			for (String string : list) {
				String classpath = "";
				if (StringUtils.isBlank(pack)) {
					classpath = string;
				} else {
					classpath = pack + File.separator + string;
				}
				if (string.endsWith(".class")) {
			    	if(Pattern.matches(scanRegex, classpath)) {
			    		classpath = classpath.replace("/", ".").replace("\\", ".").substring(0, classpath.lastIndexOf("."));
			    		process(classpath);
			    	}
				} else { // 文件夹
					scanClasses(rootPath, classpath, scanRegex);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ScanException(e);
		}
    }
    
    /**
     * 优化正则表达式
     * @param regex
     * @return
     * 2017年3月22日
     */
    private String optimizeRegex(String regex) {
    	return regex.replaceAll("\\.", "[\\\\\\\\/]").replaceAll("\\*", "((?!\\\\.).)*") + ".*\\.class";
    }
    
	/**
	 * 对扫描到的类进行操作
	 * @param classname 类路径
	 * 2017年3月22日
	 */
	abstract public void process (String classname) throws Exception;

	public ClassLoader getClassLoader() {
		return classLoader;
	}
	
	public static void main(String[] args) {
		String regex = "com[\\\\/]emotte.*\\.class";
		System.out.println(Pattern.matches(regex, "com/emotte/Base.class"));
	}
}
