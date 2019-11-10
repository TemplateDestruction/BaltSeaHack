package xyz.tusion.baltseahack_androidapp.presentation.map

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.catalogue_item.view.*
import kotlinx.android.synthetic.main.club_item.*
import kotlinx.android.synthetic.main.club_item.view.*
import kotlinx.android.synthetic.main.search_frag.*
import xyz.tusion.baltseahack_androidapp.R
import xyz.tusion.baltseahack_androidapp.domain.model.Club
import xyz.tusion.baltseahack_androidapp.domain.spinerdialog.SpinnerDialog
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment

class SearchFragment : BaseFragment(R.layout.search_frag) {
    lateinit var spinnerSectionsDialog: SpinnerDialog
    lateinit var spinnerSkillsDialog: SpinnerDialog
    private var sections = ArrayList<String>()
    private var skills = ArrayList<String>()
    private var clubsWithSections = ArrayList<Club>()
    private var sectionsWithSkills = ArrayList<String>()
    private var clubs = ArrayList<Club>()
    lateinit var adapterSearchClubs: ClubsItemsListAdapter
    lateinit var adapterSearchSections: StringItemsListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sections.addAll((resources.getStringArray(R.array.sections)))
        val spinnerAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listOf("по секции", "по навыкам")
        )
        clubs.addAll(arguments?.getParcelableArrayList("clubs")!!)
        adapterSearchClubs = ClubsItemsListAdapter(requireContext())
        adapterSearchSections = StringItemsListAdapter(requireContext())
        fragSearchClubs_rv.adapter = adapterSearchClubs
//        fragSearchSections_rv.adapter = adapterSearchSections


        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_searchFragment.adapter = spinnerAdapter
        spinner_searchFragment.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    selectSection_searchFragment.visibility = View.VISIBLE
                    searchBySection_searchFragment.visibility = View.VISIBLE
//                    fragSearchSections_rv.visibility = View.VISIBLE
                    selectSkills_searchFragment.visibility = View.GONE
                    searchBySkills_searchFragment.visibility = View.GONE

                } else {
                    selectSection_searchFragment.visibility = View.GONE
                    searchBySection_searchFragment.visibility = View.GONE
                    selectSkills_searchFragment.visibility = View.VISIBLE
                    searchBySkills_searchFragment.visibility = View.VISIBLE
//                    fragSearchSections_rv.visibility = View.GONE

                }
            }

        }


        spinnerSectionsDialog =
            SpinnerDialog(requireActivity(), sections, "Выберите секцию", "Закрыть")
        spinnerSectionsDialog.setCancellable(true) // for cancellable
        spinnerSectionsDialog.setShowKeyboard(false) // for open keyboard by default
        spinnerSectionsDialog.bindOnSpinerListener { item, position ->
            selectSection_searchFragment.text = item.toString()
            spinnerSectionsDialog.closeSpinerDialog()
        }

        spinnerSkillsDialog = SpinnerDialog(requireActivity(), skills, "Выберите навыки", "Закрыть")
        spinnerSkillsDialog.setCancellable(true) // for cancellable
        spinnerSkillsDialog.setShowKeyboard(false) // for open keyboard by default
        spinnerSkillsDialog.bindOnSpinerListener { item, position ->
            selectSkills_searchFragment.text = item.toString()
            spinnerSectionsDialog.closeSpinerDialog()
        }
        selectSection_searchFragment.setOnClickListener {
            spinnerSectionsDialog.showSpinerDialog()
        }
        selectSkills_searchFragment.setOnClickListener {
            spinnerSkillsDialog.showSpinerDialog()
        }
        searchBySection_searchFragment.setOnClickListener {
            fragSearchClubs_rv.visibility = View.VISIBLE
            clubsWithSections.clear()
            for (club in clubs) {
                club.sections.forEach {
                    if (it.name == selectSection_searchFragment.text) {
                        clubsWithSections.add(club)
                        adapterSearchClubs.updateItems(clubsWithSections)
                        Log.e("SearchFragment", club.name + " | " + club.sections)

                    }
                }
            }
        }
        searchBySkills_searchFragment.setOnClickListener {
            //            TODO request to sever and fill array
//            fragSearchSections_rv.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun createBinds() {
        super.createBinds()
        rxBinds.addAll(

        )
    }
}

class StringItemsListAdapter(
    val context: Context,
    var stringsList: MutableList<String> = mutableListOf()
) : RecyclerView.Adapter<StringItemsListAdapter.CatalogueItemViewHolder>() {

    fun updateItems(newItems: List<String>) {
        stringsList.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.string_item, parent, false)

        return CatalogueItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = stringsList.size

    override fun onBindViewHolder(holder: CatalogueItemViewHolder, position: Int) {
        holder.bind(stringsList[position])
    }

    inner class CatalogueItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(string: String) {
            containerView.catalogue_item_message.text = string
        }


    }
}

class ClubsItemsListAdapter(
    val context: Context,
    var clubsList: MutableList<Club> = mutableListOf()
) : RecyclerView.Adapter<ClubsItemsListAdapter.ClubsItemViewHolder>() {

    fun updateItems(newItems: List<Club>) {
        clubsList.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubsItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.club_item, parent, false)

        return ClubsItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = clubsList.size

    override fun onBindViewHolder(holder: ClubsItemViewHolder, position: Int) {
        holder.bind(clubsList[position])

    }

    inner class ClubsItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(club: Club) {
            containerView.clubName_clubItem.text  = club.name
            containerView.clubAdress_clubItem.text  = club.adress
            containerView.layout_club_item.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToMapFragment(club)
//                action.arguments.putParcelable("club", club)
//                action.arguments.putString("dich", "dich")
//                var bundle = bundleOf("club" to club)
                it.findNavController().navigate(action)
            }

        }


    }
}