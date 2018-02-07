package com.emotte.eserver.core.helper;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Stack;

import com.emotte.kernel.exception.BaseException;

/**
 * URL类加载器
 * @author ChengJing
 * 2017年3月23日
 */
public class URLClassLoaderHelper {
	
	private URLClassLoader classLoader;
	public URLClassLoaderHelper() {
		classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	}
	
	public URLClassLoaderHelper (ClassLoader parentClassloader) {
		this.classLoader = new URLClassLoader(new URL[]{}, parentClassloader);
	}
	
	public URLClassLoaderHelper (URL[] urls, ClassLoader parentClassloader) {
		this.classLoader = new URLClassLoader(urls, parentClassloader);
	}
	
	public void loadClass (String classRootPath) throws BaseException {
		// 设置class文件所在根路径  
		// 例如/usr/java/classes下有一个test.App类，则/usr/java/classes即这个类的根路径，而.class文件的实际位置是/usr/java/classes/test/App.class  
		try {
			File clazzPath = new File(classRootPath);  
			// 记录加载.class文件的数量  
			int clazzCount = 0;  
			if (clazzPath.exists() && clazzPath.isDirectory()) {
			    // 获取路径长度  
			    Stack<File> stack = new Stack<File>();
			    stack.push(clazzPath);  
			    // 遍历类路径  
			    while (!stack.isEmpty()) {
			    	File path = stack.pop();  
			        File[] classFiles = path.listFiles(new FileFilter() {  
			            public boolean accept(File pathname) {  
			                return pathname.isDirectory() || pathname.getName().endsWith(".class");  
			            }
			        });  
			        for (File subFile : classFiles) {
			            if (subFile.isDirectory()) {
			                stack.push(subFile);
			            } else {
			                if (clazzCount++ == 0) {
			                    add2ClassLoader(clazzPath);
			                }  
			            }  
			        }  
			    }  
			}
		} catch (Exception e) {
			LogHelper.error(getClass(), e.getMessage(), e);
			throw new BaseException(e.getMessage());
		}  
	}
	
	public void loadClasses(String classesRootPath) {
		loadClass(classesRootPath);
	}
	
	public void loadJar (String jarFilePath) throws BaseException {
		// 系统类库路径  
		File libPath = new File(jarFilePath);
		if (!libPath.exists()) {
			throw new BaseException("路径不存在");
		}
		if (libPath.isFile()) {
			add2ClassLoader(libPath);
		}
	}
	
	public void loadJars(String libPath) {
		// 系统类库路径  
		File lib = new File(libPath);
		if (!lib.exists()) {
			throw new BaseException("路径不存在");
		}
		if (lib.isDirectory()) {
			// 获取所有的.jar和.zip文件  
			File[] jarFiles = lib.listFiles(new FilenameFilter() {  
			    public boolean accept(File dir, String name) {  
			        return name.endsWith(".jar") || name.endsWith(".zip");  
			    }  
			});  
			if (jarFiles != null) {
				add2ClassLoader(jarFiles);
			}
		}
	}
	
	public void loadJar (File file) throws BaseException {
		// 系统类库路径  
		if (!file.exists()) {
			throw new BaseException("路径不存在");
		}
		if (file.isFile()) {
			add2ClassLoader(file);
		} else {
			// 获取所有的.jar和.zip文件  
			File[] jarFiles = file.listFiles(new FilenameFilter() {  
			    public boolean accept(File dir, String name) {  
			        return name.endsWith(".jar") || name.endsWith(".zip");  
			    }  
			});  
			if (jarFiles != null) {
				add2ClassLoader(jarFiles);
			}
		}
	}
	
	public void loadJars(File[] jarFiles) {
		add2ClassLoader(jarFiles);
	}
	
	private void add2ClassLoader (File... files) throws BaseException {
		Method method = null;
		try {
			method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
		} catch (NoSuchMethodException e) {
			LogHelper.error(getClass(), "方法未找到", e);
			throw new BaseException("方法未找到");
		} catch (SecurityException e) {
			LogHelper.error(getClass(), "安全异常", e);
			throw new BaseException("安全异常");
		}
        boolean accessible = method.isAccessible();
        try {
	        if (accessible == false) {
	            method.setAccessible(true);
	        }
	        // 设置类加载器  
	        for (File file : files) {
	        	try {
	        		// 将当前类路径加入到类加载器中 
	        		URL url = file.toURI().toURL();
	                method.invoke(classLoader, url);
	                LogHelper.debug(getClass(), "加载文件[" + file.getName() + "]成功");
				} catch (Exception e) {
		        	LogHelper.error(getClass(), "加载文件[" + file.getName() + "]失败", e);
		        	throw new BaseException("加载失败");
		        }
	        }
        } finally {  
            method.setAccessible(accessible);
        }
	}

	public URLClassLoader getClassLoader() {
		return classLoader;
	}
	
}
