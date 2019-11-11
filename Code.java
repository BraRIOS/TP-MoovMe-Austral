public class Code {
    private static int code;

    public static String getCode() {
        code++;
        return "##"+code+"##";
    }
}
