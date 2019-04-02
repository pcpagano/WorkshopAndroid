package android.workshop.clase4

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.NumberFormat

class SearchItemAdapter (private val results : List<Results>, private val onclickAction: (String) -> Unit ) : RecyclerView.Adapter<SearchItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val searchItemRow = view.findViewById<ConstraintLayout>(R.id.searchItemRow)
        val title = view.findViewById<TextView>(R.id.itemTitle)
        val price = view.findViewById<TextView>(R.id.itemPrice)
        val thumb = view.findViewById<ImageView>(R.id.itemImage)
    }

    val format = NumberFormat.getCurrencyInstance()

    init {
        format.maximumFractionDigits = 0
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(p0.context).inflate(R.layout.search_item_row, p0, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        val item = results.get(p1)
        p0.title.text = item.title
        p0.price.text = format.format(item.price.toDouble())
        Picasso.get().load(item.thumbnail).resize(100, 100).into(p0.thumb)
        p0.searchItemRow.setOnClickListener { onclickAction(item.id) }
    }
}