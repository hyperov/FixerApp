package com.fixer.app.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fixer.app.R
import com.fixer.app.viewmodel.MainViewModel
import com.fixer.app.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : DaggerFragment() {

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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        getCurrenciesList()
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.currenciesLiveData.observe(viewLifecycleOwner, Observer { list ->

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