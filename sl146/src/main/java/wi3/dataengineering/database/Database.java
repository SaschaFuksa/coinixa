package wi3.dataengineering.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;

import wi3.dataengineering.unirest.CoinsInterface;
import wi3.dataengineering.unirest.candlesticks.CandleStick;

/**
 * classes for exporting coin data.
 * coins and candlesticks will be given as parameter.
 */
public class Database {
    public Database() {
    }
    
    public Boolean exportCandle(HashMap<String, ArrayList<CandleStick>> candles, String api) {
        FileWriter file;
        
        //change path for google cloud deployment
        //String path = "C:/Data/" + api + "/";
        String path = "/home/sascha_fuksa/Data/" + api + "/";

        Boolean success = false;
            for (Entry<String, ArrayList<CandleStick>> entry : candles.entrySet()) {
                try {
                    file = new FileWriter(path + entry.getKey() + "/candleStickData_" + api + "_" + entry.getKey() + ".csv");
                    file.write("openTime, openTimeStamp, open, high, low, close, volume, closeTime, closeTimeStamp, quoteAssetVolume, numberOfTrades, takerBuyBase, takerBuyQuote, IGNORE" + "\n");
                    for (CandleStick candleStick : entry.getValue()) {
                        file.write(candleStick.toStringCSV());
                    }
                    file.flush();
                    file.close();
                    success = true;
                    //System.out.println("Coin Candlesticks Exportiert in Ordner: " + entry.getKey() + " erfolgreich! --> Nachschauen!");
                    System.out.println("CandleStick Export: -- " + entry.getKey() +" -- Time: -- " + System.currentTimeMillis());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return success;
    }

    public Boolean exportCoin(HashMap<String, CoinsInterface> coins, String api) {
        FileWriter file;
        
        //String path = "C:/Data/" + api + "/";
        String path = "/home/sascha_fuksa/Data/" + api + "/";
        
        Boolean success = false;
        for (Entry<String, CoinsInterface> entry : coins.entrySet()) {
            try {
                file = new FileWriter(path + entry.getKey() + "/coin_Value_" + api + "_" + entry.getKey() + ".json");
                file.write(new Gson().toJson(entry.getValue()));
                file.flush();
                file.close();
                success = true;
                //System.out.println("Coin Value Exportiert in Ordner: " + entry.getKey() +  " erfolgreich! --> Nachschauen!");
                System.out.println("Coin Value Export: -- " + entry.getKey() +" -- Time: -- " + System.currentTimeMillis());
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }  
    return success;
    }
}
