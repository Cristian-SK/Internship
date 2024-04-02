package com.example.testproject

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.leanback.widget.BrowseFrameLayout
import com.example.testproject.fragment.AppsFragment
import com.example.testproject.fragment.Dashboard_Fragment
import com.example.testproject.fragment.MessagesFragment
import com.example.testproject.fragment.NotificationsFragment
import com.example.testproject.fragment.ServicesFragment
import com.example.testproject.fragment.TvChannelFragment
import utils.Common
import utils.Constants


class MainActivity : FragmentActivity(), View.OnKeyListener {

    lateinit var navBar: BrowseFrameLayout
    lateinit var fragmentContainer: FrameLayout

    //    lateinit var btnSearch: TextView
    lateinit var btnHome: TextView
    lateinit var btnTv: TextView
    lateinit var btnMovies: TextView
    lateinit var btnApp: TextView
    lateinit var btnServices: TextView
    lateinit var btnNotifications: TextView
    lateinit var btnMessages: TextView
    lateinit var btnTest: LinearLayout
    lateinit var emptySpace: TextView
//    lateinit var btnSettings: TextView


    var SIDE_MENU = false
    var selectedMenu = Constants.MENU_HOME

    lateinit var lastSelectedMenu : View
    private var userInteractionListener: UserInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentContainer = findViewById(R.id.container)
        navBar = findViewById(R.id.blfNavBar)

//        btnSearch = findViewById(R.id.btn_search)
        btnHome = findViewById(R.id.btn_home)
        btnTv = findViewById(R.id.btn_tv)
        btnMovies = findViewById(R.id.btn_movies)
        btnApp = findViewById(R.id.btn_apps)
        btnServices = findViewById(R.id.btn_services)
        btnNotifications = findViewById(R.id.btn_notifications)
        btnMessages = findViewById(R.id.btn_messages)
        emptySpace = findViewById(R.id.space)
//        btnSettings = findViewById(R.id.btn_settings)

//        btnSearch.setOnKeyListener(this)
        btnHome.setOnKeyListener(this)
        btnTv.setOnKeyListener(this)
        btnMovies.setOnKeyListener(this)
        btnApp.setOnKeyListener(this)
        btnServices.setOnKeyListener(this)
        btnNotifications.setOnKeyListener(this)
        btnMessages.setOnKeyListener(this)
//        btnSettings.setOnKeyListener(this)

        btnHome.setOnKeyListener(this)
        btnTv.setOnKeyListener(this)
        btnMovies.setOnKeyListener(this)
        btnApp.setOnKeyListener(this)
        btnServices.setOnKeyListener(this)
        btnNotifications.setOnKeyListener(this)
        btnMessages.setOnKeyListener(this)

        lastSelectedMenu = btnMovies
        lastSelectedMenu.isActivated = true
        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_home_focus, 0, 0, 0);
        changeFragment(Dashboard_Fragment())



        btnHome.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnHome.setShadowLayer(8.0F, 0.0F, 0.0F, Color.WHITE);
            } else {

                // run scale animation and make it smaller
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnHome.setShadowLayer(0.0F, 0.0F, 0.0F, Color.TRANSPARENT);
            }
        }
        btnTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnTv.setShadowLayer(8.0F, 0.0F, 0.0F, Color.WHITE);
            } else {

                // run scale animation and make it smaller
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnTv.setShadowLayer(0.0F, 0.0F, 0.0F, Color.TRANSPARENT);
            }
        }
        btnMovies.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnMovies.setShadowLayer(8.0F, 0.0F, 0.0F, Color.WHITE);
            } else {

                // run scale animation and make it smaller
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnMovies.setShadowLayer(0.0F, 0.0F, 0.0F, Color.TRANSPARENT);
            }
        }
        btnApp.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnApp.setShadowLayer(8.0F, 0.0F, 0.0F, Color.WHITE);
            } else {

                // run scale animation and make it smaller
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnApp.setShadowLayer(0.0F, 0.0F, 0.0F, Color.TRANSPARENT);
            }
        }
        btnServices.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnServices.setShadowLayer(8.0F, 0.0F, 0.0F, Color.WHITE);
            } else {

                // run scale animation and make it smaller
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnServices.setShadowLayer(0.0F, 0.0F, 0.0F, Color.TRANSPARENT);
            }
        }
        btnNotifications.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnNotifications.setShadowLayer(8.0F, 0.0F, 0.0F, Color.WHITE);
            } else {

                // run scale animation and make it smaller
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnNotifications.setShadowLayer(0.0F, 0.0F, 0.0F, Color.TRANSPARENT);
            }
        }
        btnMessages.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_in)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnMessages.setShadowLayer(8.0F, 0.0F, 0.0F, Color.WHITE);
            } else {

                // run scale animation and make it smaller
                val anim = AnimationUtils.loadAnimation(this, R.anim.word_scale_out)
                view.startAnimation(anim)
                anim.fillAfter = true
                btnMessages.setShadowLayer(0.0F, 0.0F, 0.0F, Color.TRANSPARENT);
            }
        }
        emptySpace.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if(!SIDE_MENU && hasFocus) {
                if (SIDE_MENU) {

                }else {
                    openmenu()
                    SIDE_MENU = true
                    switchToLastSelectedMenu()
                }

            }
            else if (SIDE_MENU){

            }
        }

    }
    fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()

