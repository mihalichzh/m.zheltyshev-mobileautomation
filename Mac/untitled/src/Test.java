import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

    public class Test {


        public static void main(String[] args) {

            for (int i = 34; i < 95; i++) {

                String url = "https://vs1.coursehunters.net/software-testing-java/lesson" + i + ".mp4";
                String file = "C:\\Users\\mihal\\Desktop\\Java for Testers\\Lessons\\lesson" + i + ".mp4";
                try {
                    downloadUsingNIO(url, file);

                    //downloadUsingStream(url, "/Users/pankaj/sitemap_stream.xml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private static void downloadUsingStream(String urlStr, String file) throws IOException {
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
        }

        private static void downloadUsingNIO(String urlStr, String file) throws IOException {
            URL url = new URL(urlStr);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        }
    }
