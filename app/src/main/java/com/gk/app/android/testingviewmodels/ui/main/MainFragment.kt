package com.gk.app.android.testingviewmodels.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.viewModels
import com.gk.app.android.testingviewmodels.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment(
    private var mainViewModel: MainViewModel? = null
) : Fragment() {

    class Factory(
        private val viewModel: MainViewModel? = null
    ) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return if (className == MainFragment::class.java.name) {
                MainFragment(viewModel)
            } else {
                super.instantiate(classLoader, className)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.v(javaClass.simpleName, "onCreateView()")
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onDestroy() {
        Log.v(javaClass.simpleName, "onDestroy()")
        super.onDestroy()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.v(javaClass.simpleName, "onActivityCreated() savedInstanceState=$savedInstanceState")
        super.onActivityCreated(savedInstanceState)

        // If we do not have a constructor injected view model, obtain it from property delegate
        if (mainViewModel == null) {
            val vm: MainViewModelImpl by viewModels()
            mainViewModel = vm
        }

        Log.i(javaClass.simpleName, "viewModel=$mainViewModel")

        mainBtn.setOnClickListener { mainViewModel?.onButtonClicked("someId") }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(javaClass.simpleName, "onSaveInstanceState()")

        //TODO use view model to save instance state
        //outState.putCharSequence("test", "Some saved data")

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d(javaClass.simpleName, "onViewStateRestored() savedInstanceState=$savedInstanceState")

        super.onViewStateRestored(savedInstanceState)
    }
}