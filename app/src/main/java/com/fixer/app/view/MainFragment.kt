package com.fixer.app.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.fixer.app.R
import com.fixer.app.viewmodel.MainViewModel
import com.fixer.app.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    private lateinit var currenciesAdapter: CurrenciesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupAdapter()
        getCurrenciesList()
        subscribeToViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory)
            .get(MainViewModel::class.java)
    }

    private fun setupAdapter() {
        currenciesAdapter =
            CurrenciesAdapter(arrayListOf()) { currency: Pair<String, Double> ->
                viewModel.selectedCurrency.postValue(currency)
                findNavController().navigate(R.id.detailFragment)
            }
        rvCurrencies.adapter = currenciesAdapter
        rvCurrencies.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )
        )
    }

    private fun subscribeToViewModel() {
        viewModel.currenciesLiveData.observe(viewLifecycleOwner, Observer { list ->
            currenciesAdapter.add(list)
        })

        viewModel.progressLiveData.observe(viewLifecycleOwner, Observer { isProgress ->
            if (isProgress)
                progress.visibility = View.VISIBLE
            else
                progress.visibility = View.GONE
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            Snackbar.make(this.requireView(), error!!, Snackbar.LENGTH_LONG).show()
            Log.e("subscribeToViewModel: ", error)
            progress.visibility = View.GONE
        })
    }

    private fun getCurrenciesList() {
        viewModel.getCurrencies(key)
    }

    companion object {
        const val key = "59daaf72b44793b5580dbd6a81124efc"
    }

}