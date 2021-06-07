package com.example.appagenda.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appagenda.R
import com.example.appagenda.config.Constants
import com.example.appagenda.databinding.ScheduleItemBinding
import com.example.appagenda.models.Person
import com.example.appagenda.ui.FormActivity

class ScheduleAdapter(private val personlist: List<Person>?): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_item,parent,false)
        return ScheduleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item = personlist?.get(position)
        holder.bind(item!!)
    }

    override fun getItemCount(): Int {
        return personlist?.size!!
    }
    class ScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var binding = ScheduleItemBinding.bind(itemView)
        var context = itemView.context

        fun bind(p: Person){
            binding.tvName.text = "${p.name} ${p.surname} "
            binding.tvEmail.text = p.email
            binding.tvTelephone.text = p.telephone

            binding.root.setOnClickListener {
                val intent =  Intent(context,FormActivity::class.java)
                intent.putExtra(Constants.OP_KEY, Constants.OP_UPDATE)
                intent.putExtra(Constants.ID_PERSONAL_KEY,p.id)
                context.startActivity(intent)
            }
        }
    }
}