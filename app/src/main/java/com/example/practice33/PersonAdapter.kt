package com.example.practice33

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice33.databinding.PersonItemBinding

class PersonAdapter(listArray:ArrayList<Person>) : RecyclerView.Adapter<PersonAdapter.PersonHolder>() {
    val personList = listArray

    class PersonHolder(item : View) : RecyclerView.ViewHolder(item) {
        val binding = PersonItemBinding.bind(item)
        fun bind(person : Person) = with(binding){
            when(person.sex){
                "female" -> sexImg.setImageResource(R.drawable.female)
                "male" -> sexImg.setImageResource(R.drawable.male)
                "unknown" -> sexImg.setImageResource(R.drawable.unknown)
            }
            name.text = person.name
            phoneNumber.text = person.phoneNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
    val view =  LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return PersonHolder(view)
    }

    override fun onBindViewHolder(holder: PersonHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    fun updateAdapter(listArray : ArrayList<Person>)
    {
        personList.clear()
        personList.addAll(listArray)
        notifyDataSetChanged()
    }
}