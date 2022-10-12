package com.prianshuprasad.myapplication


import android.graphics.Bitmap
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoView
import org.mozilla.geckoview.Image

class contestAdapter(private val listener: MainActivity2) :
    RecyclerView.Adapter<contestAdapter.ViewHolder>() {


    private val item: ArrayList<GeckoSession> = ArrayList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var geckoView: GeckoView
        var webName: TextView
        var closeButton:ImageView
        var image:ImageView


        init {


            geckoView=view.findViewById(R.id.tabWebView)
            webName = view.findViewById(R.id.webName)
            closeButton =view.findViewById(R.id.closeTab)
            image= view.findViewById(R.id.image)


        }




    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_view, viewGroup, false)

        val viewHolder= ViewHolder(view)
        viewHolder.geckoView.setOnClickListener{


            listener.openWeb(viewHolder.adapterPosition)
        }

        viewHolder.image.setOnClickListener {
            listener.openWeb(viewHolder.adapterPosition)
        }

        viewHolder.closeButton.setOnClickListener {
           listener.removeTab(viewHolder.adapterPosition)
        }


        return viewHolder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val curritem = item[position]



        viewHolder.webName.text = "WebPage ${viewHolder.adapterPosition+1}"

        viewHolder.geckoView.setSession(curritem )

        viewHolder.image.bringToFront()
        viewHolder.closeButton.bringToFront()


//        viewHolder.geckoView.releaseSession()



    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = item.size


    fun updatenews(array:ArrayList<GeckoSession>){

        item.clear()
        item.addAll(array)

        notifyDataSetChanged()

    }





}
