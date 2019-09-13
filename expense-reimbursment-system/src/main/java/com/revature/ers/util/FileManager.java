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

import com.revature.ers.models.Resource;

public class FileManager {

	public FileManager() {
	}

	public List<File> getFiles(HttpServletRequest request, String filePath){
		filePath = request.getServletContext().getRealPath(filePath);
		List<File> files = null;
		try {
			files = Files.walk(Paths.get(filePath))
			        .filter(Files::isRegularFile)
			        .map(Path::toFile)
			        .collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return files;
	}
	public String getFileContent(HttpServletRequest request, String filePath) {
		filePath = request.getServletContext().getRealPath(filePath);
		String resource = "";
		try {
			resource = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	public String getFileContentRealPath(HttpServletRequest request, String filePath) {
		String resource = "";
		try {
			resource = new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resource;
	}
	
	public Map<String, Resource> getHTMLCSSJSResources(HttpServletRequest request) {
		List<File> HTMLFiles = getFiles(request, ResourceUrls.getDIRECTORYURLS().get("HTML"));
		List<File> CSSFiles = getFiles(request, ResourceUrls.getDIRECTORYURLS().get("CSS"));
		List<File> JSFiles = getFiles(request, ResourceUrls.getDIRECTORYURLS().get("JS"));
		Map<String, Resource> resources = new HashMap<>();
		for(File file: HTMLFiles) {
			String key = file.getName().substring(0, file.getName().indexOf("."));
			String html = getFileContentRealPath(request, file.getAbsolutePath());
			Resource resource = new Resource(html, null, null);	
			resources.put(key, resource);
		}
		for(File file: CSSFiles) {
			String key = file.getName().substring(0, file.getName().indexOf("."));
			String css = getFileContentRealPath(request, file.getAbsolutePath());
			Resource resource = resources.get(key);
			if(resource != null) {
				resource.setCss(css);
				resources.put(key, resource);
			}
			
		}
		for(File file: JSFiles) {
			String key = file.getName().substring(0, file.getName().indexOf("."));
			String js = getFileContentRealPath(request, file.getAbsolutePath());
			Resource resource = resources.get(key);
			if(resource != null) {
				resource.setJavascript(js);
				resources.put(key, resource);
			}
		}
		return resources;
	}
	
	public Map<String, Resource> getHTMLCSSJSURIs(HttpServletRequest request) {
		List<File> HTMLFiles = getFiles(request, ResourceUrls.getDIRECTORYURLS().get("HTML"));
		List<File> CSSFiles = getFiles(request, ResourceUrls.getDIRECTORYURLS().get("CSS"));
		List<File> JSFiles = getFiles(request, ResourceUrls.getDIRECTORYURLS().get("JS"));
		Map<String, Resource> resources = new HashMap<>();
		for(File file: HTMLFiles) {
			String key = file.getName().substring(0, file.getName().indexOf("."));
			String html = getFileContentRealPath(request, file.getAbsolutePath());
			Resource resource = new Resource(html, null, null);	
			resources.put(key, resource);
		}
		for(File file: CSSFiles) {
			String key = file.getName().substring(0, file.getName().indexOf("."));
			String css = getFileContentRealPath(request, file.getAbsolutePath());
			Resource resource = resources.get(key);
			if(resource != null) {
				resource.setCss(css);
				resources.put(key, resource);
			}
			
		}
		for(File file: JSFiles) {
			String key = file.getName().substring(0, file.getName().indexOf("."));
			String js = getFileContentRealPath(request, file.getAbsolutePath());
			Resource resource = resources.get(key);
			if(resource != null) {
				resource.setJavascript(js);
				resources.put(key, resource);
			}
		}
		return resources;
	}
}
