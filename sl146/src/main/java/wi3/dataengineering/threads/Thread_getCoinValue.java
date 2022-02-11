package wi3.dataengineering.threads;

import java.util.Map.Entry;

import wi3.dataengineering.database.Database;
import wi3.dataengineering.unirest.APIsInterface;
import wi3.dataengineering.unirest.CoinsInterface;
import wi3.dataengineering.unirest.apis.BinanceAPI;
import wi3.dataengineering.unirest.apis.BiteniumAPI;
import wi3.dataengineering.unirest.apis.OkxAPI;

public class Thread_getCoinValue implements Runnable {
    private Boolean running = false;
    private Thread worker;

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run() {
        this.running = true;
        getCoinValueAndExport();
        //getCoinDataTest();
    }
    
    public void stopThread() {
        this.running = false;
        worker.interrupt();
    }

    private void getCoinValueAndExport() {
        System.out.println("Starting Thread - Get Coin Values and Export to Datastorage!");
        
        Database export = new Database();

        APIsInterface binance = new BinanceAPI();
        APIsInterface bitenium = new BiteniumAPI();
        APIsInterface okx = new OkxAPI();

        while (running) {

            export.exportCoin(binance.getCoinData(), "binance");

            export.exportCoin(bitenium.getCoinData(), "bitenium");

            export.exportCoin(okx.getCoinData(), "okx");

        }
    }


    //method for testing purposes
    private void getCoinDataTest() {
        while (running) {
            APIsInterface coins = new BinanceAPI();

            for (Entry<String, CoinsInterface> entry : coins.getCoinData().entrySet()) {
                System.out.println("THREAD_1 --- " + "Coin: " + entry.getValue().getCoinName());
            } 
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println(
                  "Thread was interrupted");
            }
        }
    }
}
