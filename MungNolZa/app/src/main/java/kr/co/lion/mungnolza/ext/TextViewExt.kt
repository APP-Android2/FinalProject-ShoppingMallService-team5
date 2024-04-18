package kr.co.lion.mungnolza.ext

import android.widget.TextView
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun TextView.moneyFormat(money: Int): String{
    val koreanLocale = Locale("ko", "KR")
    val formatter = NumberFormat.getCurrencyInstance(koreanLocale)

    val koreanCurrency = Currency.getInstance("KRW")
    formatter.currency = koreanCurrency

    return formatter.format(money) + " 원"
}