package arthur.ddmo.mobilebudget;

import java.text.NumberFormat;

/**
 * Created by arthur on 08/12/14.
 */
public class Utils {
    public static String formatMoney(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }
}
