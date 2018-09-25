package com.inc.silence.weather.ui.forecast

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

    internal var clickListener: (List<ForecastView>) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.item_forecast))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecasts: List<ForecastView>, clickListener: (List<ForecastView>) -> Unit) {
            itemView.tv_week_day.text = forecasts[0].date.toDayOfWeek()
            itemView.tv_temp.text = forecasts.degreeByHour("15")
            itemView.tv_today.visible(forecasts[0].date.toDayOfWeek() == today())
        }

        private fun today(): String {
            val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
            val d = Date()
            return sdf.format(d)
        }
    }
}