package com.app.petstore.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.app.petstore.views.adapters.PetAdapter
import com.app.petstore.R
import com.app.petstore.models.Pet
import com.app.petstore.service.Clients
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val petListCall = Clients.petStoreClient.getPetListByStatus("available")
    val petRecyclerView by lazy { find<RecyclerView>(R.id.pet_recycler_view) }
    val linearLayoutManager by lazy { LinearLayoutManager(this@MainActivity) }
    val recyclerAdapter = PetAdapter(emptyList<Pet>(), OnPetClickListener(this@MainActivity))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        petRecyclerView.layoutManager = linearLayoutManager
        petRecyclerView.adapter = recyclerAdapter
        requestAndDisplayPets()
    }

    override fun onResume() {
        super.onResume()
        requestAndDisplayPets()
    }

    private fun requestAndDisplayPets() {
        petListCall.clone().enqueue(object : Callback<List<Pet>> {
            override fun onResponse(call: Call<List<Pet>>?, response: Response<List<Pet>>) {
                if (response.code() == 200) {
                    recyclerAdapter.petList = response.body()!!
                    petRecyclerView.adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<Pet>>?, t: Throwable) {
                toast("Something went wrong :(")
                t.printStackTrace()
            }
        })
    }

}
