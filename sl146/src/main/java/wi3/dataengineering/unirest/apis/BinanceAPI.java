package wi3.dataengineering.unirest.apis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
import wi3.dataengineering.unirest.coins.ShibaInu;
import wi3.dataengineering.unirest.coins.Tezos;

/**
 * responsible for rest-request, rest-responses and mapping
 * class used for sending rest-requests to the binance public api
 * rest-response with coin data is mapped to coin object and put into coins hashmap
 * rest-response with candlestick data is mapped to candlestick object and put into candlestick hashmap
 */
public class BinanceAPI implements APIsInterface{

    @Override
    public HashMap<String, CoinsInterface> getCoinData() {
        // all coin objects will be stored and returned in this hashmap
        HashMap<String, CoinsInterface> coins = new HashMap<>();

        // initializing coin object
        // get coin data through rest-request and map it to the coin object
        // put coin object into hashmap with coinname as key
        // repeat for each coin
        CoinsInterface btc = new Bitcoin();
        btc = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "BTCUSDT")
				    .asObject(Bitcoin.class)
                    .getBody();
        coins.put("bitcoin", btc);

        CoinsInterface ada = new Cardano();
        ada = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "ADAUSDT")
				    .asObject(Cardano.class)
                    .getBody();
        coins.put("cardano",ada);

        CoinsInterface doge = new Dogecoin();
        doge = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "DOGEUSDT")
				    .asObject(Dogecoin.class)
                    .getBody();
        coins.put("dogecoin",doge);

        CoinsInterface eth = new Ethereum();
        eth = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "ETHUSDT")
				    .asObject(Ethereum.class)
                    .getBody();
        coins.put("ethereum",eth);
        
        CoinsInterface shib = new ShibaInu();
        shib = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "SHIBUSDT")
				    .asObject(ShibaInu.class)
                    .getBody();
        coins.put("shibainu", shib);

        CoinsInterface xtz = new Tezos();
        xtz = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "XTZUSDT")
				    .asObject(Tezos.class)
                    .getBody();
        coins.put("tezos", xtz);

        // return hashmap with all coin objects
        return coins;

        
        /*    Old Coins, which will not be used anymore 
        CoinsInterface safemoon = new SafeMoon();
        safemoon = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "SAFEMOONUSDT")
				    .asObject(SafeMoon.class)
                    .getBody();
        coins.add(safemoon);
        
        CoinsInterface cake = new PancakeSwap();
        cake = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "CAKEUSDT")
				    .asObject(PancakeSwap.class)
                    .getBody();
        coins.add(cake);
        
        CoinsInterface rari = new Rarible();
        rari = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "RARIUSDT")
				    .asObject(Rarible.class)
                    .getBody();
        coins.add(rari);
        
        CoinsInterface ctk = new CertiK();
        ctk = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "CTKUSDT")
				    .asObject(CertiK.class)
                    .getBody();
        coins.add(ctk);
        */
    }

    @Override
    public HashMap<String, ArrayList<CandleStick>> getCandlestickData() {
        // candlestick data of all coins will be saved in this hashmap
        HashMap<String, ArrayList<CandleStick>> candlesBinance = new HashMap<>();

        // putting each candlestick data into the hashmap
        // using coinname as key
        // Candles Bitcoin
        candlesBinance.put("bitcoin", getCandleData("BTCUSDT"));
        // Candles Cardano
        candlesBinance.put("cardano",getCandleData("ADAUSDT"));
        // Candles Dogecoin
        candlesBinance.put("dogecoin", getCandleData("DOGEUSDT"));
        // Candles Ethereum
        candlesBinance.put("ethereum", getCandleData("ETHUSDT"));
        // Candles ShibaInu
        candlesBinance.put("shibainu", getCandleData("SHIBUSDT"));
        // Candles Tezos
        candlesBinance.put("tezos", getCandleData("XTZUSDT"));
        
        // return hashmap with all candlestick arraylists
        return candlesBinance;
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
        JSONArray resp = Unirest.get("https://api.binance.com/api/v3/klines")
                        .queryString("symbol", symbol)
                        .queryString("interval", "1h")
                        .queryString("startTime", "1643670000000")
                        .asJson()
                        .getBody()
                        .getArray();
        
        // create arraylist with received candlestick data
        // received data is in one big json-array with nested candlestick arrays
        ArrayList<CandleStick> binanceCandlesList = new ArrayList<CandleStick>();
        
        //loop through all nested arrays
        for (int i=0; i < resp.length(); i++) {
            JSONArray candle = (JSONArray) resp.get(i);

            // map nested array to candlestick object
            long openTime = candle.getLong(0);
            float open = candle.getFloat(1);
            float high = candle.getFloat(2);
            float low = candle.getFloat(3);
            float close = candle.getFloat(4);
            float volume = candle.getFloat(5);
            long closeTime = candle.getLong(6);
            float quoteAssetVolume = candle.getFloat(7);
            long numberOfTrades = candle.getInt(8);
            float takerBuyBase = candle.getFloat(9);
            float takerBuyQuote = candle.getFloat(10);
            float ignore = candle.getFloat(11);

            CandleStick candleData = new CandleStick(openTime, open, high, low, close, volume, closeTime, quoteAssetVolume, numberOfTrades, takerBuyBase, takerBuyQuote, ignore);
            binanceCandlesList.add(candleData);
        }
        //return arraylist with candlestick data
        return binanceCandlesList;
    }

    // export method used for testing 
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
                System.out.println("Erfolgreicher Export Candle-Data Binance!");
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
            System.out.println("Erfolgreicher Export Coin-Data Binance!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