//        navBar.requestLayout()
//        navBar.layoutParams.width = Common.getWidthInPercent(this, 5)

//        SIDE_MENU = false

//      closemenu()
    }
    override fun onKey(view: View?, i: Int, key_event: KeyEvent?): Boolean {
        when(i) {

            KeyEvent.KEYCODE_DPAD_CENTER -> {

                lastSelectedMenu.isActivated = false
                view?.isActivated = true
                lastSelectedMenu = view!!

                when(view.id) {
//                    R.id.btn_search -> {
//                        selectedMenu = Constants.MENU_SEARCH
//                        changeFragment(com.example.testproject.fragment.SearchFragment())
//                    }
                    R.id.btn_tv -> {
                        selectedMenu = Constants.MENU_TV
                        changeFragment(TvChannelFragment())
                        btnTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_tv_focus, 0, 0, 0);

                        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home, 0, 0, 0);
                        btnMovies.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie, 0, 0, 0);
                        btnApp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_apps, 0, 0, 0);
                        btnServices.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_services, 0, 0, 0);
                        btnNotifications.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications, 0, 0, 0);
                        btnMessages.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_messages, 0, 0, 0);

                    }
                    R.id.btn_home -> {
                        selectedMenu = Constants.MENU_HOME
                        changeFragment(Dashboard_Fragment())
                        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_home_focus, 0, 0, 0);

                        btnTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv, 0, 0, 0);
                        btnMovies.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie, 0, 0, 0);
                        btnApp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_apps, 0, 0, 0);
                        btnServices.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_services, 0, 0, 0);
                        btnNotifications.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications, 0, 0, 0);
                        btnMessages.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_messages, 0, 0, 0);

                    }
                    R.id.btn_movies -> {
                        selectedMenu = Constants.MENU_MOVIE
                        changeFragment(HomeFragment())
//                        Handler(Looper.getMainLooper()).postDelayed(
//                            { fragmentContainer.requestFocus() },
//                            1000
//                        )


                        btnMovies.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_movies_focus, 0, 0, 0);

                        btnTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv, 0, 0, 0);
                        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home, 0, 0, 0);
                        btnApp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_apps, 0, 0, 0);
                        btnServices.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_services, 0, 0, 0);
                        btnNotifications.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications, 0, 0, 0);
                        btnMessages.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_messages, 0, 0, 0);
                    }
                    R.id.btn_apps -> {
                        selectedMenu = Constants.MENU_APPS
                        changeFragment(AppsFragment())
                        btnApp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_apps_focus, 0, 0, 0);

                        btnTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv, 0, 0, 0);
                        btnMovies.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie, 0, 0, 0);
                        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home, 0, 0, 0);
                        btnServices.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_services, 0, 0, 0);
                        btnNotifications.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications, 0, 0, 0);
                        btnMessages.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_messages, 0, 0, 0);
                    }
                    R.id.btn_services -> {
                        selectedMenu = Constants.MENU_SERVICES
                        changeFragment(ServicesFragment())
                        btnServices.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_services_focus, 0, 0, 0);

                        btnTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv, 0, 0, 0);
                        btnMovies.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie, 0, 0, 0);
                        btnApp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_apps, 0, 0, 0);
                        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home, 0, 0, 0);
                        btnNotifications.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications, 0, 0, 0);
                        btnMessages.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_messages, 0, 0, 0);
                    }
                    R.id.btn_notifications -> {
                        selectedMenu = Constants.MENU_NOTIFICATION
                        changeFragment(NotificationsFragment())
                        btnNotifications.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_notifications_focus, 0, 0, 0);

                        btnTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv, 0, 0, 0);
                        btnMovies.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie, 0, 0, 0);
                        btnApp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_apps, 0, 0, 0);
                        btnServices.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_services, 0, 0, 0);
                        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home, 0, 0, 0);
                        btnMessages.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_messages, 0, 0, 0);
                    }
                    R.id.btn_messages -> {
                        selectedMenu = Constants.MENU_MESSAGES
                        changeFragment(MessagesFragment())
                        btnMessages.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selected_messages_focus, 0, 0, 0);

                        btnTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tv, 0, 0, 0);
                        btnMovies.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_movie, 0, 0, 0);
                        btnApp.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_apps, 0, 0, 0);
                        btnServices.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_services, 0, 0, 0);
                        btnNotifications.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notifications, 0, 0, 0);
                        btnHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home, 0, 0, 0);
                    }
