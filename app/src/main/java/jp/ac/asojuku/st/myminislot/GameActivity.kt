package jp.ac.asojuku.st.myminislot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity() {
    var g1: Int?=null;
    var g2: Int?=null;
    var g3: Int?=null;

    var coin = 0;
    var kcoin = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)



        val id = intent.getIntExtra("Mycoin", 1000);
        coin = id;
        val id2 = intent.getIntExtra("kakecoin", 10);
        kcoin = id2;

        mycoin.text = id.toString();
        kake.text = id2.toString();

        back.setOnClickListener {finish()};
        btn_img.setOnClickListener {chenge(btn_img)};
        btn_img2.setOnClickListener {chenge(btn_img2)};
        btn_img3.setOnClickListener {chenge(btn_img3)};
    }

    fun chenge(imageButton: ImageButton){
        val ran = (Math.random() * 9).toInt();
        when(ran){
            0->imageButton.setImageResource(R.drawable.banana);
            1->imageButton.setImageResource(R.drawable.bar);
            2->imageButton.setImageResource(R.drawable.bigwin);
            3->imageButton.setImageResource(R.drawable.cherry);
            4->imageButton.setImageResource(R.drawable.grape);
            5->imageButton.setImageResource(R.drawable.lemon);
            6->imageButton.setImageResource(R.drawable.orange);
            7->imageButton.setImageResource(R.drawable.seven);
            8->imageButton.setImageResource(R.drawable.waltermelon);
        }
        when(imageButton){
            btn_img-> g1 = ran;
            btn_img2-> g2 = ran;
            btn_img3-> g3 = ran;
        }
        if(g1!=null && g2!=null && g3!=null){
            var su = 0;
            if(g1==7 && g2==7 && g3==7){
                su = 20;
            }else if(g1==2 && g2==2 && g3==2){
                su = 10;
            }else if(g1==1 && g2==1 && g3==1){
                su = 5;
            }else if(g1==g2 && g2==g3){
                su = 2;
            }else if(g1==7 && g2==7 || g1 == 7 && g3 == 7 || g2==7&&g3==7){
                su = 3;
            }else if(g1==g2 || g1==g3 || g2==g3){
                su = 1;
            }else if(g1==8 || g2==8 || g3==8){
                su = 1;
            }else if(g1==8 && g2==3 && g3==5){
                su = 30;
            }else if(g1==8 && g2==0 && g3==4){
                su = 10;
            }else{
                su=-1;
            }
            if (su == -1) {
                coin -= kcoin
            } else {
                coin += kcoin * su;
            }
            mycoin.text = coin.toString()
            val pref = PreferenceManager.getDefaultSharedPreferences(this);
            val editor = pref.edit();
            editor.putInt("temoti",coin).apply()

        }

    }

    override fun onResume() {
        super.onResume()
    }


}
