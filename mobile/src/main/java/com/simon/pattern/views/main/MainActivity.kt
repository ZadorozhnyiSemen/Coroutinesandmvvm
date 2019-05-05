package com.simon.pattern.views.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.simon.pattern.R
import com.simon.pattern.base.BaseActivity
import com.simon.pattern.utils.viewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel
    override val layoutId: Int? = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)
        subscribeToData()
        viewModel.checkIfCoroutinesWorking()
    }

    private fun subscribeToData() {
        viewModel.textWithStatus.observe(this,
            Observer<String> { t -> t?.let { work_status.text = it } })
    }
}
