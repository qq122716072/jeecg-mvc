package com.emotte.eserver.core.resource.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.emotte.eserver.core.resource.Resource;

public class ClassPathResource implements Resource {
	private String path;

	private ClassLoader classLoader;

	private Class<?> clazz;
	public ClassPathResource(String path) {
		this(path, (ClassLoader) null);
	}
	public ClassPathResource(String path, ClassLoader classLoader) {
		this.path = path;
		this.classLoader = (classLoader != null ? classLoader : ClassLoader.getSystemClassLoader());
	}

	public ClassPathResource(String path, Class<?> clazz) {
		this.path = path;
		this.clazz = clazz;
	}

	protected ClassPathResource(String path, ClassLoader classLoader, Class<?> clazz) {
		this.path = path;
		this.classLoader = classLoader;
		this.clazz = clazz;
	}
	@Override
	public InputStream getInputStream() throws IOException {
		InputStream is;
		if (this.clazz != null) {
			is = this.clazz.getResourceAsStream(this.path);
		}
		else {
			is = this.classLoader.getResourceAsStream(this.path);
		}
		if (is == null) {
			throw new FileNotFoundException(" cannot be opened because it does not exist");
		}
		return is;
	}

}
