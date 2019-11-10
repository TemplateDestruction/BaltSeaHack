package xyz.tusion.baltseahack_androidapp.presentation.teacher

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.frag_my_event.*
import kotlinx.android.synthetic.main.item_visitor.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.model.Event
import xyz.tusion.baltseahack_androidapp.domain.model.Visitor
import xyz.tusion.baltseahack_androidapp.domain.model.listOfVisitor
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment
import xyz.tusion.baltseahack_androidapp.presentation.base.binding.ViewAction
import xyz.tusion.baltseahack_androidapp.presentation.qr.QrDialog

class MyEventFragment : BaseFragment(R.layout.frag_my_event) {

    private lateinit var adapter: VisitorListAdapter
    val videoClickAction = ViewAction<Visitor>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show_qr.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            val newFragment = QrDialog()

            val bundle = Bundle()
            bundle.putString("eventId", "ID_4444194783")

            newFragment.arguments = bundle
            newFragment.show(ft, "dialog")
        }

        back_arrow.setOnClickListener {
            navController.popBackStack()
        }
        back_word.setOnClickListener {
            navController.popBackStack()
        }
            event_name.text = arguments?.getParcelable<Event>("event")?.name
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        adapter.updateItems(listOfVisitor)
        frag_visitors_list_rv.setItemViewCacheSize(listOfVisitor.size)
    }

    override fun createBinds() {
        super.createBinds()
        rxBinds.addAll(
            videoClickAction.subscribe {
                val bundle = bundleOf("visitor" to it)
                navController.navigate(R.id.visitorDetailsFragment, bundle)
            }
        )
    }

    private fun initRecyclerView() {
        adapter = VisitorListAdapter(videoClickAction, requireContext())
        frag_visitors_list_rv.adapter = adapter
    }
}

class VisitorListAdapter(
    val onVideoClickAction: ViewAction<Visitor>,
    val context: Context,
    var videoList: MutableList<Visitor> = mutableListOf()
) : RecyclerView.Adapter<VisitorListAdapter.VideoItemViewHolder>() {

    fun updateItems(newItems: List<Visitor>) {
        videoList.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_visitor, parent, false)

        return VideoItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = videoList.size

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    inner class VideoItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(visitor: Visitor) {

            visitor_name.text = visitor.name
            visitor_number.text = "${adapterPosition + 1}."
            containerView.setOnClickListener {
                onVideoClickAction.accept(visitor)
            }
        }
    }
}
