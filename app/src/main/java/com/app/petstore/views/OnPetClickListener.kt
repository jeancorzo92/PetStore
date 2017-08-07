package com.app.petstore.views

import android.content.Context
import com.app.petstore.models.Pet
import org.jetbrains.anko.startActivity

class OnPetClickListener(val context: Context) {
    fun onPetClick(pet: Pet) {
        context.startActivity<PetDetailActivity>("pet" to pet)
    }
}