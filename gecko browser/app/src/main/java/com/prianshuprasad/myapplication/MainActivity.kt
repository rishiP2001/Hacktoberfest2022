package com.prianshuprasad.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoSessionSettings
import org.mozilla.geckoview.GeckoSessionSettings.USER_AGENT_MODE_MOBILE
import org.mozilla.geckoview.GeckoView

class MainActivity : AppCompatActivity() {
    private lateinit var progressView: ProgressBar
    private lateinit var geckoView: GeckoView
    private lateinit var urlEditText: EditText

    private val geckoSession = GeckoSession()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupGeckoView()
        supportActionBar?.hide()
        urlEditText= findViewById(R.id.location_view)

//        setSupportActionBar(findViewById(R.id.toolbar))
//        setupToolbar()
        val settings = GeckoSessionSettings.Builder()
            .usePrivateMode(true)
            .useTrackingProtection(true)
            .userAgentMode(USER_AGENT_MODE_MOBILE)
            .userAgentOverride("")
            .suspendMediaWhenInactive(true)
            .allowJavascript(true)




        urlEditText.setOnEditorActionListener(object :
            View.OnFocusChangeListener, TextView.OnEditorActionListener {

            override fun onFocusChange(view: View?, hasFocus: Boolean) = Unit

            override fun onEditorAction(
                textView: TextView,
                actionId: Int,
                event: KeyEvent?
            ): Boolean {

                Toast.makeText(this@MainActivity,"ooo",Toast.LENGTH_LONG).show()
                onCommit(textView.text.toString())
//                textView.hideKeyboard()
                return true
            }
        })







    }
    private fun setupGeckoView() {
        // 1
        geckoView = findViewById(R.id.geckoview)

        // 2
        val runtime = GeckoRuntime.create(this)
        geckoSession.open(runtime)

        // 3
        geckoView.setSession(geckoSession)

        // 4
        geckoSession.loadUri("https://google.com")
        progressView = findViewById(R.id.page_progress)
        geckoSession.progressDelegate = createProgressDelegate()
    }

    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
    }

    fun onCommit(text: String) {
        if ((text.contains(".") ||
                    text.contains(":")) &&
            !text.contains(" ")) {
            geckoSession.loadUri(text)
        } else {
            geckoSession.loadUri("https://www.google.com/search?q=$text" )
        }

        geckoView.requestFocus()
    }

    private fun createProgressDelegate(): GeckoSession.ProgressDelegate {
        return object : GeckoSession.ProgressDelegate {

            override fun onPageStop(session: GeckoSession, success: Boolean) = Unit

            override fun onSecurityChange(
                session: GeckoSession,
                securityInfo: GeckoSession.ProgressDelegate.SecurityInformation
            ) = Unit

            override fun onPageStart(session: GeckoSession, url: String) = Unit

            override fun onProgressChange(session: GeckoSession, progress: Int) {
                progressView.progress = progress

                if (progress in 1..99) {
                    progressView.visibility = View.VISIBLE
                } else {
                    progressView.visibility = View.GONE
                }
            }
        }
    }





}