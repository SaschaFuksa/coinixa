package wi3.dataengineering.unirest.hilfsklassen;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * converting unix time to datetime format
 */
public class UnixToTimeStamp {
    
    /**
     * method for converting unix to datetime
     * @param time unix format
     * @return datetime string
     */
    public String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        return format.format(date);
    }

}
