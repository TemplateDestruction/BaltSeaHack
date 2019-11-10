package xyz.tusion.baltseahack_androidapp.presentation.teacher

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.frag_my_event.*
import kotlinx.android.synthetic.main.frag_visitor_details.*
import kotlinx.android.synthetic.main.item_skill.*
import kotlinx.android.synthetic.main.item_visitor.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.model.Skill
import xyz.tusion.baltseahack_androidapp.domain.model.Visitor
import xyz.tusion.baltseahack_androidapp.domain.model.listOfVisitor
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment
import xyz.tusion.baltseahack_androidapp.presentation.base.binding.ViewAction

class VisitorDetailsFragment: BaseFragment(R.layout.frag_visitor_details) {

    private lateinit var adapter: SkillsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        details_back.setOnClickListener {
            navController.popBackStack()
        }
        arguments?.getParcelable<Visitor>("visitor")
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        adapter.updateItems(listOfSkills)
        frag_skills_list_rv.setItemViewCacheSize(listOfVisitor.size)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun createBinds() {
        super.createBinds()
        rxBinds.addAll(

        )
    }

    private fun initRecyclerView() {
        adapter = SkillsListAdapter(requireContext())
        frag_skills_list_rv.adapter = adapter
    }
}

class SkillsListAdapter(
    val context: Context,
    var videoList: MutableList<Skill> = mutableListOf()
) : RecyclerView.Adapter<SkillsListAdapter.VideoItemViewHolder>() {

    fun updateItems(newItems: List<Skill>) {
        videoList.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)

        return VideoItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = videoList.size

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    inner class VideoItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(skill: Skill) {

            skill_name.text = skill.name
            skill_value.text = skill.value.toString()
        }
    }
}


val listOfSkills = listOf<Skill>(
    Skill("Выносливость", 14),
    Skill("Актерское мастерсво", 4),
    Skill("Коммуникабельность", 99),
    Skill("Выносливость", 14),
    Skill("Актерское мастерсво", 4),
    Skill("Коммуникабельность", 99),
    Skill("Выносливость", 14),
    Skill("Актерское мастерсво", 4),
    Skill("Коммуникабельность", 99),
    Skill("Выносливость", 14),
    Skill("Актерское мастерсво", 4),
    Skill("Коммуникабельность", 99),
    Skill("Выносливость", 14),
    Skill("Актерское мастерсво", 4),
    Skill("Коммуникабельность", 99),
    Skill("Выносливость", 14),
    Skill("Актерское мастерсво", 4),
    Skill("Коммуникабельность", 99)
)