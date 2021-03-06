package by.paulharokh.it

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    private var services = mutableListOf<AdaptedService>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val services: Bundle = intent.extras!!
        val receivedServiceS = services["ALL SERVICES"]

        val servicesArr = receivedServiceS.toString().toCharArray()

        for (i in servicesArr.indices) {
            val bufferAdaptedService = AdaptedService(servicesArr[i].toString(), true)
            this.services.add(bufferAdaptedService)
        }

        rv_services_pref_id.adapter = ServiceAdapter(this.services, this)
        rv_services_pref_id.layoutManager = LinearLayoutManager(this)
    }


    fun savePref(view: android.view.View) {

        var bannedS = ""

        for (i in services.indices) {
            if (!services[i].isChecked) {
                bannedS += services[i].name
            }
        }

        val intent = Intent(this@FilterActivity, MapsActivity::class.java)
        intent.putExtra("PREF S", bannedS)
        startActivity(intent)
        onBackPressed()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun isBanned(adapterPosition: Int) {
        services[adapterPosition].isChecked = !services[adapterPosition].isChecked
        rv_services_pref_id.adapter?.notifyDataSetChanged()
    }

}