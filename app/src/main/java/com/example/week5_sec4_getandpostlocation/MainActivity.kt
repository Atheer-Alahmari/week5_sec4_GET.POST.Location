package com.example.week5_sec4_getandpostlocation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val apiInterface by lazy { APIClient().getClient()?.create(APIInterface::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_save.setOnClickListener {
            if (name_ED.text.isNotEmpty() && locatoin_ED.text.isNotEmpty()) {
          var input = Name_LocationItem(name_ED.text.toString() , locatoin_ED.text.toString())
                postNameLocation(input)
                name_ED.text.clear()
                locatoin_ED.text.clear()

            } else {
                Toast.makeText(this, " is empty", Toast.LENGTH_LONG).show()
            }
        }
        btn_get.setOnClickListener {

            getLocation()
        }
    }

    private fun getLocation() {

        apiInterface?.getLocation()?.enqueue(object : Callback<ArrayList<Name_LocationItem?>?> {
            override fun onResponse(
                call: Call<ArrayList<Name_LocationItem?>?>,
                response: Response<ArrayList<Name_LocationItem?>?>
            ) {

                var displayResponse = ""

                val datumList = response.body()!!


                    for (datum in datumList!!) {
                        if (nameED_toGetL.text.toString().lowercase() == datum?.name?.lowercase()) {
                            displayResponse = "${datum?.location} \n"
                        }

                    }

                location_TV.text = displayResponse
                //  textView2.text = displayResponse

            }

            override fun onFailure(call: Call<ArrayList<Name_LocationItem?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Unable to get data", Toast.LENGTH_LONG).show()
            }
        })

    }

    //--------------------------------------add-----------------------
    private fun postNameLocation(input:Name_LocationItem) {

        apiInterface?.postNameLocation(input)?.enqueue(object :
            Callback<Name_LocationItem> {
            override fun onResponse(
                call: Call<Name_LocationItem>,
                response: Response<Name_LocationItem>
            ) {
                Toast.makeText(applicationContext, "adding Sucess!", Toast.LENGTH_SHORT).show();
            }

            override fun onFailure(call: Call<Name_LocationItem>, t: Throwable) {
                Toast.makeText(applicationContext, "Unable to get data", Toast.LENGTH_LONG).show()
            }
        })
    }
}