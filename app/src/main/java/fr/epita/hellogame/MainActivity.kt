package fr.epita.hellogame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    val baseUrl = "https://androidlessonsapi.herokuapp.com/api/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list_game = arrayListOf<Game>()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        val services: WSInterface = retrofit.create(WSInterface::class.java)

        val callback_game_list : Callback<List<Game>> = object : Callback<List<Game>> {

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                Log.d("TAG", "Webservice call failed" + t.toString())
            }

            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                if (response.code() == 200) {
                    val data : List<Game> = response.body()!!
                    val r = Random()
                    val i = r.nextInt(data.size - 0)
                    list_game.add(data.get(i))
                    btn_1.setText(data.get(i).name)

                    val j = r.nextInt(data.size - 0)
                    list_game.add(data.get(j))
                    btn_2.setText(data.get(j).name)

                    val k = r.nextInt(data.size - 0)
                    list_game.add(data.get(k))
                    btn_3.setText(data.get(k).name)

                    val t = r.nextInt(data.size - 0)
                    list_game.add(data.get(t))
                    btn_4.setText(data.get(t).name)
                }
            }
        }

        services.getAllGames().enqueue(callback_game_list)

    }
}
