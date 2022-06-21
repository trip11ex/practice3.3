package com.example.practice33

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice33.databinding.ActivityMainBinding

import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var adapter : PersonAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PersonAdapter(getJSONArrayList())
        init()


        //___________________
//        val workerList: ArrayList<Person> = ArrayList()
//
//        try {
//            val obj = JSONObject(getJSONFromAssets()!!)
//            val workersArray = obj.getJSONArray("workers")
//            for (i in 0 until workersArray.length()){
//                val worker = workersArray.getJSONObject(i)
//                workerList.add(Person(worker.getString("name"), worker.getString("sex"), worker.getString("phoneNumber")))
//            }
//        }
//        catch (e: JSONException){
//            e.printStackTrace()
//        }

        //___________________
    }
    //___________________

    private fun getJSONArrayList(): ArrayList<Person>{
        val workerList: ArrayList<Person> = ArrayList()

        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val workersArray = obj.getJSONArray("workers")
            for (i in 0 until workersArray.length()){
                val worker = workersArray.getJSONObject(i)
                workerList.add(Person(worker.getString("phoneNumber"), worker.getString("name"), worker.getString("sex")))
            }
        }
        catch (e: JSONException){
            e.printStackTrace()
        }
        return workerList
    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("workers.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    //___________________
    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter

            // На скорую руку отсортировал
            button2.setOnClickListener{
                val list = adapter.personList
                val sortedList : ArrayList<Person> = ArrayList()
                for(i in list.indices)
                    if(list[i].sex == "female")
                        sortedList.add(list[i])
                for(i in list.indices)
                    if(list[i].sex == "male")
                        sortedList.add(list[i])
                for(i in list.indices)
                    if(list[i].sex == "unknown")
                        sortedList.add(list[i])
                adapter.updateAdapter(sortedList)
            }

            button1.setOnClickListener{
                val list = adapter.personList

                val ser : MutableMap<Int, String> = mutableMapOf<Int, String>()
                val sortedList : ArrayList<Person> = ArrayList()

                for(i in list.indices)
                    ser.put(i, list[i].name)

                val sortedSer = ser.toList().sortedBy { (_, value) -> value}.toMap()

                for (i in sortedSer.keys)
                    sortedList.add(list[i])

                adapter.updateAdapter(sortedList)
            }
        }
    }



//    fun  someF(){
//        var jsonArray = JSONArray(sampleJson)
//        for (jsonIndex in 0..(jsonArray.length() - 1)) {
//            Log.d("JSON", jsonArray.getJSONObject(jsonIndex).getString("title"))
//        }
//
//    }
}