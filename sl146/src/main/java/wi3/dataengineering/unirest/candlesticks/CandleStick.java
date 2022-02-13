package wi3.dataengineering.unirest.candlesticks;

import wi3.dataengineering.unirest.hilfsklassen.UnixToTimeStamp;

/**
 * candlestick object
 * used for mapping received candlestick data to an object
 */
public class CandleStick{
    private long openTime;
    private String openTimeStamp;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;
    private long closeTime;
    private String closeTimeStamp;
    private float quoteAssetVolume;
    private long numberOfTrades;
    private float takerBuyBase;
    private float takerBuyQuote;
    private float ignore;
    
    public CandleStick() {
    }

    public CandleStick(long openTime, float open, float high, float low, float close, float volume, long closeTime,
            float quoteAssetVolume, long numberOfTrades, float takerBuyBase, float takerBuyQuote, float ignore) {
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.closeTime = closeTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.takerBuyBase = takerBuyBase;
        this.takerBuyQuote = takerBuyQuote;
        this.ignore = ignore;
        
        setOpenTimeStamp(openTime);
        setCloseTimeStamp(closeTime);
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(int openTime) {
        this.openTime = openTime;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(int closeTime) {
        this.closeTime = closeTime;
    }

    public float getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public void setQuoteAssetVolume(float quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    public long getNumberOfTrades() {
        return numberOfTrades;
    }

    public void setNumberOfTrades(int numberOfTrades) {
        this.numberOfTrades = numberOfTrades;
    }

    public float getTakerBuyBase() {
        return takerBuyBase;
    }

    public void setTakerBuyBase(float takerBuyBase) {
        this.takerBuyBase = takerBuyBase;
    }

    public float getTakerBuyQuote() {
        return takerBuyQuote;
    }

    public void setTakerBuyQuote(float takerBuyQuote) {
        this.takerBuyQuote = takerBuyQuote;
    }

    public float getIgnore() {
        return ignore;
    }

    public void setIgnore(float ignore) {
        this.ignore = ignore;
    }

    private void setOpenTimeStamp(long openTime) {
        UnixToTimeStamp convertLong = new UnixToTimeStamp();
        this.openTimeStamp = convertLong.convertTime(openTime);
    }

    public String getOpenTimeStamp() {
        return openTimeStamp;
    }

    private void setCloseTimeStamp(long closeTime) {
        UnixToTimeStamp convertLong = new UnixToTimeStamp();
        this.closeTimeStamp = convertLong.convertTime(closeTime);
    }

    public String getCloseTimeStamp() {
        return closeTimeStamp;
    }


    @Override
    public String toString() {
        return "candleStick [QuoteAssetVolume=" + quoteAssetVolume + ", close=" + close + ", closeTime=" + closeTime
                + ", high=" + high + ", ignore=" + ignore + ", low=" + low + ", numberOfTrades=" + numberOfTrades
                + ", open=" + open + ", openTime=" + openTime + ", takerBuyBase=" + takerBuyBase + ", takerBuyQuote="
                + takerBuyQuote + ", volume=" + volume + ", openTimeStamp=" + openTimeStamp + ", closeTimeStamp=" + closeTimeStamp + "]";
    }

    public String toStringCSV() {
        return openTime + "," + openTimeStamp + "," + open + "," + high + "," + low + "," + close + "," + volume + "," + closeTime + "," + closeTimeStamp + "," + quoteAssetVolume
                 + "," + numberOfTrades + "," + takerBuyBase + "," + takerBuyQuote + "," + ignore +"\n";
    }

}
