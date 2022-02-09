package wi3.dataengineering.unirest;

import java.util.ArrayList;

import kong.unirest.Headers;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import wi3.dataengineering.unirest.coins.Bitcoin;
import wi3.dataengineering.unirest.coins.Cardano;
import wi3.dataengineering.unirest.coins.CertiK;
import wi3.dataengineering.unirest.coins.Dogecoin;
import wi3.dataengineering.unirest.coins.Ethereum;
import wi3.dataengineering.unirest.coins.PancakeSwap;
import wi3.dataengineering.unirest.coins.Rarible;
import wi3.dataengineering.unirest.coins.SafeMoon;
import wi3.dataengineering.unirest.coins.ShibaInu;
import wi3.dataengineering.unirest.coins.Tezos;

public class UnirestDatasourceTests {

    public UnirestDatasourceTests() {
    }
    
    public void testBinance() {
    String testBin = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "ETHUSDT")
                    .asString()
                    .getBody();
    
    System.out.println(testBin);

    JSONObject result = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "ETHUSDT")
				    .asJson()
				    .getBody()
				    .getObject();
    
    
    System.out.println(result);
                    
    Ethereum eth = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "ETHUSDT")
				    .asObject(Ethereum.class)
                    .getBody();
    
    System.out.println(eth.toString());
                    }

    public void testOKX(){
    String testBin = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "ETH-USDT")
                    .asString()
                    .getBody();
    
    System.out.println(testBin);

    Headers testHead = Unirest.get("https://www.okx.com/api/v5/market/ticker")
                    .queryString("instId", "ETH-USDT")
                    .asString()
                    .getHeaders();
    
    System.out.println(testHead);
    }

    public void testBitenium() {
    String testBin = Unirest.get("https://api.bitenium.com/spotapi/api/ticker24Hr")
                    .queryString("symbol", "ETHUSDT")
                    .asString()
                    .getBody();
    
    System.out.println(testBin);
    }

    public void testCoinsToList() {
        ArrayList <CoinsInterface> coins = new ArrayList<CoinsInterface>();
        CoinsInterface btc = new Bitcoin();
        btc = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "BTCUSDT")
				    .asObject(Bitcoin.class)
                    .getBody();
        coins.add(btc);

        CoinsInterface ada = new Cardano();
        ada = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "ADAUSDT")
				    .asObject(Cardano.class)
                    .getBody();
        coins.add(ada);

        CoinsInterface ctk = new CertiK();
        ctk = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "CTKUSDT")
				    .asObject(CertiK.class)
                    .getBody();
        coins.add(ctk);

        CoinsInterface doge = new Dogecoin();
        doge = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "DOGEUSDT")
				    .asObject(Dogecoin.class)
                    .getBody();
        coins.add(doge);

        CoinsInterface eth = new Ethereum();
        eth = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "ADAUSDT")
				    .asObject(Ethereum.class)
                    .getBody();
        coins.add(eth);

        CoinsInterface cake = new PancakeSwap();
        cake = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "CAKEUSDT")
				    .asObject(PancakeSwap.class)
                    .getBody();
        coins.add(cake);
        
        CoinsInterface rari = new Rarible();
        rari = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "RARIUSDT")
				    .asObject(Rarible.class)
                    .getBody();
        coins.add(rari);

        CoinsInterface safemoon = new SafeMoon();
        safemoon = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "SAFEMOONUSDT")
				    .asObject(SafeMoon.class)
                    .getBody();
        coins.add(safemoon);

        CoinsInterface shib = new ShibaInu();
        shib = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "SHIBUSDT")
				    .asObject(ShibaInu.class)
                    .getBody();
        coins.add(shib);

        CoinsInterface xtz = new Tezos();
        xtz = Unirest.get("https://api.binance.com/api/v3/ticker/24hr")
                    .queryString("symbol", "XTZUSDT")
				    .asObject(Tezos.class)
                    .getBody();
        coins.add(xtz);

        for (CoinsInterface x : coins) {
            System.out.println(x.toString());
        }
    }

}