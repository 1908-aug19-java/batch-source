package com.revature.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationDriver {
	
	public static void main(String[] args) {
	
		String path = "src/com/revature/io/serialized.ser";
		
		Person p = new Person(35, "Sally Jenkins", "sjenkins@gmail.com");
		
		try(FileOutputStream fos = new FileOutputStream(path);
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			oos.writeObject(p);
			
			System.out.println("object written");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
