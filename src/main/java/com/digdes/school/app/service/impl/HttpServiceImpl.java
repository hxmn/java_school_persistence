package com.digdes.school.app.service.impl;

import com.digdes.school.app.service.HttpService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * ...
 *
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
@Service
public class HttpServiceImpl implements HttpService {
    @Override
    public String getUrl(String link) {
        try {
            URL url = new URL(link);
            try (InputStream is = url.openStream()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
