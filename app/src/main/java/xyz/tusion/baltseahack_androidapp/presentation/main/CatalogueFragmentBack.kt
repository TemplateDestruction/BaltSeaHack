package xyz.tusion.baltseahack_androidapp.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.catalogue_item.view.*
import kotlinx.android.synthetic.main.event_layout.view.*
import kotlinx.android.synthetic.main.fragment_catalogue.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.model.CatalogueItem
import xyz.tusion.baltseahack_androidapp.domain.model.Event
import xyz.tusion.baltseahack_androidapp.domain.repository.RepositoryProvider
import xyz.tusion.baltseahack_androidapp.presentation.standard.LoadingDialog

class CatalogueFragmentBack : Fragment() {

    private lateinit var adapter: CatalogueBackItemsListAdapter
    private var dates = ArrayList<String>()
    private var messages = ArrayList<String>()


    private var catalogueItems = ArrayList<Event>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_catalogue, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = LoadingDialog.view(fragmentManager)
        RepositoryProvider
            .getJsonRepository()
            .allEvents
            .doOnSubscribe { dialog.showLoadingIndicator() }
            .doAfterTerminate { dialog.hideLoadingIndicator() }
            .subscribe(
                { fillInItems(it) },
                {

                }
            )
//        fillInItems()


    }

    private fun fillInItems(it: List<Event>) {
        catalogueItems.addAll(it)
        initRecyclerView()

    }

    private fun fillInItems() {
    }

    private fun initRecyclerView() {
        adapter = CatalogueBackItemsListAdapter(requireContext())
        fragCatalogue_rv.adapter = adapter
        adapter.updateItems(catalogueItems)
    }
}

class CatalogueBackItemsListAdapter(
    val context: Context,
    var eventList: MutableList<Event> = mutableListOf()
) : RecyclerView.Adapter<CatalogueBackItemsListAdapter.CatalogueBackItemViewHolder>() {

    fun updateItems(newItems: List<Event>) {
        eventList.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueBackItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.event_layout, parent, false)

        return CatalogueBackItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(holder: CatalogueBackItemViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    inner class CatalogueBackItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        private var absoluteNumber = 1
        fun bind(event: Event) {
//            containerView.catalogue_item_message.text = catalogueItem.message
            containerView.name_eventLayout.text = event.name
//            containerView.img_eventLayout
            containerView.description_eventLayout.text = event.description
            when (absoluteNumber % 6) {
                1 -> {
                    Picasso.with(context).load(R.drawable.img_1)
                        .into(containerView.img_eventLayout)
                }
                2 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img_2))
                }
                3 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img_3))
                }
                4 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img_4))
                }
                5 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img_5))
                }
                6 -> {
                    containerView.img_eventLayout.setImageDrawable(context.getDrawable(R.drawable.img_6))
                }
            }
            absoluteNumber++
        }


    }
}