package wi3.dataengineering.unirest.apis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.google.gson.Gson;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import wi3.dataengineering.unirest.APIsInterface;
import wi3.dataengineering.unirest.CoinsInterface;
import wi3.dataengineering.unirest.candlesticks.CandleStick;
import wi3.dataengineering.unirest.coins.Bitcoin;
import wi3.dataengineering.unirest.coins.Cardano;
import wi3.dataengineering.unirest.coins.Dogecoin;
import wi3.dataengineering.unirest.coins.Ethereum;
import wi3.dataengineering.unirest.coins.OkxCoinObject;
import wi3.dataengineering.unirest.coins.ShibaInu;
import wi3.dataengineering.unirest.coins.Tezos;

/**
 * responsible for rest-request, rest-responses and mapping
 * class used for sending rest-requests to the bitenium public api
 * rest-response with coin data is mapped to coin object and put into coins hashmap
 * rest-response with candlestick data is mapped to candlestick object and put into candlestick hashmap
 * okx returns different coin data 
 * use of adapter class to map incoming data to a object
 * data from adapter object is used to construct coin object (missing values set to 0.0f)
 */
public class OkxAPI implements APIsInterface{

    @Override
    public HashMap<String, CoinsInterface> getCoinData() {
        // all coin objects will be stored and returned in this hashmap
        HashMap <String, CoinsInterface> coins = new HashMap<>();
        
        // create OkxCoinObject which will be used as adapter object
        OkxCoinObject okx = new OkxCoinObject();

        // get coin data through rest-request and map it to the okxcoinobject object
        // use data from okxcoinobjekt to construct coin object
        // put coin object into hashmap with coinname as key
        // repeat for each coin
        String btc = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "BTC-USDT")
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("data")
                    .getJSONObject(0)
                    .toString();       
        Gson gson = new Gson();
        okx = gson.fromJson(btc, OkxCoinObject.class);
        // create coin object with okx data
        // missing data will be constructed with zero (0.0f)
        CoinsInterface btcCoin = new Bitcoin(okx.getInstId(), 0.0f, (float) 0.0f, 0.0f, okx.getLast(), okx.getOpen24h(), okx.getHigh24h(), okx.getVolCcy24h());
        coins.put("bitcoin", btcCoin);

