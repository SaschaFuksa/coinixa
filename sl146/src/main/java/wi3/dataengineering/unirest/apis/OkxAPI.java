package wi3.dataengineering.unirest.apis;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;

import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import wi3.dataengineering.unirest.APIsInterface;
import wi3.dataengineering.unirest.CoinsInterface;
import wi3.dataengineering.unirest.OkxCoinObject;
import wi3.dataengineering.unirest.candlesticks.CandleStick;
import wi3.dataengineering.unirest.coins.Bitcoin;
import wi3.dataengineering.unirest.coins.Cardano;
import wi3.dataengineering.unirest.coins.Dogecoin;
import wi3.dataengineering.unirest.coins.Ethereum;
import wi3.dataengineering.unirest.coins.ShibaInu;
import wi3.dataengineering.unirest.coins.Tezos;

public class OkxAPI implements APIsInterface{

    @Override
    public ArrayList<CoinsInterface> getCoinData() {
        // create list for all coin objects
        ArrayList <CoinsInterface> coins = new ArrayList<CoinsInterface>();
        // create OkxCoinObject which will be used as adapter object
        OkxCoinObject okx = new OkxCoinObject();


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
        coins.add(btcCoin);


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
        coins.add(adaCoin);


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
        coins.add(dogeCoin);


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
        coins.add(ethCoin);


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
        coins.add(shibCoin);     
        

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
        coins.add(xtzCoin);   

        //export of demo data
        //do not forget to delete
        exportCoin("sl146/BTC_OKX.json", btcCoin);
        exportCoin("sl146/ETH_OKX.json", ethCoin);

       return coins;
    }

    @Override
    public void getCoinHistory() {
        // TODO Auto-generated method stub 
    }


    @Override
    public ArrayList<ArrayList<CandleStick>> getCandlestickData() {
        // TODO Auto-generated method stub
        ArrayList<ArrayList<CandleStick>> candlesBinance = new ArrayList<>();
        
        // Candles Bitcoin
        candlesBinance.add(getCandleData("BTC-USDT"));
        // Candles Cardano
        candlesBinance.add(getCandleData("ADA-USDT"));
        // Candles Dogecoin
        candlesBinance.add(getCandleData("DOGE-USDT"));
        // Candles Ethereum
        candlesBinance.add(getCandleData("ETH-USDT"));
        // Candles ShibaInu
        candlesBinance.add(getCandleData("SHIB-USDT"));
        // Candles Tezos
        candlesBinance.add(getCandleData("XTZ-USDT"));

        //testing exports
        //do not forget to delete
        exportCandle(getCandleData("BTCUSDT"), "sl146/BTC_Candle_OKX.csv");
        exportCandle(getCandleData("ETHUSDT"), "sl146/ETH_Candle_OKX.csv");

        return candlesBinance;
    }
    
    private ArrayList<CandleStick> getCandleData(String symbol) {
        JSONArray resp = Unirest.get("https://www.okx.com/api/v5/market/candles")
                        .queryString("instId", symbol)
                        .queryString("bar", "1H")
                        .queryString("after", "1643670000000")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getJSONArray("data");

        ArrayList<CandleStick> binanceCandlesList = new ArrayList<CandleStick>();
        for (int i=0; i < resp.length(); i++) {
            JSONArray candle = (JSONArray) resp.get(i);

            long openTime = candle.getLong(0);
            float open = candle.getFloat(1);
            float high = candle.getFloat(2);
            float low = candle.getFloat(3);
            float close = candle.getFloat(4);
            float volume = candle.getFloat(5);
            float quoteAssetVolume = candle.getFloat(6);
            long closeTime = candle.getLong(0) + 3600;
            /*
            long numberOfTrades = candle.getInt(8);
            float takerBuyBase = candle.getFloat(9);
            float takerBuyQuote = candle.getFloat(10);
            float ignore = candle.getFloat(11); */

            CandleStick candleData = new CandleStick(openTime, open, high, low, close, volume, closeTime, quoteAssetVolume, 0, 0, 0, 0);
            binanceCandlesList.add(candleData);
        }

        Collections.reverse(binanceCandlesList);
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
                System.out.println("Erfolgreicher Export Candle-Data OKX!");
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
            System.out.println("Erfolgreicher Export Coin-Data OKX!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
