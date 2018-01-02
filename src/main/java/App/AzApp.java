package App;

import Scrapper.BulkDataScrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AzApp {
    public static void main(String[] args) {
        List<String> queryWords = getwordPair("Alphabit/Alphabit.txt");
        //   Hh, Xx, Iı, İi, Jj, Kk, Qq, Ll, Mm, Nn, Oo, Öö, Pp, Rr, Ss, Şş, Tt, Uu, Üü, Vv, Yy, Zz

        //queryWords.add("&");
        //queryWords.add("N");
    /*    queryWords.add("aLi");
        queryWords.add("ALI");
        queryWords.add("ali");*/
      //  queryWords.add("n");

        //SƏHMDAR
  /*      queryWords.add("al");
        queryWords.add("llc");
        queryWords.add("it");
        queryWords.add("muh");
        queryWords.add("MƏHDUD");
        queryWords.add("CƏMİYYƏTİ");*/


        // AÇIQ SƏHMDAR CƏMİYYƏTİ
        //     KOOPERATİVİ
        //MÜƏSSİSƏ
        //      FERMER
        // ŞİRKƏTİ
        //       FİRMASI
        //ASSOSİASİYASI

        //    Jj, Kk, Qq, Ll, Mm, Nn, Oo, Öö, Pp, Rr, Ss, Şş, Tt, Uu, Üü, Vv, Yy, Zz

        BulkDataScrap bulkDataScrap = new BulkDataScrap(queryWords, "az_taxcompanies", "File/Az3.json");
        bulkDataScrap.makeBulkRequests();
    }
    private static List<String> getwordPair(String filename){
        List<String> alphabit=readAlphabit(filename);
        List<String> wordPair=new ArrayList<>();
        for(String word:alphabit){
            wordPair.add(word);
            for(String secondPair:alphabit){
                String second=word+secondPair;
                wordPair.add(second);
            }
        }
        return wordPair;
    }
    private static List<String> readAlphabit(String fileName) {
       List<String> taxCodes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String values[] = line.split(",");
                //  stringBuilder.append(values[0]);
                for(int i=0;i<values.length;i++) {
                    String charac=values[i].trim().charAt(0)+"";
                    taxCodes.add(charac);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taxCodes;
    }
}
/*
     queryWords.add("MƏSULİYYƏTLİ");
        queryWords.add("CƏMİYYƏTİ");
        queryWords.add("AÇIQ");
        queryWords.add("SƏHMDAR");
        queryWords.add("CƏMİYYƏTİ");
        queryWords.add("A");
                queryWords.add("1");
                queryWords.add("2");
                queryWords.add("3");
                queryWords.add("4");
                queryWords.add("5");
                queryWords.add("6");
                queryWords.add("7");
                queryWords.add("8");
                queryWords.add("9");
                queryWords.add("\"");
                queryWords.add("MƏHDUD MƏSULİYYƏTLİ CƏMİYYƏTİ");
                queryWords.add("AÇIQ SƏHMDAR CƏMİYYƏTİ");
                queryWords.add("KOOPERATİVİ");
                queryWords.add("MÜƏSSİSƏ");
                queryWords.add("FERMER");
                queryWords.add("ŞİRKƏTİ");
                queryWords.add("FİRMASI");
                queryWords.add("ASSOSİASİYASI");

                // AÇIQ SƏHMDAR CƏMİYYƏTİ
                //     KOOPERATİVİ
                //MÜƏSSİSƏ
                //      FERMER
                // ŞİRKƏTİ
                //       FİRMASI
                //ASSOSİASİYASI
                queryWords.add("A");
                queryWords.add("A");
                queryWords.add("a");
                queryWords.add("B");
                queryWords.add("b");
                queryWords.add("C");
                queryWords.add("c");
                queryWords.add("Ç");
                queryWords.add("ç");
                queryWords.add("E");
                queryWords.add("e");
                queryWords.add("Ə");
                queryWords.add("ə");
                queryWords.add("F");
                queryWords.add("f");
                queryWords.add("G");
                queryWords.add("g");
                queryWords.add("Ğ");
                queryWords.add("ğ");
                queryWords.add("H");
                queryWords.add("h");
                queryWords.add("X");
                queryWords.add("I");
                queryWords.add("İ");
                //    Jj, Kk, Qq, Ll, Mm, Nn, Oo, Öö, Pp, Rr, Ss, Şş, Tt, Uu, Üü, Vv, Yy, Zz
                queryWords.add("j");
                queryWords.add("k");
                queryWords.add("İ");
                queryWords.add("q");
                queryWords.add("l");
                queryWords.add("m");
                queryWords.add("Ö");
                queryWords.add("Ş");
                queryWords.add("ü");
                    queryWords.add("%");
        queryWords.add("-");
        queryWords.add(" ");
        queryWords.add("11");
        queryWords.add("turk");
        queryWords.add("az");
        queryWords.add("ltd");
        queryWords.add("taxi");
                queryWords.add("Z");*/
