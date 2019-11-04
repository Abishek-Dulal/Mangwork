package mangaexecuter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Map;

public class MangaProvider {

    public void doWork(Map mangaData) throws IOException {
        ArrayList results = (ArrayList) mangaData.get("result");
        ArrayList downloadbles = (ArrayList) mangaData.get("downloadable");

        for(int j=0;j<results.size();j++) {
            Map resultval = (Map) results.get(j);

            for (int i = 0; i < downloadbles.size(); i++) {
                String s = (String) resultval.get(downloadbles.get(i));
                String title = ((String)resultval.get("title")).replaceAll("[^a-zA-Z0-9]", "");

                URLConnection connection = new URL("https:"+s).openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                System.out.println("https:"+s);
                ReadableByteChannel readableByteChannel = Channels.newChannel(connection.getInputStream());

                FileOutputStream fileOutputStream = new FileOutputStream(new File("thumb-images/"+ title+".jpg"));
                fileOutputStream.getChannel()
                        .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            }


        }


    }

}
