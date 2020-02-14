package com.androdocs.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.content_add.*

class F_add_k : Fragment() {

    var database = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.content_add, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //inint
        var arrData = ArrayList<Users>()
        var arrContractID = ArrayList<String>()

        var myRef = database.getReference("/tb_song")

        btndel.setOnClickListener {

            cleartext()
        }

        btnedit.setOnClickListener {

            val namecheck:String = txtnamesong?.text.toString()
            val textsongcheck:String = txttextsong?.text.toString()

            if(namecheck.equals("")){
                txtnamesong?.error="กรุณากรอกช่องนี้"
            }else if(textsongcheck.equals("")){
                txttextsong?.error="กรุณากรอกช่องนี้"
            }else{
                var userObj  = Users(txtnamesong.text.toString() , txtlinkchord.text.toString(), txttextsong.text.toString(), txtlinksong.text.toString())

                myRef.push().setValue(userObj)

                    .addOnSuccessListener {
                        // Write was successful!
                        // ...
                        cleartext()
                        Toast.makeText(activity,"เพิ่มเพลงสำเร็จ",Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        // Write failed
                        // ...
                        Toast.makeText(activity,"เกิดข้อผิดพลาดบางประการ",Toast.LENGTH_SHORT).show()
                    }
            }

        }

    }

    fun cleartext(){
        txtnamesong.setText("")
        txtlinkchord.setText("")
        txttextsong.setText("")
        txtlinksong.setText("")
    }

}
