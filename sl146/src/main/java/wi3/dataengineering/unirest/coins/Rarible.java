package wi3.dataengineering.unirest.coins;

import wi3.dataengineering.unirest.CoinsInterface;

/**
 * coin object
 * used to map received coin data to an object
 * stores all relevant information about a coin
 */
public class Rarible implements CoinsInterface{
    private String symbol;
    private float priceChange;
    private float priceChangePercent;
    private float prevClosePrice;
    private float lastPrice;
    private float openPrice;
    private float highPrice;
    private float volume;
    
    public Rarible() {
    }

    public Rarible(String symbol, float priceChange, float priceChangePercent, float prevClosePrice, float lastPrice,
            float openPrice, float highPrice, float volume) {
        this.symbol = symbol;
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.prevClosePrice = prevClosePrice;
        this.lastPrice = lastPrice;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.volume = volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public float getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(float priceChange) {
        this.priceChange = priceChange;
    }

    public float getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(float priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public float getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice(float prevClosePrice) {
        this.prevClosePrice = prevClosePrice;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(float openPrice) {
        this.openPrice = openPrice;
    }

    public float getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(float highPrice) {
        this.highPrice = highPrice;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Rarible [highPrice=" + highPrice + ", lastPrice=" + lastPrice + ", openPrice=" + openPrice
                + ", prevClosePrice=" + prevClosePrice + ", priceChange=" + priceChange + ", priceChangePercent="
                + priceChangePercent + ", symbol=" + symbol + ", volume=" + volume + "]";
    }

    @Override
    public String getCoinName() {
        // TODO Auto-generated method stub
        return "Rarible";
    }
    
}
