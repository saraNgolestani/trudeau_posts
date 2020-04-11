package io.github.sarangolestani.utils;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;

public class Utils {

    public static String tonality(String txt, String apiKey, String ibmUrl) {
        String tonality;
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", authenticator);
        toneAnalyzer.setServiceUrl(ibmUrl);

        HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
                .disableSslVerification(true)
                .build();
        toneAnalyzer.configureClient(configOptions);
        ToneOptions toneOptions = new ToneOptions.Builder()
                .text(txt)
                .build();
        ToneAnalysis toneAnalysis = toneAnalyzer.tone(toneOptions).execute().getResult();
        if(toneAnalysis.getDocumentTone().getTones()!=null && toneAnalysis.getDocumentTone().getTones().size()>=1){
            tonality = toneAnalysis.getDocumentTone().getTones().get(0).getToneName();
        }
        else if(toneAnalysis.getSentencesTone()!=null && toneAnalysis.getSentencesTone().get(0).getTones().size()>=1){
            tonality = toneAnalysis.getSentencesTone().get(0).getTones().get(0).getToneName();
        }
        else {
            tonality = "MixedFeelings";
        }

        return tonality;
    }

    public static String getLimitedSentences(String txt, int sentenceNumber){
        String firsttwntywrds = "";
        String[] splitedTxt= txt.split("\\.",sentenceNumber);
        if(splitedTxt.length<sentenceNumber){
            return txt;
        }
        for(int i = 0; i<(sentenceNumber-1);i++){
            firsttwntywrds += splitedTxt[i];
        }
        return firsttwntywrds;
    }
}
