package com.prianshuprasad.myapplication

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoView

class MainActivity2 : AppCompatActivity() {

    private lateinit var progressView: ProgressBar

    private lateinit var newTab: ImageButton
    private lateinit var tabList:ImageButton
    private var sessionList = mutableListOf<GeckoSession>()
    private lateinit var geckoView: GeckoView
    private lateinit var webView: RelativeLayout
    private lateinit var tabView:RelativeLayout
    private lateinit var backButton: ImageButton
    private lateinit var rcView:RecyclerView
    private lateinit var mAdapter: contestAdapter
    private lateinit var urlEditText:EditText
    var currIndex=0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.hide()
        progressView = findViewById(R.id.page_progress)
        geckoView= findViewById(R.id.geckoview)

        newTab= findViewById(R.id.newTab)
        tabList= findViewById(R.id.tabs)
        backButton = findViewById(R.id.back)

        webView = findViewById(R.id.WebView)
        tabView = findViewById(R.id.tabView)

        rcView = findViewById(R.id.rcView)
        mAdapter=  contestAdapter(this)

        editUrlEdittext()

        newTab.setOnClickListener {
            addNewSession()
            openWebView()
        }

        tabList.setOnClickListener {

            openTabPreView()
        }

        backButton.setOnClickListener {
           openWeb(currIndex)
//            openWebView()


        }

        rcView.layoutManager = GridLayoutManager(this,2)
//        mAdapter=  contestAdapter(this)
        rcView.adapter= mAdapter


        addNewSession()

        openWebView()





    }

    fun editUrlEdittext(){
        urlEditText= findViewById(R.id.location_view)
        urlEditText.setOnEditorActionListener(object :
            View.OnFocusChangeListener, TextView.OnEditorActionListener {

            override fun onFocusChange(view: View?, hasFocus: Boolean) = Unit

            override fun onEditorAction(
                textView: TextView,
                actionId: Int,
                event: KeyEvent?
            ): Boolean {


                onCommit(textView.text.toString())
//                textView.hideKeyboard()
                return true
            }
        })

    }
    fun onCommit(text: String) {
        if ((text.contains(".") ||
                    text.contains(":")) &&
            !text.contains(" ")) {
            sessionList[currIndex].loadUri(text)
        } else {
            sessionList[currIndex].loadUri("https://www.google.com/search?q=$text" )
        }

        geckoView.requestFocus()
    }


    fun openWebView(){



        tabView.visibility= View.GONE
        webView.visibility = View.VISIBLE

    }

    fun openTabPreView(){

        mAdapter.updatenews(sessionList as ArrayList<GeckoSession>)

        tabView.visibility= View.VISIBLE
        webView.visibility = View.GONE

    }


    fun openWeb(index:Int){
        openWebView()
        if(currIndex>= sessionList.size)
        {
            addNewSession()
            Toast.makeText(this,"New Tab Added",Toast.LENGTH_LONG).show()

        }else {


            currIndex = index
            geckoView.releaseSession()




            geckoView.setSession(sessionList[index])
            sessionList[index].progressDelegate = createProgressDelegate()
        }


    }

    fun addNewSession(){

        val geckoSession = GeckoSession()

        val runtime = GeckoRuntime.getDefault(this)
        geckoSession.open(runtime)




        geckoSession.loadUri("https://google.com")
        sessionList.add(geckoSession)

        openWeb(sessionList.size-1)


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

    fun removeTab(index:Int){
        sessionList.removeAt(index)
        mAdapter.updatenews(sessionList as ArrayList<GeckoSession> )
        currIndex=0;
    }





}