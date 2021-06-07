package com.example.appagenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appagenda.R
import com.example.appagenda.adapter.ScheduleAdapter
import com.example.appagenda.config.Constants
import com.example.appagenda.databinding.ActivityMainBinding
import com.example.appagenda.viewmodel.ScheduleViewModel

class ScheduleActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ScheduleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get()
        binding.lifecycleOwner = this
        binding.scheduleViewModel = viewModel
        viewModel.init()

        binding.rvPersons.apply {
            layoutManager = LinearLayoutManager(applicationContext)
        }

        viewModel.personList.observe(this, Observer {

            binding.rvPersons.adapter = ScheduleAdapter(it)
            if (it.isEmpty()) {
                binding.tvEmptyList.visibility = View.VISIBLE
            } else {
                binding.tvEmptyList.visibility = View.GONE
            }
        })

        binding.fabAddPerson.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            intent.putExtra(Constants.OP_KEY, Constants.OP_INSERT)
            startActivity(intent)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    viewModel.searchPerson()
                } else {
                    viewModel.init()
                }
            }
        })

        supportActionBar?.title = getString(R.string.action_bar_schedule)

    }

}