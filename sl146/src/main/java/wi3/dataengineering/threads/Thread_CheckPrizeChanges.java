package wi3.dataengineering.threads;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * class will be used to check prize changes of coins
 * if the last price change exceeds a specific threshold:
 *      -> check e-mail list with subsribers
 *      -> send notification to all e-mails
 */
public class Thread_CheckPrizeChanges implements Runnable{
    //private final String path = "C:/Data/";
    private final String path = "/home/sascha_fuksa/Data/";
    private final String subscriberPath = path + "/UI/subscriber.json";
    private final String[] apis = {"binance", "bitenium", "okx"};
    private final String[] coins = {"bitcoin", "cardano", "dogecoin", "ethereum", "shibainu", "tezos"};
    private final int schwellwert = 5;
    private Boolean running = false;
    private Thread worker; 
    JSONParser parser = new JSONParser();

    //test 
    public void test() {
        checkChanges();;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run() {
        running = true;  
        checkChangesAndSendEmail();    
    }

    public void stopThread() {
        running = false;
        worker.interrupt();
    }
    
    /**
     * thread loop reocurring every 5 minutes
     */
    private void checkChangesAndSendEmail() {
        
        while (running) {
            System.out.println("Starting priceChange notifications worker! -- Threshold: " + schwellwert + "% -- Time: " + System.currentTimeMillis());
            checkChanges();
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted");
            }
        }   
    }

    /**
     * checking all price changes in percent of all six coins
     * loads newest safed coin data and checks if value exceeds threshold
     */
    private void checkChanges() {
        Boolean notify = false;
        ArrayList <String> coinWarnings = new ArrayList<>();
 
        for (String api: apis) {
            String apiPath = path + api + "/";
            for (String coin : coins) {
                String coinPath = apiPath + coin + "/coin_Value_" + api + "_" + coin + ".json";
                try {
                    JSONObject coinJSON = (JSONObject) parser.parse(new FileReader(coinPath));
                    double priceChangePercent = (double) coinJSON.get("priceChangePercent");
                    //System.out.println("Pricechange Value von: -- " + api + " -- " + coin + " -->  " + priceChangePercent + "%");

                    double absPriceChangePercent = Math.abs(priceChangePercent);
  
                    //checking if change in value exceeds threshold
                    //raising a flag (notify) if threshold is reached or exceeded
                    //adds the coin-name to a list
                    if (absPriceChangePercent >= schwellwert) {
                        notify = true;
                        coinWarnings.add(coin);
                    }

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        // if the notify flag was raised, were calling the method to send notifications
        if (notify) {
            System.out.println("Sending E-Mail notifications! -- Time: " + System.currentTimeMillis());
            sendNotifications(coinWarnings);
            System.out.println("Sent E-Mail notifications to all subsriber! -- Time: " + System.currentTimeMillis());
        }  

        //resetting the flag after sending notifications
        notify = false;
    }

    /**
     * for every subsriber where calling the method to send an email
     * @param coinWarnings all coins which price change exceeds the threshold
     */
    private void sendNotifications(ArrayList<String> coinWarnings) {
        for (String sub : getSubscribers()) {
            generateAndSendEmail(coinWarnings, sub);
        }
    }

    /**
     * building connection to mailtrap
     * building email with coin-names and subscriber email
     * sending email
     * 
     * mailtrap.io is a service (free plan used) to send and trap emails without sending
     * them to the intended recipient. Where using it to test our notifications, without needing to send to actual emails
     * 
     * @param coinWarnings all coins which price change exceeds the threshold
     * @param emailTo email adress of a subsriber
     */
    private void generateAndSendEmail(ArrayList<String> coinWarnings, String emailTo) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "2525");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("0985cceffbba8a", "f4c6db3c65386e");
            }
        });

        Message message = new MimeMessage(session);
        
        try {
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
            Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Mail Subject");

            String msg = "Folgende Coins hatten eine starke Kursänderung: "
                + coinWarnings.toString()
                + "Bitte prüfen!";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * loading safed subriber emails into arraylist
     * @return arraylist with all subscriber emails
     */
    private ArrayList<String> getSubscribers() {
        ArrayList<String> subscribers = new ArrayList<>();
        try {
            JSONArray subscriberJSON = (JSONArray) parser.parse(new FileReader(subscriberPath));
            for (Object sub : subscriberJSON) {
                subscribers.add(sub.toString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return subscribers;
    }
}
