package com.inc.silence.weather.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.inc.silence.weather.R
import com.inc.silence.weather.extension.*
import com.inc.silence.weather.presentation.view.ForecastView
import kotlinx.android.synthetic.main.item_forecast_details.view.*
import kotlin.properties.Delegates

class ForecastDetailsAdapter : RecyclerView.Adapter<ForecastDetailsAdapter.ViewHolder>() {

    internal var collection: List<ForecastView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.item_forecast_details))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val IMG_URL = "https://api.openweathermap.org/img/w/"
        fun bind(forecast: ForecastView) {
            itemView.tvForecastDetailHour.text = forecast.date.hour(false)
            itemView.tvForecastDetailAmPm.text = forecast.date.amOrPm()
            itemView.imgWeatherDetailsIcon.loadFromUrl(IMG_URL + forecast.weather.icon)
            itemView.tvForecastDetailDegree.text = forecast.temperature.toDegree()
        }
    }
}