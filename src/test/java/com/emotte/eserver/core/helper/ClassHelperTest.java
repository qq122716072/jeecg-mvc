package com.emotte.eserver.core.helper;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;

import com.emotte.eserver.loader.Loader;

public class ClassHelperTest {

	@Test
	public void test() throws ClassNotFoundException, IOException {
		// 加载class文件和jar包
		Loader loader = new Loader(ServerHelper.getServicePath());
		loader.load(ServerHelper.CLASSESROOTPATH, ServerHelper.LIBPATH, ServerHelper.SERVICELIB);
		ClassLoader classLoader = loader.getChildClassLoader();
		System.out.println("current classloader:" + classLoader);
		// 扫描文件并初始化
//		String className = "com.emotte.kernel.file.rw.IFileRWService";
		String className = "com.emotte.amib.amib.service.TypeService";
//		String className = "com.emotte.amib.emerp.service.WorkTypeService";
//		String className =  "com.emotte.emp.service.ScheduleService";
		Class<?> clazz = Class.forName(className, true, loader.getChildClassLoader());
//		String path = clazz.getPackage().getName().replace('.', '/');
//		System.out.println(path);
//		Enumeration<URL> enumeration = (classLoader).getResources(path);
//        while (enumeration.hasMoreElements()) {
//            URL url = enumeration.nextElement();
//            System.out.println(url);
//        }
        
		Class<?> subClazz = ClassHelper.getSubClass(clazz, loader.getChildClassLoader());
		System.out.println(subClazz);
	}

}
