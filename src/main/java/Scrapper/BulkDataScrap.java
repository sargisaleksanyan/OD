package Scrapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BulkDataScrap {
    private List<String> queryParams;
    private String mainUrl = "https://data.occrp.org/api/1/entities?facet=countries&facet=schemata&facet=dataset&facet=collections&filter:dataset=";
    private FileWriter fw;
    private FileWriter missedFw;

    public static void main(String[] args) {

    }

    public BulkDataScrap(List<String> queryParams, String countryQuery, String fileName) {
        mainUrl = mainUrl + countryQuery;
        this.queryParams = queryParams;
        try {
            fw = new FileWriter(fileName);
            missedFw=new FileWriter("File/Missed.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeBulkRequests() {
        File file = new File("File/Missed.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //makeRequest(null);
        for (int i = 0; i < queryParams.size(); i++) {
            makeRequest(queryParams.get(i));
        }
    }

    private void makeRequest(String query) {
        int offset = 0;
        int limit = 2000;
        String url = getUrl(query, 30, offset);
        String json = getResponse(url);
        int total = getTotalSize(json);
        System.out.println("String "+query+" "+"Total "+total );
        if (total > 0) {
     /*       while (total > offset) {
                offset += limit;
                String newurl = getUrl(query, limit, offset);
                String companyJson = getResponse(newurl);
                writeJson(companyJson);
            }*/
            do {

                String newurl = getUrl(query, limit, offset);
                offset += limit;
                String companyJson = getResponse(newurl);
                writeJson(companyJson);
            }
            while (total > offset);
        }
    }


    private int getTotalSize(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int total = jsonObject.getInt("total");

            return total;
        }
        catch (Exception e){
            return 0;
        }
    }

    private void writeJson(String json) {
        try {
            JSONObject contentObject = new JSONObject(json);
            //JSONObject arrayObject=contentObject.getJSONObject("array");
            JSONArray array = contentObject.getJSONArray("results");
            writeDataToFile(array.toString());
          //  System.out.println("Size " + array.length());
          //  System.out.println("Name 1" + array.getJSONObject(0).getString("name"));
        }
        catch (Exception e){

        }
    }

    private String getUrl(String query, int limit, int offset) {
        String limitText = "limit=" + limit;
        String offsetString = "offset=" + offset;
        String queryParam = "q=" + query;
        String url=null;
        if(query==null) {
            url = mainUrl + "&" + limitText + "&" + offsetString ;
        }
        else{
            url = mainUrl + "&" + limitText + "&" + offsetString + "&" + queryParam;
        }
        return url;
    }

    private String getResponse(String requestUrl) {
        String json = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setConnectTimeout(25000);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36");
            connection.setRequestProperty("Accept", "Accept:application/json, text/plain, */*");
            InputStream is = connection.getInputStream();
            json = getJsonByStream(is);
        } catch (Exception e) {
            writeMissedData(requestUrl);
            return null;
        }

        return json;
    }

    private String getJsonByStream(InputStream inputStream) {
        //  =httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String value = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private void writeMissedData(String content) {
        try {
            missedFw.write(content);
            missedFw.write("\n");
            missedFw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToFile(String content) {
        try {

            fw.write(content);
            fw.write("\n");
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
