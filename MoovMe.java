import org.joda.time.DateTime;

public class MoovMe {
    public static void main(String[] args) {
        DateTime aDate = null;
        DateTime endTime = DateTime.now();
        if (aDate!=null && (endTime.isBefore(aDate) || endTime.isEqual(aDate))){
            System.out.println("1" + endTime);
        }
        else {
            System.out.println("2");
        }
    }
}