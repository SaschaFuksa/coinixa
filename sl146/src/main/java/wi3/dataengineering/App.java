package wi3.dataengineering;

import java.util.ArrayList;

import wi3.dataengineering.unirest.APIsInterface;
import wi3.dataengineering.unirest.apis.BinanceAPI;
import wi3.dataengineering.unirest.apis.BiteniumAPI;
import wi3.dataengineering.unirest.apis.OkxAPI;
import wi3.dataengineering.unirest.candlesticks.CandleStick;

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

        APIsInterface candle = new BiteniumAPI();
        candle.getCoinData();
        candle.getCandlestickData();

        APIsInterface candle2 = new BinanceAPI();
        candle2.getCoinData();
        candle2.getCandlestickData();

        APIsInterface candle3 = new OkxAPI();
        candle3.getCoinData();
        candle3.getCandlestickData();

        /* for (ArrayList<CandleStick> candleList : candle.getCandlestickData()) {
            for (CandleStick candleStick : candleList) {
                System.out.println(candleStick.toString());
            }   
        }    */
    }
}