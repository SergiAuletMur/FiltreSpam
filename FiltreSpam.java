package filtrespam;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;


/**
 *
 * @author Oulet
 */

public class FiltreSpam {

    public static void main(String[] args) throws IOException {
        String target_dir = "./SPAM_TRAINING";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) {
            if(f.isFile()) {
                BufferedReader inputStream = null;

                try {
                    inputStream = new BufferedReader(
                                    new FileReader(f));
                    String line;

                    while ((line = inputStream.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }
    }

}
