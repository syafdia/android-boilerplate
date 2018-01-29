package com.github.syafdia.androidboilerplate.feature.information

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.syafdia.androidboilerplate.R
import com.github.syafdia.androidboilerplate.databinding.FragmentInformationBinding
import com.github.syafdia.androidboilerplate.feature.BaseFragment
import javax.inject.Inject


class InformationFragment : BaseFragment<FragmentInformationBinding, InformationViewModel>() {

    override val viewId: Int = R.layout.fragment_information

    override lateinit var viewModel: InformationViewModel

    @Inject
    lateinit var viewModelFactory: InformationViewModelFactory

    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var infoAdapter: InformationAdapter

    private val infos = mutableListOf<InformationItemModel>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this, viewModelFactory)
                .get(InformationViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)

        recyclerView  = rootView?.findViewById(R.id.recyclerView_information) as RecyclerView

        infoAdapter = InformationAdapter(infos)
        layoutManager = LinearLayoutManager(activity)

        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = infoAdapter

        seedData()

        return rootView
    }

    private fun seedData() {
        (0 until 25).mapTo(infos) { InformationItemModel(
                title = "Title ${it + 1}",
                body = """
Many say exploration is part of our destiny, but it’s actually our duty to future generations and their quest to ensure the survival of the human species.
If you could see the earth illuminated when you were in a place as dark as night, it would look to you more splendid than the moon.
Astronomy compels the soul to look upward, and leads us from this world to another.
                    """,
                imageUri = "http://lorempixel.com/400/400/"
        ) }

        infoAdapter.notifyDataSetChanged()
    }
}