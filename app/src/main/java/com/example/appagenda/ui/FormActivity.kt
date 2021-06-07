package com.example.appagenda.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.appagenda.R
import com.example.appagenda.config.Constants
import com.example.appagenda.databinding.ActivityFormBinding
import com.example.appagenda.databinding.ActivityMainBinding
import com.example.appagenda.viewmodel.FormViewModel

class FormActivity : AppCompatActivity(), DeleteDialogFragment.DeleteListener {
    lateinit var binding: ActivityFormBinding
    lateinit var viewModel: FormViewModel
    lateinit var deleteDialogFragment: DeleteDialogFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        deleteDialogFragment = DeleteDialogFragment(this)
        viewModel = ViewModelProvider(this).get()
        viewModel.op = intent.getStringExtra(Constants.OP_KEY)!!
        binding.formViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.succes.observe(this, Observer {
            if (it) {
                showMessage(getString(R.string.succes))
                intent = Intent(this, ScheduleActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            } else {
                showMessage(getString(R.string.error_save))
            }
        })

        if (viewModel.op.equals(Constants.OP_UPDATE)) {
            viewModel.id.value = intent.getLongExtra(Constants.ID_PERSONAL_KEY,0)
            viewModel.fillFields()
            binding.llEditLayout.visibility = View.VISIBLE
            binding.btnSave.visibility = View.GONE
            supportActionBar?.title = getString(R.string.action_bar_update)
        } else {
            binding.llEditLayout.visibility = View.GONE
            binding.btnSave.visibility = View.VISIBLE
            supportActionBar?.title = getString(R.string.action_bar_create)
        }
        binding.btnDlt.setOnClickListener {
            showDialog()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun showDialog() {
        deleteDialogFragment.show(supportFragmentManager, "tag dialog delete")
    }

    private fun showMessage(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun deletePersonDialog() {
        viewModel.deletePerson()
    }
}