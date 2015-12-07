import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.ArrayList;
/** 
 Program made by Summarum
  **/
public class Main {
	public static void rekurencja(File s, PrintWriter writer, String sciezka, String aktFolder){
		String[] abc = s.list();
		ArrayList<String> folders = new ArrayList<>();
		writer.println("");
		writer.println("###############################");
		writer.println(aktFolder);
		writer.println("###############################");
		for(String string : abc){
			if(!string.endsWith(".mp3")){
			File test = new File(sciezka.concat("/"+string));
			if(test.isDirectory()){
				folders.add(string);
			}
			}else{
				String[] tempor = string.split(".mp3");
				writer.println(tempor[0]);
			}
		}
		for(String a : folders){
			rekurencja(new File(sciezka.concat("/"+a)),writer,sciezka.concat("/"+a),a);
		}

	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub


		String sciezka = new String(Paths.get(".").toAbsolutePath().normalize().toString());
		sciezka = sciezka.replace("\\", "/");
		
		String[] temporary = sciezka.split("/");
		String aktFolder = new String(temporary[temporary.length-1]);

		File file = new File(sciezka);

		PrintWriter writer = new PrintWriter("piosenki.txt", "UTF-8");
		rekurencja(file,writer,sciezka,aktFolder);

		writer.close();
	}

}
