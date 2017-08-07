package com.app.petstore.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.app.petstore.R
import com.app.petstore.models.Category
import com.app.petstore.models.Pet
import com.app.petstore.models.Tag
import com.app.petstore.service.Clients
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomInfoFragment : Fragment() {

    lateinit var fragmentView: View
    lateinit var addPetCall: Call<Pet>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        fragmentView = inflater.inflate(R.layout.custom_info_fragment, container, false)

        fragmentView.find<Button>(R.id.send_pet_button).setOnClickListener {
            val pet = getPetFromUserInput()
            addPetCall = Clients.petStoreClient.postNewPet(pet)
            postPetToApi()
        }
        return fragmentView
    }

    private fun postPetToApi() {
        addPetCall.clone().enqueue(object : Callback<Pet> {
            override fun onResponse(call: Call<Pet>?, response: Response<Pet>) {
                if (response.isSuccessful) {
                    activity.toast("Pet saved successfully. Congrats :D")
                }
            }

            override fun onFailure(call: Call<Pet>?, t: Throwable?) {
                activity.toast("Couldn't save your pet, sorry :'(")
            }
        })
    }

    private fun getPetFromUserInput(): Pet {
        val petName = fragmentView.find<EditText>(R.id.name_input).text.toString()
        val petId = fragmentView.find<EditText>(R.id.id_input).text.toString()
        val petCategoryName = fragmentView.find<EditText>(R.id.category_input).text.toString()
        val petStatus = fragmentView.find<EditText>(R.id.status_input).text.toString()
        val petTags = fragmentView.find<EditText>(R.id.tags_input).text.toString()
        val petPhotosUrls = fragmentView.find<EditText>(R.id.photo_url_input).text.toString()

        val petCategory = Category(getRandomLong(), petCategoryName)
        val petTagsList = mapToObjectListFromString(petTags) { Tag(getRandomLong(), it) }
        val petPhotoList = mapToObjectListFromString(petPhotosUrls) { it }

        return Pet(petId, petCategory, petName, petPhotoList, petTagsList, petStatus)
    }

    private fun <T> mapToObjectListFromString(input: String, map: (String) -> T): List<T> {
        val objectList = mutableListOf<T>()
        input.split(",").map { it -> it.trim() }.forEach {
            objectList.add(map(it))
        }
        return objectList
    }

    private fun getRandomLong(): Long {
        return Math.random().toLong()
    }
}