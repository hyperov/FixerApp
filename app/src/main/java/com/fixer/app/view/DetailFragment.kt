package com.fixer.app.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fixer.app.R
import com.fixer.app.extensions.roundToTwoDecimalNumbers
import com.fixer.app.viewmodel.MainViewModel
import com.fixer.app.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_currency.*
import javax.inject.Inject


class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MainViewModel
    private var baseRate: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = setupViewModel()
        subscribeToViewModel()
        addTextListeners()
    }

    private fun addTextListeners() {
        etBase.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (editable.isNullOrEmpty()) tvRate.text = baseRate.toString()
                else {
                    val result = editable.toString().toDouble() * baseRate
                    tvRate.text = result.roundToTwoDecimalNumbers().toString()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun subscribeToViewModel() {
        viewModel.selectedCurrency.observe(viewLifecycleOwner, Observer { currency ->
            tvCurrency.text = currency.first
            baseRate = currency.second.roundToTwoDecimalNumbers()
            tvRate.text = baseRate.toString()
        })
    }

    private fun setupViewModel() =
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(MainViewModel::class.java)


}