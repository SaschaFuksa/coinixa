package wi3.dataengineering.unirest.coins;

/**
 * adapter object
 * okx api return different data as binance or bitenium
 * used to map okx data 
 * stored data from this class is used to construct coin data objects
 */
public class OkxCoinObject {
    private String instId;
    private float last;
    private float open24h;
    private float high24h;
    private float volCcy24h;
    
    public OkxCoinObject() {
    }

    public OkxCoinObject(String instId, float last, float open24h, float high24h, float volCcy24h) {
        this.instId = instId;
        this.last = last;
        this.open24h = open24h;
        this.high24h = high24h;
        this.volCcy24h = volCcy24h;
    }


    public String getInstId() {
        return instId;
    }

    public void setInstId(String instId) {
        this.instId = instId;
    }

    public float getLast() {
        return last;
    }

    public void setLast(float last) {
        this.last = last;
    }

    public float getOpen24h() {
        return open24h;
    }

    public void setOpen24h(float open24h) {
        this.open24h = open24h;
    }

    public float getHigh24h() {
        return high24h;
    }

    public void setHigh24h(float high24h) {
        this.high24h = high24h;
    }

    public float getVolCcy24h() {
        return volCcy24h;
    }

    public void setVolCcy24h(float volCcy24h) {
        this.volCcy24h = volCcy24h;
    }

    @Override
    public String toString() {
        return "OkxAdapter [high24h=" + high24h + ", instId=" + instId + ", last=" + last + ", open24h=" + open24h
                + ", volCcy24h=" + volCcy24h + "]";
    }
}
