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

    //What else to do huh. Made some cleaning among names, should be easier to read. Also added some comments for future me/whoever will be
    //using this thing. If you're reading this, thank you for that one and if you have any insights how to improve this, feel free to tell me :)

public class Main {
    /*
    This method makes list of songs by using recursion. You need to provide valid File object (with path to current folder),
    PrintWriter with a text file where song list will be created, filePath to the folder, where songs are located (should be the same as Object File)
    and name of actual folder. Last two are used mainly to keep track of recursions.
    
    
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
		/*
                    First, we're getting path to the folder, where the program is located. You can change the String filePath
                    so it reads from text file if you wish to be able to set your music folder path without the need of moving the jar.
                    Rest is just making the string usable for the rest of the process and should be left "as is".
                */
		String filePath = new String(Paths.get(".").toAbsolutePath().normalize().toString());
		filePath = filePath.replace("\\", "/");
		
		String[] temporary = filePath.split("/");
		String actualFolder = new String(temporary[temporary.length-1]);

		File file = new File(filePath);

		PrintWriter writer = new PrintWriter("songs.txt", "UTF-8");
		buildSongListRecursion(file,writer,filePath,actualFolder);

		writer.close();
                
                
	}

}


