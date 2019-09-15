package com.revature.ers.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.ers.models.Resource;

public class FileManager {
	private static final Logger LOGGER = Logger.getLogger(FileManager.class);

	public List<File> getFiles(HttpServletRequest request, String root) {
		root = request.getServletContext().getRealPath(root);
		List<File> files = null;
		try {
			files = Files.walk(Paths.get(root)).filter(Files::isRegularFile).map(Path::toFile)
					.collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return files;
	}

	public List<String> getDirectories(HttpServletRequest request, String root, int depth) {
		final String finalRoot = request.getServletContext().getRealPath(root);
		List<String> files = null;
		try {
			files = Files.walk(Paths.get(finalRoot), depth).filter(Files::isDirectory).map((Path p) -> {
				String d = p.toString();
				return d.substring(d.lastIndexOf(root));
			}).collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return files;
	}

	public String getFileContent(HttpServletRequest request, String filePath) {
		filePath = request.getServletContext().getRealPath(filePath);
		String resource = "";
		try {
			resource = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return resource;
	}

	public String getFileContentRealPath(String filePath) {
		String resource = "";
		try {
			resource = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			LOGGER.error(e);
		}
		return resource;
	}

	public Map<String, Resource> getHTMLCSSJSResources(HttpServletRequest request, String root, String[] subs) {
		Map<String, String> directoryUris = ResourceURIs.getDIRECTORYURIS(request, root, 1);
		Map<String, Resource> resources = new HashMap<>();
		for (String sub : subs) {
			List<File> fileList = getFiles(request, directoryUris.get(sub));
			for (File file : fileList) {
				String key = file.getName().substring(0, file.getName().indexOf('.'));
				String field = getFileContentRealPath(file.getAbsolutePath());
				switch (sub.toUpperCase()) {
				case "HTML":
					Resource resource = new Resource(field, null, null);
					resource.setTitle(key.substring(0, 1).toUpperCase() + key.substring(1));
					resources.put(key, resource);
					break;
				case "CSS":
					resources.computeIfPresent(key, (k, r) -> {
						if (r != null) {
							r.setCss(field);
						}
						return r;
					});
					break;
				case "JS":
					resources.computeIfPresent(key, (k, r) -> {
						if (r != null) {
							r.setJavascript(field);
						}
						return r;
					});
					break;
				default:
				}
			}
		}
		return resources;
	}
}