//                    R.id.btn_settings -> {
//                        selectedMenu = Constants.MENU_SEARCH
//                        changeFragment(SettingFragment())
//                    }
                }


            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if(!SIDE_MENU){
                    switchToLastSelectedMenu()
                    openmenu()
                    SIDE_MENU = true
                }
            }

        }
        return false
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && SIDE_MENU){
            SIDE_MENU = false
            closemenu()
        }

        if (keyCode == KeyEvent.KEYCODE_HOME) {
            Log.i(
                "TEST",
                "Home Button"
            ) // here you'll have to do something to prevent the button to go to the home screen
            changeFragment(HomeFragment())
            return true
        }

        return super.onKeyDown(keyCode, event)
    }
    override fun onBackPressed() {
        if (SIDE_MENU) {
            SIDE_MENU = false
            closemenu()
        } else if(!SIDE_MENU){
            switchToLastSelectedMenu()
            openmenu()
            SIDE_MENU = true
        } else {
            Toast.makeText(
                applicationContext,
                "You Are Not Allowed to Exit the App",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    fun switchToLastSelectedMenu(){
        when(selectedMenu){
//            Constants.MENU_SEARCH -> {
//                btnSearch.requestFocus()
//            }
            Constants.MENU_HOME -> {
                btnHome.requestFocus()

            }
            Constants.MENU_TV -> {
                btnTv.requestFocus()
            }
            Constants.MENU_MOVIE -> {
                btnMovies.requestFocus()
            }
            Constants.MENU_APPS -> {
                btnApp.requestFocus()
            }
            Constants.MENU_SERVICES -> {
                btnServices.requestFocus()
            }
            Constants.MENU_NOTIFICATION -> {
                btnNotifications.requestFocus()
            }
            Constants.MENU_MESSAGES -> {
                btnMessages.requestFocus()
            }

//            Constants.MENU_SEARCH -> {
//                btnSettings.requestFocus()
//            }

        }
    }
    fun openmenu() {
        val animSlide: Animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_left)
        navBar.startAnimation(animSlide)

        navBar.requestLayout()
        navBar.layoutParams.width = Common.getWidthInPercent(this, 16)
    }

    fun closemenu() {
        navBar.requestLayout()
        navBar.layoutParams.width = Common.getWidthInPercent(this, 5)

//        fragmentContainer.requestFocus()
        SIDE_MENU = false
    }
    fun setUserInteractionListener(userInteractionListener: UserInteractionListener) {
        this.userInteractionListener = userInteractionListener
    }
    override fun onUserInteraction() {
        super.onUserInteraction()
        userInteractionListener?.onUserInteraction()
    }


}