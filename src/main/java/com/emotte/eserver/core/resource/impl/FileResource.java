package com.emotte.eserver.core.resource.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.emotte.eserver.core.resource.Resource;

public class FileResource implements Resource {
	private String path;
	public FileResource(String path) {
		this.path = path.replace(File.separator, "/");
	}

	@Override
	public InputStream getInputStream() throws IOException {
		File file = new File(path);
		if (!file.exists()) throw new FileNotFoundException();
		return new FileInputStream(file);
	}

}
