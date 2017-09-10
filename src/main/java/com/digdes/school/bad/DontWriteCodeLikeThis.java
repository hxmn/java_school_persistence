package com.digdes.school.bad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * There is no excuse to write code like this.
 *
 * There only purpose is to show common mistakes.
 */
@SuppressWarnings({"all"})
public class DontWriteCodeLikeThis {
    public static void main(String[] args) {
        int санктПетербург = 26063; //spb
        String ссылкаНаПогоду = "http://informer.gismeteo.ru/xml/" + санктПетербург + "_1.xml";

        String овтет = "";
        InputStream is = null;
        try {
            BufferedReader br;
            URL url = new URL(ссылкаНаПогоду);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                овтет += line + "\n";
            }
        } catch (Throwable ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        System.out.println(овтет);
        Pattern pattern = Pattern.compile("<TEMPERATURE max=\"(\\d+)\" min=\"(\\d+)\"/>");
        Matcher matcher = pattern.matcher(овтет);
        if (matcher.find()) {
            String tMax = matcher.group(1);
            String tMin = matcher.group(2);
            System.out.println("Temperature: " + tMin + "-" + tMax + "° C");
        }
    }
}
