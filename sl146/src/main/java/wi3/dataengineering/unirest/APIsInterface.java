package wi3.dataengineering.unirest;

import java.util.ArrayList;
import java.util.HashMap;

import wi3.dataengineering.unirest.candlesticks.CandleStick;

public interface APIsInterface {
    public HashMap<String, CoinsInterface> getCoinData();
    
    public HashMap<String, ArrayList<CandleStick>> getCandlestickData();
}
