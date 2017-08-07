package com.app.petstore.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.app.petstore.views.adapters.PetAdapter.PetHolder
import com.app.petstore.R
import com.app.petstore.models.Pet
import com.app.petstore.views.OnPetClickListener
import com.app.petstore.views.PetDetailActivity
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class PetAdapter(var petList: List<Pet>, val listener: OnPetClickListener) : RecyclerView.Adapter<PetHolder>() {

    override fun getItemCount(): Int {
        return petList.size
    }

    override fun onBindViewHolder(holder: PetHolder, position: Int) {
        val pet = petList[position]
        holder.bindPet(pet, listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetHolder {
        val inflatedView = LayoutInflater.from(parent.context)
                .inflate(R.layout.pet_item_view, parent, false)
        return PetHolder(inflatedView)
    }

    class PetHolder(val v: View): RecyclerView.ViewHolder(v), View.OnClickListener {
        val petName = v.find<TextView>(R.id.pet_name)

        fun bindPet(pet: Pet, listener: OnPetClickListener) {
            petName.text = pet.name
            v.setOnClickListener { listener.onPetClick(pet) }
        }

        override fun onClick(v: View) {
            v.context.startActivity<PetDetailActivity>()
        }
    }
}