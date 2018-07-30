package com.demo.io;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * @author fangcong on 2018/7/19.
 */
public class LocaleDemo {

    public static void main(String[] args) throws Exception {
        Locale locale = new Locale("de", "DE");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        double amt = 123456.78;
        String result = numberFormat.format(amt);
        System.out.println(result);

        double money = 10000;
        NumberFormat euroFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String dollar = euroFormat.format(money);
        System.out.println(dollar);
        euroFormat.setCurrency(Currency.getInstance("EUR"));
        System.out.println(euroFormat.format(money));
    }
}
