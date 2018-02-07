package com.emotte.eserver.loader;

import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.eserver.core.helper.URLClassLoaderHelper;

/**
 * 加载类
 * @author ChengJing
 * 2017年3月23日
 */
public class Loader {
	private String rootPath;
	private ClassLoader parentClassLoader;
	private ClassLoader childClassLoader;
	
	public Loader(String rootPath) {
		this.rootPath = rootPath;
	}
	
	public void load(String classesRootPath, String libPath, String serviceLib) {
		URLClassLoaderHelper loaderHelper = new URLClassLoaderHelper();
		LogHelper.info(getClass(), "开始加载classes文件...");
		loaderHelper.loadClasses(rootPath + classesRootPath);
		LogHelper.info(getClass(), "加载classes结束");
		LogHelper.info(getClass(), "开始加载lib中的jar文件...");
		loaderHelper.loadJars(rootPath + libPath);
		LogHelper.info(getClass(), "加载lib中的jar文件结束");
		URLClassLoaderHelper subLoaderHelper = new URLClassLoaderHelper(loaderHelper.getClassLoader());
		LogHelper.info(getClass(), "开始加载serviceLib中的jar文件...");
		subLoaderHelper.loadJars(rootPath + serviceLib);
		LogHelper.info(getClass(), "加载serviceLib中的jar文件结束");
		
		parentClassLoader = loaderHelper.getClassLoader();
		childClassLoader = subLoaderHelper.getClassLoader();
	}

	public ClassLoader getParentClassLoader() {
		return parentClassLoader;
	}

	public ClassLoader getChildClassLoader() {
		return childClassLoader;
	}
	
}
