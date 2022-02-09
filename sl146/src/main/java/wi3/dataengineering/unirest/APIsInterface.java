package wi3.dataengineering.unirest;

import java.util.ArrayList;

import wi3.dataengineering.unirest.candlesticks.CandleStick;

public interface APIsInterface {
    public ArrayList<CoinsInterface> getCoinData();

    //placeholder falls mal benötigt
    public void getCoinHistory();
    public ArrayList<ArrayList<CandleStick>> getCandlestickData();
}
