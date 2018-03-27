import java.awt.List;
import java.io.*;


public class IO_prva {

	
	
	
	public static void main(String[] args){
		String from = new String("C:\\Users\\Gorjan\\eclipse-workspace\\IO-prva\\src");
		String to = new String("C:\\Users\\Gorjan\\eclipse-workspace\\IO-prva\\src\\noviot\\gorjan\\dzin\\");
		File dest = new File(to);
		File[] paths = null;
		
	
	
		File f = new File(from);
		if(!f.exists()) {
			
			System.out.println("Ne postoi");
			return;
		}
		if(f.isDirectory()) {
			paths = f.listFiles();
			
		}
		for(File t : paths) {
			if(t.getName().endsWith(".txt") && t.canWrite()) {
				dest.mkdirs();
			t.renameTo(new File(to  + t.getName()));
			}	
	
			}
		}
	}
