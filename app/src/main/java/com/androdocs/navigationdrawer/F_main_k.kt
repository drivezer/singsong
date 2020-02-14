package com.androdocs.navigationdrawer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.content_a.*


class F_main_k : Fragment() {

    public final var TAG_SONGID = "SONGID"
    public final var TAG_NAMESONG = "NAMESONG"
    public final var TAG_LINKCHORD = "LINKCHORD"
    public final var TAG_TEXTSONG = "TEXTSONG"
    public final var TAG_LINKSONG = "LINKSONG"

    var database = FirebaseDatabase.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.content_a, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //inint
        var arrData = ArrayList<Users>()
        var arrContractID = ArrayList<String>()

        var myRef = database.getReference("/tb_song")

        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                //clear data
                arrData = ArrayList<Users>()
                arrContractID = ArrayList<String>()

                var arrShow = ArrayList<String>()

                var mAdapter =
                    activity?.let { ArrayAdapter(it,android.R.layout.simple_list_item_1,arrShow) }
                var value = datasnapshot.children
                value.forEach {

                    arrContractID.add(it.key.toString())

                    arrData.add(Users(it.child("namesong").value.toString(),
                        it.child("linkchord").value.toString(),
                        it.child("textsong").value.toString() ,
                        it.child("linksong").value.toString()) )


                    arrShow.add(it.child("namesong").value.toString())
                    //Log.e("DATA",""+it.child("namesong").value)

                }

                mAdapter =
                    activity?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, arrShow) }
                listsong.adapter = mAdapter

            }

        })


        listsong.setOnItemClickListener { parent, view, position, id ->

            var data_namesong = arrData.get(position).namesong
            var data_textsong = arrData.get(position).textsong

            var goPageView = Intent(activity,ClassAdd ::class.java)

            //กำหนดข้อมูลและส่งข้อมูล (ชื่อของข้อมูล,ข้อมูล)
            goPageView.putExtra(TAG_NAMESONG,data_namesong)
            goPageView.putExtra(TAG_TEXTSONG,data_textsong)

            //เริ่มต้นเข้าสู่ activity second
            startActivity(goPageView)

        }

        listsong.setOnItemLongClickListener { parent, view, position, id ->


            var data_songid = arrContractID.get(position)
            var data_namesong = arrData.get(position).namesong
            var data_linkchord = arrData.get(position).linkchord
            var data_textsong = arrData.get(position).textsong
            var data_linksong = arrData.get(position).linksong

            var goPageEdit = Intent(activity,ClassEdit ::class.java)

            //กำหนดข้อมูลและส่งข้อมูล (ชื่อของข้อมูล,ข้อมูล)
            goPageEdit.putExtra(TAG_SONGID,data_songid)
            goPageEdit.putExtra(TAG_NAMESONG,data_namesong)
            goPageEdit.putExtra(TAG_LINKCHORD,data_linkchord)
            goPageEdit.putExtra(TAG_TEXTSONG,data_textsong)
            goPageEdit.putExtra(TAG_LINKSONG,data_linksong)

            //เริ่มต้นเข้าสู่ activity second
            startActivity(goPageEdit)
            true
        }


    }


}

