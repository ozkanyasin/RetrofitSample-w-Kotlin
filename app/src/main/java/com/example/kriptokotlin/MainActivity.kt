package com.example.kriptokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kriptokotlin.adapter.RecyclerAdapter
import com.example.kriptokotlin.databinding.ActivityMainBinding
import com.example.kriptokotlin.model.CryptoModel
import com.example.kriptokotlin.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerAdapter.Listener {

    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModels : ArrayList<CryptoModel>? = null
    private lateinit var binding: ActivityMainBinding
    private var recyclerAdapter : RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadData()


    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val  call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {

                        cryptoModels = ArrayList(it)

                        cryptoModels?.let {
                            recyclerAdapter = RecyclerAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter = recyclerAdapter
                        }

                       /* for (cryptoModel : CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }*/

                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this,"Clicked: ${cryptoModel.currency}",Toast.LENGTH_SHORT)
    }


}