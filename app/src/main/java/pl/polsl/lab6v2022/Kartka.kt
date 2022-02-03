package pl.polsl.lab6v2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView

class Kartka : AppCompatActivity() {
    var presentList: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val extras = intent.extras
        if (extras != null) {
            presentList = extras.getString("presents").toString()
        }

        //WebView - kontrolka wyswietlajaca html
        val page = WebView(this)

        //wlaczenie obslugi JS
        page.settings.javaScriptEnabled=true

        //dodanie interfejsu pomiędzy Kotlinem a JS
        //this - obiekt tej klasy dostarcza metody JSInterface, activity - nazwa widoczna w JS
        page.addJavascriptInterface(this, "activity") //ODKOMENTOWAC DLA JS

        //zaladowanie zawartosci kontroli WebView - pliki z katalogu assests w projekcie
        page.loadUrl("file:///android_asset/Kartka.html")

        //wstawienie kontrolki WebView jako calej fasady aktywnosci
        setContentView(page)
    }

    @JavascriptInterface
    fun getPresents(): String {
        return presentList
    }
}