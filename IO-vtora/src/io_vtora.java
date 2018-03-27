import java.io.*;
import java.nio.file.Files;
public class io_vtora {
	static void copyLargeTxtFiles(String from, String to, long size) throws IOException {
		File src = new File(from);
		File dest = new File(to);
		FileOutputStream fos = new FileOutputStream(dest);
		FileInputStream fin = new FileInputStream(src);
		byte[] b = null;
		src.mkdirs();
		File[] paths = null;
		if(src.isDirectory()) {
			paths = src.listFiles();
			
		}
		for(File t : paths) {
			if(t.getName().endsWith(".txt") && t.getTotalSpace() > size) {
				if(!dest.exists()) {
					dest.createNewFile();
				}
				while(fin.read(b) != -1) {
				fos.write(b);
			}	
			}
			
		}
			
		
	}


	public static void main(String[] args) throws IOException {
		String source = "C:\\Users\\Gorjan\\eclipse-workspace\\IO-vtora\\bin";
		String to = "C:\\Users\\Gorjan\\eclipse-workspace\\IO-vtora\\bin\\nov";
		copyLargeTxtFiles(source,to,1);
	}
}
