package wi3.dataengineering;

import wi3.dataengineering.threads.Thread_getCandleStick;
import wi3.dataengineering.threads.Thread_getCoinValue;

public class Coinixa_Data_Pipeline 
{
    public static void main( String[] args )
    {

        

        
        //Threading:
        //  Thread 1: get coin value from public apis -> binance, bitenium, okx
        //  create thread which will update the coin value of all coins every 5 sec
        //  start the thread
        Thread_getCoinValue thread_coinValue = new Thread_getCoinValue();
        thread_coinValue.start();

        //  Thread 2: get candlestick data from public apis -> binance, bitenium, okx
        //  create thread which will update candlestick data of all coins every 24h
        Thread_getCandleStick thread_coinCandleStick = new Thread_getCandleStick();
        thread_coinCandleStick.start();

        //  Thread 3: check price changes of coins an send notification e-mails -> WIP
        //  If theres time left, try to implemnt e-mail notifications


        // non threaded methods to test -> get and export data
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