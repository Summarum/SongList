package com.summa.songmain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 *
 * @author Summarum
 */
public class Main {
    /*
    This method makes list of songs by using recursion. You need to provide 
    
    */
    public static void buildSongListRecursion(File fileP, PrintWriter writer, String filePath, String actualFolder){
		String[] listOfFiles = fileP.list();
		ArrayList<String> folders = new ArrayList<>();
		writer.println("");
		writer.println("###############################");
		writer.println(actualFolder);
		writer.println("###############################");
		for(String string : listOfFiles){
			if(!string.endsWith(".mp3")){
			File test = new File(filePath.concat("/"+string));
			if(test.isDirectory()){
				folders.add(string);
			}
			}else{
				String[] temp = string.split(".mp3");
				writer.println(temp[0]);
			}
		}
		for(String a : folders){
			buildSongListRecursion(new File(filePath.concat("/"+a)),writer,filePath.concat("/"+a),a);
		}

	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub


		String filePath = new String(Paths.get(".").toAbsolutePath().normalize().toString());
		//filePath = filePath.replace("\\", "/");
		filePath = "D:/mp3";
		String[] temporary = filePath.split("/");
		String actualFolder = new String(temporary[temporary.length-1]);

		File file = new File(filePath);

		PrintWriter writer = new PrintWriter("songs.txt", "UTF-8");
		buildSongListRecursion(file,writer,filePath,actualFolder);

		writer.close();
	}

}


