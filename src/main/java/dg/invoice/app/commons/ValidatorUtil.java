package dg.invoice.app.commons;

public class ValidatorUtil {

    public static Integer convertToInteger(String string){
        String regex = "\\d+";
        if(string.matches(regex)){
            return Integer.parseInt(string);
        }
        return null;
    }

    public static Double convertToDouble(String string){
        String regex = "\\d+\\.\\d+";
        if(convertToInteger(string) != null || string.matches(regex)){
            return Double.parseDouble(string);
        }
        return null;
    }

    public static boolean isNipValid(String nip){
        String nipRegex = "^(\\d{3}[- ]\\d{3}[- ]\\d{2}[- ]\\d{2})";
        return nip.matches(nipRegex);
    }

    public static boolean isPostalCodeValid(String postalCode){
        String postalCodeRegex = "\\d{2}-\\d{3}";
        return postalCode.matches(postalCodeRegex);
    }

    public static boolean isNumeral(String string){
        String regex = "\\d+";
        return string.matches(regex);
    }
}
