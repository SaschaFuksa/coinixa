package wi3.dataengineering.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import wi3.dataengineering.database.Database;
import wi3.dataengineering.unirest.APIsInterface;
import wi3.dataengineering.unirest.apis.BinanceAPI;
import wi3.dataengineering.unirest.apis.BiteniumAPI;
import wi3.dataengineering.unirest.apis.OkxAPI;
import wi3.dataengineering.unirest.candlesticks.CandleStick;

/**
 * used to run a thread which uses methods from other classes to:
 * get candlestick data for all six coins
 * export all values
 * repeat the process every 24 hours
 */
public class Thread_getCandleStick implements Runnable{
    private Boolean running = false;
    private Thread worker;

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run() {
        this.running = true;
        getCandleSticksAndExport();
        //getCoinDataTest();
    }
    
    public void stopThread() {
        this.running = false;
        worker.interrupt();
    }

    // method to get data and export data
    private void getCandleSticksAndExport() {
        System.out.println("Starting Thread - Get Candlesticks and Export to Datastorage!");

        Database export = new Database();

        APIsInterface binance = new BinanceAPI();
        APIsInterface bitenium = new BiteniumAPI();
        APIsInterface okx = new OkxAPI();
        
        // while loop which gets and exports data until its interrupted through the terminal
        while (running) {

            export.exportCandle(binance.getCandlestickData(), "binance");

            export.exportCandle(bitenium.getCandlestickData(), "bitenium");

            export.exportCandle(okx.getCandlestickData(), "okx");

            // after initial get and export this threads sleeps 24 hours
            try {
                Thread.sleep(86400000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted");
            }
        }
    }


    // method for testing purposes
    private void getCoinDataTest() {
        while (running) {
            APIsInterface candles = new BinanceAPI();
            HashMap<String, ArrayList<CandleStick>> candleSticks = candles.getCandlestickData();

            for (Entry<String, ArrayList<CandleStick>> entry : candleSticks.entrySet()) {
                for (CandleStick candle : entry.getValue()) {
                    System.out.println("THREAD_2 --- " + "CandleStickData: " + candle.getOpen());
                } 
                try {
                    Thread.sleep(86400000);
                    //Thread.sleep(5000);
                } catch (InterruptedException e){ 
                    Thread.currentThread().interrupt();
                    System.out.println(
                    "Thread was interrupted");
                }
            }
        }
    }
}
