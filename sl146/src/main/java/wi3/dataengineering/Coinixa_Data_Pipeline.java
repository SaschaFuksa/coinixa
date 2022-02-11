package wi3.dataengineering;

import java.util.Scanner;

import wi3.dataengineering.database.Database;
import wi3.dataengineering.threads.Thread_getCandleStick;
import wi3.dataengineering.threads.Thread_getCoinValue;
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
        Thread_getCoinValue thread_coinValue = new Thread_getCoinValue();
        thread_coinValue.start();

        //  Thread 2: get candlestick data from public apis -> binance, bitenium, okx
        Thread_getCandleStick thread_coinCandleStick = new Thread_getCandleStick();
        thread_coinCandleStick.start();

        //  Thread 3: check price changes of coins an send notification e-mails -> WIP

        // non threaded methods to get and export data
        /* Database export = new Database();

        // coin value and candlestick data from binance api
        APIsInterface binance = new BinanceAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(binance.getCandlestickData(), "binance"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(binance.getCoinData(), "binance"));

        // coin value and candlestick data from bitenium api
        APIsInterface bitenium = new BiteniumAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(bitenium.getCandlestickData(), "bitenium"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(bitenium.getCoinData(), "bitenium"));

        // coin value and candlestick data from okx api
        APIsInterface okx = new OkxAPI();
        System.out.println("Ergbenis Export CandleSticks: -- " + export.exportCandle(okx.getCandlestickData(), "okx"));
        System.out.println("Ergbenis Export Coinvalue: -- " + export.exportCoin(okx.getCoinData(), "okx"));
 */
    }
}