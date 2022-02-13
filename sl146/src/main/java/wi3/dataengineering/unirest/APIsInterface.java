package wi3.dataengineering.unirest;

import java.util.ArrayList;
import java.util.HashMap;

import wi3.dataengineering.unirest.candlesticks.CandleStick;

/**
 * interface of classes used to get data from public apis 
 * used public apis are binance, bitenium and okx
 */
public interface APIsInterface {
    // returns coin data (value, volume, etc...) of all six coins in a hashmap
    public HashMap<String, CoinsInterface> getCoinData();
    // returns candlestick data of all six coins in a hashmap
    public HashMap<String, ArrayList<CandleStick>> getCandlestickData();
}
