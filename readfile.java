import java.net.URLConnection;
import java.net.URL;
import java.net.Socket;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;

class readfile {
	
    private BufferedReader br;

    /*
     * for stdin
     */
    public readfile() {
        InputStreamReader isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
    }

    /*
     * for stdin
     */
    public readfile(Socket socket) {
        try {
            InputStream is        = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br                    = new BufferedReader(isr);
        } catch (IOException ioe) { ioe.printStackTrace(); }
    }
 
    /*
     * for URLs
     */
    public readfile(URL url) {
        try {
            URLConnection site    = url.openConnection();
            InputStream is        = site.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br                    = new BufferedReader(isr);
        } catch (IOException ioe) { ioe.printStackTrace(); }
    }


    // for files and web pages
    public readfile(String s) {

        try {

            // first try to read file from local file system
            File file = new File(s);
            if (file.exists()) {
                FileReader fr = new FileReader(s);
                br = new BufferedReader(fr);
                return;
            }

            // next try for files included in jar
            URL url = getClass().getResource(s);

            // or URL from web
            if (url == null) url = new URL(s);

            URLConnection site    = url.openConnection();
            InputStream is        = site.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
        } catch(IOException ioe) { throw new RuntimeException("Could not open " + s); }
    }

    /*
     * return rest of line as string and return it, not including newline 
     */
    public String readLine() {
        if (br == null) return null;
        String s = null;
        try { s = br.readLine(); }
        catch(IOException ioe) { ioe.printStackTrace(); }
        return s;
    }
}
