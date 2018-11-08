package jp.ac.asojuku.st.myminislot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var my_coin:Int = 1000;
    var kake_coin:Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener { onstartBtnTap(it) };
        betup.setOnClickListener { onupBtn() };
        betdown.setOnClickListener {ondoBtn()};
        button.setOnClickListener {reset()};
    }
    fun onstartBtnTap(view: View?){
        val intent = Intent(this, GameActivity::class.java);
        intent.putExtra("Mycoin", my_coin);
        intent.putExtra("kakecoin", kake_coin);

        this.startActivity(intent);
    }

    fun onupBtn() {
        if(my_coin > 0) {
            kake_coin += 10;
            kake.text = kake_coin.toString();
            my_coin -= 10
            mycoin.text = my_coin.toString()
        }
    }

    fun ondoBtn(){
        if(kake_coin > 0) {
            kake_coin -= 10;
            kake.text = kake_coin.toString();
            my_coin += 10;
            mycoin.text = my_coin.toString();
        }
    }

    fun reset(){
        my_coin = 1000;
        mycoin.text = my_coin.toString();
        kake_coin = 0;
        kake.text = kake_coin.toString();
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        val editor = pref.edit();
        editor.clear().apply();
    }

    override fun onResume() {
        super.onResume()
        val pref = PreferenceManager.getDefaultSharedPreferences(this);
        my_coin = pref.getInt("temoti",1000);
        kake_coin = 0;
        kake.text = kake_coin.toString()
        mycoin.text = my_coin.toString();
    }
}
