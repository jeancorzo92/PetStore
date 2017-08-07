package com.app.petstore.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.app.petstore.R
import com.app.petstore.models.Pet
import org.jetbrains.anko.find


class PetDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.pet_detail_fragment, container, false)
        val petData = activity.intent.getParcelableExtra<Pet>("pet")
        displayPetDataInView(petData, view)
        return view
    }

    private fun displayPetDataInView(petData: Pet, view: View) {
        view.find<TextView>(R.id.pet_name).text = petData.name
        view.find<TextView>(R.id.pet_category).text = petData.category.name
        view.find<TextView>(R.id.pet_id).text = petData.id
        view.find<TextView>(R.id.pet_tags).text = petData.tags.map { it -> it.name }.joinToString(", ")
        view.find<TextView>(R.id.pet_status).text = petData.status
    }
}