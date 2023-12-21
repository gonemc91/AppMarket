package com.example.catalog.presentation.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.elveum.elementadapter.ElementListAdapter
import com.example.catalog.R
import com.example.catalog.databinding.FragmetFilterBinding
import com.example.catalog.domain.entites.ProductFilter
import com.example.presentation.BaseScreen
import com.example.presentation.args
import com.example.presentation.viewBinding
import com.example.presentation.viewModelCreator
import com.example.presentation.views.observe
import com.example.presentation.views.setupSimpleList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CatalogFilterFragment : Fragment(R.layout.fragmet_filter) {


    class Screen(
        val filter: ProductFilter,
    ) : BaseScreen

    private val binding by viewBinding<FragmetFilterBinding>()

    @Inject
    lateinit var factory: CatalogFilterViewModel.Factory
    private val viewModel by viewModelCreator { factory.create(args()) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = createFilterAdapter(
            onApply = {
                viewModel.applyFilter()
            }
        )
        with(binding){
            filterRecyclerView.setupSimpleList()
            filterRecyclerView.adapter = adapter
            observeState(adapter)
            setupListeners()
        }

        viewModel.initBackListener(viewLifecycleOwner.lifecycleScope)

    }

    private fun FragmetFilterBinding.observeState(adapter: ElementListAdapter<FilterItem>){
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue){filterItems ->
            adapter.submitList(filterItems)
        }
    }

    private fun FragmetFilterBinding.setupListeners(){
        root.setTryAgainListener { viewModel.load() }
    }
}


