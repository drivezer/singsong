package com.androdocs.navigationdrawer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.content_add.*
import kotlinx.android.synthetic.main.content_add.btndel
import kotlinx.android.synthetic.main.content_add.btnedit
import kotlinx.android.synthetic.main.content_add.txtlinkchord
import kotlinx.android.synthetic.main.content_add.txtlinksong
import kotlinx.android.synthetic.main.content_add.txtnamesong
import kotlinx.android.synthetic.main.content_add.txttextsong
import kotlinx.android.synthetic.main.content_edit.*
import kotlinx.android.synthetic.main.content_view.*
import kotlinx.android.synthetic.main.content_view.btn_back
import kotlinx.android.synthetic.main.content_view.texttitle

class ClassEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_edit)

        supportActionBar?.hide()

        var database = FirebaseDatabase.getInstance()
        var myRef = database.getReference("/tb_song")

        var songid = intent.extras?.getString(F_main_k().TAG_SONGID)
        var namesong = intent.extras?.getString(F_main_k().TAG_NAMESONG)
        var linkchord = intent.extras?.getString(F_main_k().TAG_LINKCHORD)
        var textsong = intent.extras?.getString(F_main_k().TAG_TEXTSONG)
        var linksong = intent.extras?.getString(F_main_k().TAG_LINKSONG)

        texttitle.setText("แก้ไขเพลง "+namesong)
        txtnamesong.setText(namesong)
        txtlinkchord.setText(linkchord)
        txttextsong.setText(textsong)
        txtlinksong.setText(linksong)

        btndel.setOnClickListener {

            songid?.let { it1 -> myRef.child(it1).removeValue()
                .addOnSuccessListener {
                    // Write was successful!
                    finish()

                    var goPageMain = Intent(this,MainActivity ::class.java)
                    startActivity(goPageMain)

                    Toast.makeText(this,"ลบเพลงสำเร็จ", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Write failed
                    // ...
                    Toast.makeText(this,"เกิดข้อผิดพลาดบางประการ", Toast.LENGTH_SHORT).show()
                }
            }

        }

        btnedit.setOnClickListener {

            var userObj = Users(txtnamesong.text.toString(),txtlinkchord.text.toString(),txttextsong.text.toString(),txtlinksong.text.toString())
            songid?.let { it1 -> myRef.child(it1).setValue(userObj)

                .addOnSuccessListener {
                // Write was successful!
                finish()

                var goPageMain = Intent(this,MainActivity ::class.java)
                startActivity(goPageMain)

                Toast.makeText(this,"แก้ไขเพลงสำเร็จ", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Write failed
                    // ...
                    Toast.makeText(this,"เกิดข้อผิดพลาดบางประการ", Toast.LENGTH_SHORT).show()
                }
            }

        }

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