        String ada = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "ADA-USDT")
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("data")
                    .getJSONObject(0)
                    .toString();      
        okx = gson.fromJson(ada, OkxCoinObject.class);
        // create coin object with okx data
        // missing data will be constructed with zero (0.0f)
        CoinsInterface adaCoin = new Cardano(okx.getInstId(), 0.0f, (float) 0.0f, 0.0f, okx.getLast(), okx.getOpen24h(), okx.getHigh24h(), okx.getVolCcy24h());
        coins.put("cardano", adaCoin);

        String doge = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "DOGE-USDT")
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("data")
                    .getJSONObject(0)
                    .toString();       
        okx = gson.fromJson(doge, OkxCoinObject.class);
        // create coin object with okx data
        // missing data will be constructed with zero (0.0f)
        CoinsInterface dogeCoin = new Dogecoin(okx.getInstId(), 0.0f, (float) 0.0f, 0.0f, okx.getLast(), okx.getOpen24h(), okx.getHigh24h(), okx.getVolCcy24h());
        coins.put("dogecoin", dogeCoin);


        String eth = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "ETH-USDT")
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("data")
                    .getJSONObject(0)
                    .toString();       
        okx = gson.fromJson(eth, OkxCoinObject.class);
        // create coin object with okx data
        // missing data will be constructed with zero (0.0f)
        CoinsInterface ethCoin = new Ethereum(okx.getInstId(), 0.0f, (float) 0.0f, 0.0f, okx.getLast(), okx.getOpen24h(), okx.getHigh24h(), okx.getVolCcy24h());
        coins.put("ethereum", ethCoin);


        String shib = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "SHIB-USDT")
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("data")
                    .getJSONObject(0)
                    .toString();       
        okx = gson.fromJson(shib, OkxCoinObject.class);
        // create coin object with okx data
        // missing data will be constructed with zero (0.0f)
        CoinsInterface shibCoin = new ShibaInu(okx.getInstId(), 0.0f, (float) 0.0f, 0.0f, okx.getLast(), okx.getOpen24h(), okx.getHigh24h(), okx.getVolCcy24h());
        coins.put("shibainu", shibCoin);     
        

        String xtz = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "XTZ-USDT")
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("data")
                    .getJSONObject(0)
                    .toString();       
        okx = gson.fromJson(xtz, OkxCoinObject.class);
        // create coin object with okx data
        // missing data will be constructed with zero (0.0f)
        CoinsInterface xtzCoin = new Tezos(okx.getInstId(), 0.0f, (float) 0.0f, 0.0f, okx.getLast(), okx.getOpen24h(), okx.getHigh24h(), okx.getVolCcy24h());
        coins.put("tezos", xtzCoin);   

    // return hashmap with all coin objects
    return coins;
    }

    @Override
    public HashMap<String, ArrayList<CandleStick>> getCandlestickData() {
        // candlestick data of all coins will be saved in this hashmap
        HashMap<String, ArrayList<CandleStick>> candlesOkx = new HashMap<>();
        
        // putting each candlestick data into the hashmap
        // using coinname as key
        // Candles Bitcoin
        candlesOkx.put("bitcoin", getCandleData("BTC-USDT"));
        // Candles Cardano
        candlesOkx.put("cardano", getCandleData("ADA-USDT"));
        // Candles Dogecoin
        candlesOkx.put("dogecoin", getCandleData("DOGE-USDT"));
        // Candles Ethereum
        candlesOkx.put("ethereum", getCandleData("ETH-USDT"));
        // Candles ShibaInu
        candlesOkx.put("shibainu", getCandleData("SHIB-USDT"));
        // Candles Tezos
        candlesOkx.put("tezos", getCandleData("XTZ-USDT"));

        return candlesOkx;
    }
    
    /**
     * get candlestick data from public api through rest-request
     * candlestick data is in 1h timeframes 
     * api returns candlestick data in one big json-array
     * this methods maps the received json-array into candlestick arrays and puts them into a arraylist
     * @param symbol symbol of the coin to get the data for
     * @return arraylist with candlestick data
     */
    private ArrayList<CandleStick> getCandleData(String symbol) {
        // get data from api
        JSONArray resp = Unirest.get("https://www.okx.com/api/v5/market/candles")
                        .queryString("instId", symbol)
                        .queryString("bar", "1H")
                        .queryString("before", "1643670000000")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getJSONArray("data");

        // create arraylist with received candlestick data
        // received data is in one big json-array with nested candlestick arrays
        ArrayList<CandleStick> okxCandlesList = new ArrayList<CandleStick>();

        // loop through all nested arrays
        for (int i=0; i < resp.length(); i++) {
            JSONArray candle = (JSONArray) resp.get(i);

            // map nested array to candlestick object
            long openTime = candle.getLong(0);
            float open = candle.getFloat(1);
            float high = candle.getFloat(2);
            float low = candle.getFloat(3);
            float close = candle.getFloat(4);
            float volume = candle.getFloat(5);
            float quoteAssetVolume = candle.getFloat(6);
            long closeTime = candle.getLong(0) + 3600;

            CandleStick candleData = new CandleStick(openTime, open, high, low, close, volume, closeTime, quoteAssetVolume, 0, 0, 0, 0);
            okxCandlesList.add(candleData);
        }
        // okx api returns candlestick data in reversed order
        // reverse order of the arraylist
        Collections.reverse(okxCandlesList);
        //return arraylist with candlestick data
        return okxCandlesList;
    }

    //export method used for testing
    private void exportCandle(ArrayList<CandleStick> candles, String filename) {
        FileWriter file; 
            try {
                file = new FileWriter(filename);
                file.write("openTime, openTimeStamp, open, high, low, close, volume, closeTime, closeTimeStamp, quoteAssetVolume, numberOfTrades, takerBuyBase, takerBuyQuote, IGNORE" + "\n");
                for (CandleStick candleStick : candles) {
                    file.write(candleStick.toStringCSV());
                }
                file.flush();
                file.close();
                System.out.println("Erfolgreicher Export Candle-Data OKX!");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    //export method used for testing
    private void exportCoin(String filename, CoinsInterface coin) {
        FileWriter file; 
        try {
            file = new FileWriter(filename);
            file.write(new Gson().toJson(coin));
            file.flush();
            file.close();
            System.out.println("Erfolgreicher Export Coin-Data OKX!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
