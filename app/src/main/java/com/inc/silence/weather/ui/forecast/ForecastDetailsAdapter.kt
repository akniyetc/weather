package com.inc.silence.weather.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.inc.silence.weather.R
import com.inc.silence.weather.extension.hourWithAMorPM
import com.inc.silence.weather.extension.inflate
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
        fun bind(forecast: ForecastView) {
            itemView.tv_forecast_detail_time.text = forecast.date.hourWithAMorPM()
            itemView.tv_forecast_detail_degree.text = forecast.temperature.toString()
        }
    }
}