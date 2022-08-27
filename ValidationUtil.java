import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 
 * validate all the input get from the user
 * </p>
 */
public class ValidationUtil {
   static String NAME_REGEX = "^[A-Za-z]\\w{3,29}$";
   static String ID_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
   static String  DATE_REGEX = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
   static String MOBILE_NUMBER_REGEX = "^[6-9][0-9]{9}$";
    public static boolean isValid(String data,String regex) {  
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        return matcher.matches();
    }

    /**
     * <p> 
     * calculate the age of Employee
     * </p.
     *
     */
    public static int calculateAge(String dob) {
        int age = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	try {
            Date date = formatter.parse(dob);
            Instant instant = date.toInstant();
            ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
            LocalDate givenDate = zone.toLocalDate();
            Period period = Period.between(givenDate, LocalDate.now());
	    age = period.getYears();
	} catch (Exception e) {
           System.out.println("please enter valid dob");
	}
        return age;
    }
}
   