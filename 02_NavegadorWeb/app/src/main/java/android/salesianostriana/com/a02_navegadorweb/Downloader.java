package android.salesianostriana.com.a02_navegadorweb;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {

    public static String downloadURL(String strUrl) {

        String result = null;

        try {
            URL url = new URL(strUrl);
            BufferedInputStream bin = new BufferedInputStream(
                    url.openStream());
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int cantidad;
            while ((cantidad = bin.read(buffer, 0, 1024)) != -1) {
                baos.write(buffer, 0, cantidad);
            }

            bin.close();

            result = new String(baos.toByteArray(),"UTF-8");

            baos.close();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
