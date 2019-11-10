package xyz.tusion.baltseahack_androidapp.presentation.teacher

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.catalogue_item.view.*
import kotlinx.android.synthetic.main.fragment_catalogue.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.model.CatalogueItem
import xyz.tusion.baltseahack_androidapp.domain.model.Event
import xyz.tusion.baltseahack_androidapp.domain.model.Visitor
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment
import xyz.tusion.baltseahack_androidapp.presentation.base.binding.ViewAction

class MyCatalogueFragment : BaseFragment(R.layout.fragment_my_catalog) {

    private lateinit var adapter: CatalogueItemsListAdapter
    private var dates = ArrayList<String>()
    private var messages = ArrayList<String>()
    val videoClickAction = ViewAction<Event>()


    private var catalogueItems = ArrayList<CatalogueItem>(9)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messages.addAll(resources.getStringArray(R.array.info))
        fillInItems()
        initRecyclerView()
    }

    private fun fillInItems() {
        var i = 0
        while (i < 6) {
            catalogueItems.add(
                CatalogueItem(
                    messages[i],
                    i + 1
                )
            )
            i++
//            Log.e("item: ", catalogueItems[i].date + " | " + catalogueItems[i].message + " | " + catalogueItems[i].imgId)
        }
    }

    override fun createBinds() {
        super.createBinds()
        rxBinds.addAll(
            videoClickAction.subscribe {
                val bundle = bundleOf("event" to it)
                navController.navigate(R.id.myEventFragment, bundle)
            }
        )
    }

    private fun initRecyclerView() {
        adapter = CatalogueItemsListAdapter(videoClickAction, requireContext())
        fragCatalogue_rv.adapter = adapter
        adapter.updateItems(catalogueItems)
    }
}

class CatalogueItemsListAdapter(
    val onVideoClickAction: ViewAction<Event>,
    val context: Context,
    var catalogueItemsList: MutableList<CatalogueItem> = mutableListOf()
) : RecyclerView.Adapter<CatalogueItemsListAdapter.CatalogueItemViewHolder>() {

    fun updateItems(newItems: List<CatalogueItem>) {
        catalogueItemsList.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.catalogue_item, parent, false)

        return CatalogueItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = catalogueItemsList.size

    override fun onBindViewHolder(holder: CatalogueItemViewHolder, position: Int) {
        holder.bind(catalogueItemsList[position])
    }

    inner class CatalogueItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(catalogueItem: CatalogueItem) {
//            containerView.catalogue_item_message.text = catalogueItem.message
            when (catalogueItem.imgId) {
                1 -> {
                    Picasso.with(context).load(R.drawable.img_1)
                        .into(containerView.catalogue_item_image)
                }
                2 -> {
                    containerView.catalogue_item_image.setImageDrawable(context.getDrawable(R.drawable.img_2))
                }
                3 -> {
                    containerView.catalogue_item_image.setImageDrawable(context.getDrawable(R.drawable.img_3))
                }
                4 -> {
                    containerView.catalogue_item_image.setImageDrawable(context.getDrawable(R.drawable.img_4))
                }
                5 -> {
                    containerView.catalogue_item_image.setImageDrawable(context.getDrawable(R.drawable.img_5))
                }
                6 -> {
                    containerView.catalogue_item_image.setImageDrawable(context.getDrawable(R.drawable.img_6))
                }
            }
            containerView.setOnClickListener {
                onVideoClickAction.accept(
                    Event(
                        catalogueItem.imgId, catalogueItem.message, "8.11.19 9:00", "8.11.19 9:00", emptyList(), ""))
            }
        }


    }
}