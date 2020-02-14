package com.androdocs.navigationdrawer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_view.*

class ClassAdd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_view)

        supportActionBar?.hide()

        var namesong = intent.extras?.getString(F_main_k().TAG_NAMESONG)
        var textsong = intent.extras?.getString(F_main_k().TAG_TEXTSONG)

        texttitle.setText("เพลง "+namesong)
        viewtextsong.setText(""+textsong)

        btn_back.setOnClickListener{
            finish()
        }
    }

    var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            //super.onBackPressed()
            //finish()
            finishAffinity()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "กดอีกครั้งเพื่อออกจากแอพ", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


}