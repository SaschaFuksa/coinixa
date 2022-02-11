package wi3.dataengineering;

import wi3.dataengineering.database.Database;
import wi3.dataengineering.unirest.APIsInterface;
import wi3.dataengineering.unirest.apis.BinanceAPI;
import wi3.dataengineering.unirest.apis.BiteniumAPI;
import wi3.dataengineering.unirest.apis.OkxAPI;

public class Coinixa_Data_Pipeline 
{
    public static void main( String[] args )
    {
        //to-do after full commenting and formating:
        //Threading:
        //  Thread 1: get coin value from public apis -> binance, bitenium, okx
        //  Thread 2: get candlestick data from public apis -> binance, bitenium, okx
        //  Thread 3: check price changes of coins an send notification e-mails -> WIP

        // non threaded methods to get and export data
        Database export = new Database();

        // coin value and candlestick data from binance api
        APIsInterface candle = new BinanceAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(candle.getCandlestickData(), "binance"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(candle.getCoinData(), "binance"));

        // coin value and candlestick data from bitenium api
        APIsInterface candle2 = new BiteniumAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(candle2.getCandlestickData(), "bitenium"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(candle2.getCoinData(), "bitenium"));

        // coin value and candlestick data from okx api
        APIsInterface candle3 = new OkxAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(candle3.getCandlestickData(), "okx"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(candle3.getCoinData(), "okx"));

    }
}