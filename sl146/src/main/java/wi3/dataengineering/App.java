package wi3.dataengineering;

import wi3.dataengineering.database.Database;
import wi3.dataengineering.unirest.APIsInterface;
import wi3.dataengineering.unirest.apis.BinanceAPI;
import wi3.dataengineering.unirest.apis.BiteniumAPI;
import wi3.dataengineering.unirest.apis.OkxAPI;

public class App 
{
    public static void main( String[] args )
    {
        
        //test abfrage der coin daten von drei APIs
/* 
        System.out.println("Binance Coindata:");
        APIsInterface binance = new BinanceAPI();
        for (CoinsInterface coin : binance.getCoinData()) {
            System.out.println(coin.toString());
        }

        System.out.println("");
        System.out.println("Bitenium Coindata:");
        APIsInterface bitenium = new BiteniumAPI();
        for (CoinsInterface coin : bitenium.getCoinData()) {
            System.out.println(coin.toString());

        }   

        System.out.println("");
        System.out.println("OKX Coindata:");
        APIsInterface okx = new OkxAPI();
        for (CoinsInterface coin : okx.getCoinData()) {
            System.out.println(coin.toString());
        } */

        /* APIsInterface candle = new BinanceAPI();
        
        for (ArrayList<CandleStick> candleList : candle.getCandlestickData()) {
            for (CandleStick candleStick : candleList) {
                System.out.println(candleStick.toString());
            }   
        }  */

        Database export = new Database();

        APIsInterface candle = new BinanceAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(candle.getCandlestickData(), "binance"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(candle.getCoinData(), "binance"));

        APIsInterface candle2 = new BiteniumAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(candle2.getCandlestickData(), "bitenium"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(candle2.getCoinData(), "bitenium"));

        APIsInterface candle3 = new OkxAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(candle3.getCandlestickData(), "okx"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(candle3.getCoinData(), "okx"));



        /* for (ArrayList<CandleStick> candleList : candle.getCandlestickData()) {
            for (CandleStick candleStick : candleList) {
                System.out.println(candleStick.toString());
            }   
        }    */
    }
}