package com.inc.silence.weather.ui.forecast

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.inc.silence.weather.R
import com.inc.silence.weather.extension.*
import com.inc.silence.weather.presentation.view.ForecastView
import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    internal var collection: List<List<ForecastView>> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.item_forecast))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val forecastDetailsAdapter = ForecastDetailsAdapter()
        private val IMG_URL = "https://api.openweathermap.org/img/w/"

        fun bind(forecasts: List<ForecastView>) {

            initForecastDetailsView(forecasts)

            itemView.tvWeekDay.text = forecasts.first().date.toDayOfWeek()
            itemView.tvTemp.text = forecasts.weatherByHour().temperature.toDegree()
            itemView.imgWeatherIcon.loadFromUrl(IMG_URL + forecasts.weatherByHour().weather.icon)

            itemView.tvToday.visible(forecasts.first().date.toDayOfWeek() == today())
            itemView.setOnClickListener {
                itemView.detailsContent.visible(itemView.detailsContent.visibility == View.GONE)
            }
        }

        private fun initForecastDetailsView(forecasts: List<ForecastView>) {
            itemView.rvForecastDetail.layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.rvForecastDetail.adapter = forecastDetailsAdapter
            itemView.rvForecastDetail.dispatchNestedScrolling()
            forecastDetailsAdapter.collection = forecasts
        }

        private fun today(): String {
            val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
            val d = Date()
            return sdf.format(d)
        }
    }
}