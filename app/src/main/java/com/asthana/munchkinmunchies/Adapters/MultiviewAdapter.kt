package com.asthana.munchkinmunchies.Adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asthana.munchkinmunchies.Fragments.AllDetails
import com.asthana.munchkinmunchies.Models.bannermodel
import com.asthana.munchkinmunchies.R
import com.bumptech.glide.Glide
import org.w3c.dom.Text

abstract class BaseItem

data class BannerModel(val imageUrl: String, val txt: String, val textcolor: String) : BaseItem()

data class ItemCardModel(
    val dishTitle: String,
    val author: String,
    val stars: Float,
    val imageUrl: String
)

data class ItemCardContainer(val itemCardModels: List<ItemCardModel>) : BaseItem()


class MultiviewAdapter(private val items: List<BaseItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_BANNER = 0
    private val VIEW_TYPE_ITEM_CARD_CONTAINER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_BANNER -> {
                val bannerView = LayoutInflater.from(parent.context).inflate(R.layout.itemtypea, parent, false)
                BannerViewHolder(bannerView)
            }
            VIEW_TYPE_ITEM_CARD_CONTAINER -> {
                val itemCardContainerView = LayoutInflater.from(parent.context).inflate(R.layout.itemtypeb, parent, false)
                ItemCardContainerViewHolder(itemCardContainerView)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_BANNER -> {
                val bannerViewHolder = holder as BannerViewHolder
                val bannerModel = items[position] as BannerModel

                bannerViewHolder.txttitle.text = bannerModel.txt
                val textColor = Color.parseColor(bannerModel.textcolor) // Parse the hex color code
                bannerViewHolder.txttitle.setTextColor(textColor)
                // Bind data for the banner item
                Glide.with(bannerViewHolder.itemView)
                    .load(bannerModel.imageUrl)
                    .into(bannerViewHolder.imageView)

                if (bannerModel.imageUrl.isEmpty()) {
                    bannerViewHolder.imageView.visibility = View.GONE
                } else {
                    bannerViewHolder.imageView.visibility = View.VISIBLE
                }
            }
            VIEW_TYPE_ITEM_CARD_CONTAINER -> {
                val itemCardContainerViewHolder = holder as ItemCardContainerViewHolder
                val itemCardContainer = items[position] as ItemCardContainer
                val itemCardAdapter = ItemCardAdapter(itemCardContainer.itemCardModels)

                holder.itemView.setOnClickListener {
                    val allDetailsFragment = AllDetails()
                    val fragmentManager = holder.itemView.context as? FragmentManager
                    fragmentManager?.beginTransaction()
                        ?.replace(R.id.framefrag, allDetailsFragment)
                        ?.addToBackStack(null)
                        ?.commit()
                }

                // Update the layout manager to use GridLayoutManager
                val layoutManager = GridLayoutManager(itemCardContainerViewHolder.itemView.context, 2)
                itemCardContainerViewHolder.recyclerView.layoutManager = layoutManager
                itemCardContainerViewHolder.recyclerView.adapter = itemCardAdapter
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is BannerModel -> VIEW_TYPE_BANNER
            is ItemCardContainer -> VIEW_TYPE_ITEM_CARD_CONTAINER
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.banimg)
        var txttitle: TextView = itemView.findViewById(R.id.txtban)
    }

    inner class ItemCardContainerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerViewCardViews)
    }
}

class ItemCardAdapter(private val items: List<ItemCardModel>) : RecyclerView.Adapter<ItemCardAdapter.ItemCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.itemcardview, parent, false)
        return ItemCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemCardViewHolder, position: Int) {
        val itemCardModel = items[position]
        // Bind data for the item card
        holder.bind(itemCardModel)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dishTitleTextView: TextView = itemView.findViewById(R.id.fotitle)
        private val authorTextView: TextView = itemView.findViewById(R.id.author)
        private val ratingBar: RatingBar = itemView.findViewById(R.id.rating)
        private val imageView: ImageView = itemView.findViewById(R.id.img)

        fun bind(itemCardModel: ItemCardModel) {
            // Set the data values to the respective views
            dishTitleTextView.text = itemCardModel.dishTitle
            authorTextView.text = itemCardModel.author
            ratingBar.rating = itemCardModel.stars
            Glide.with(itemView)
                .load(itemCardModel.imageUrl)
                .into(imageView)
        }
    }
}
