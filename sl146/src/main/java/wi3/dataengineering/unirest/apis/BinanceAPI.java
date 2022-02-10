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


public class BinanceAPI implements APIsInterface{

    @Override
    public HashMap<String, CoinsInterface> getCoinData() {
        // TODO Auto-generated method stub
        HashMap<String, CoinsInterface> coins = new HashMap<>();
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
        //ArrayList<ArrayList<CandleStick>> candlesBinance = new ArrayList<>();
        HashMap<String, ArrayList<CandleStick>> candlesBinance = new HashMap<>();

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
        
        return candlesBinance;
    }

    private ArrayList<CandleStick> getCandleData(String symbol) {
        JSONArray resp = Unirest.get("https://api.binance.com/api/v3/klines")
                        .queryString("symbol", symbol)
                        .queryString("interval", "1h")
                        .queryString("startTime", "1643670000000")
                        .asJson()
                        .getBody()
                        .getArray();
        
        ArrayList<CandleStick> binanceCandlesList = new ArrayList<CandleStick>();
        for (int i=0; i < resp.length(); i++) {
            JSONArray candle = (JSONArray) resp.get(i);

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
        return binanceCandlesList;
    }

    // hilfsmethode export csv
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
