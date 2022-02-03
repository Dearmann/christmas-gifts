package pl.polsl.lab6v2022

import android.content.ActivityNotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import java.util.*
import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult

import androidx.core.app.ActivityCompat.requestPermissions

import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.core.app.ActivityCompat


class Prezenty(var presentList: String = "") : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WebView - kontrolka wyswietlajaca html
        val page = WebView(this)

        //wlaczenie obslugi JS
        page.settings.javaScriptEnabled=true

        //dodanie interfejsu pomiędzy Kotlinem a JS
        //this - obiekt tej klasy dostarcza metody JSInterface, activity - nazwa widoczna w JS
        page.addJavascriptInterface(this, "activity")

        //zaladowanie zawartosci kontroli WebView - pliki z katalogu assests w projekcie
        page.loadUrl("file:///android_asset/Prezenty.html")

        //wstawienie kontrolki WebView jako calej fasady aktywnosci
        setContentView(page)
    }

    @JavascriptInterface //adnotacja sygnalizujaca ze metoda bedzie dostepna z poziomu JS
    fun addPresent(presentName: String) {
        if (presentName.isNotBlank()) {
            presentList += "$presentName<br>"
        }
    }

    @JavascriptInterface
    fun generateList() {
        val myIntent = Intent(this, Kartka::class.java)
        myIntent.putExtra("presents",this.presentList)
        startActivity(myIntent)
    }

    @JavascriptInterface
    fun getPresents(): String {
        return this.presentList
    }
}