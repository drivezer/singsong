package com.androdocs.navigationdrawer

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {

        context = this.baseContext
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.app_name, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        texttitle.setText("รายชื่อเพลงทั้งหมด")
        replaceFragment(F_main_k())


    }

    fun AppCompatActivity.replaceFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.content,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                texttitle.setText("รายชื่อเพลงทั้งหมด")
                replaceFragment(F_main_k())
                //Toast.makeText(this, "หน้าแรก clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_add -> {
                texttitle.setText("เพิ่มข้อมูล")
                replaceFragment(F_add_k())


                //Toast.makeText(this, "เพิ่มเพลง clicked", Toast.LENGTH_SHORT).show()
            }
//            R.id.nav_friends -> {
//                Toast.makeText(this, "Friends clicked", Toast.LENGTH_SHORT).show()
//            }
//            R.id.nav_update -> {
//                Toast.makeText(this, "Update clicked", Toast.LENGTH_SHORT).show()
//            }
            R.id.nav_login -> {
                texttitle.setText("เข้าสู่ระบบ")
                replaceFragment(F_login_k())
                //Toast.makeText(this, "Login clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                //Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                val mAlertDialog = AlertDialog.Builder(this)
            mAlertDialog.setIcon(R.mipmap.ic_launcher_round)
                    mAlertDialog.setTitle("ออกจากระบบ")
                    mAlertDialog.setMessage("คุณต้องการออกจากระบบใช่หรือไม่!!")
                    mAlertDialog.setPositiveButton("ใช่") { dialog, id ->
                //perform some tasks here
                Toast.makeText(this, "ออกจากระบบ", Toast.LENGTH_SHORT).show()
            }
                    mAlertDialog.setNegativeButton("ไม่") { dialog, id ->
                //perform som tasks here
                Toast.makeText(this, "ยกเลิก", Toast.LENGTH_SHORT).show()
            }
                    mAlertDialog.show()
        }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true


    }
}
