package com.gk.app.android.testingviewmodels.ui.items

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gk.app.android.testingviewmodels.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_items.*

@AndroidEntryPoint
class ItemListFragment : Fragment() {

    private var itemListViewModel: ItemListViewModel? = null
    private var selectedViewPositionArg: Int? = null

    /**
     * Factory for creating this fragment with an injected ViewModel (otherwise it would be
     * automatically injected by Hilt)
     */
    class Factory(
        private val viewModel: ItemListViewModel? = null
    ) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            if (className == ItemListFragment::class.java.name) {
                return ItemListFragment().apply {
                    viewModel?.let { this.itemListViewModel = it }
                }
            }
            return super.instantiate(classLoader, className)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.v(javaClass.simpleName, "onCreateView() arguments=$arguments" +
                " savedInstanceState=$savedInstanceState")
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onDestroy() {
        Log.v(javaClass.simpleName, "onDestroy()")
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.v(javaClass.simpleName, "onCreateView() arguments=$arguments" +
                " savedInstanceState=$savedInstanceState")
        super.onActivityCreated(savedInstanceState)

        selectedViewPositionArg = arguments?.getInt("autoSelectPosition")

        // If we do not have a constructor injected view model, obtain it from property delegate
        if (itemListViewModel == null) {
            // TODO for now we're using the activityViewModels() to keep this fragment's view model
            // alive across the activity's configuration changes, as it does not need savedStateHandle
            // to be updated
            val vm: ItemListViewModelImpl by activityViewModels()
//            val vm: ItemListViewModelImpl by viewModels()
            itemListViewModel = vm
        }

        Log.i(javaClass.simpleName, "viewModel=$itemListViewModel")

        itemListViewModel?.let { viewModel ->
            val adapter = ItemRecyclerAdapter()
            adapter.setOnItemClick(viewModel::onItemClicked)
            itemsRecycler.layoutManager = LinearLayoutManager(itemsRecycler.context)
            itemsRecycler.adapter = adapter

            viewModel.bindView(
                viewOwner = viewLifecycleOwner
            ) { items, selectedPosition ->
                selectedViewPositionArg?.let {
                    if (it >= 0 && it < items.size) {
                        adapter.updateItems(items, it)
                        viewModel.onItemClicked(it)
                        selectedViewPositionArg = null
                    }
                } ?: adapter.updateItems(items, selectedPosition)
            }
        }
    }

}