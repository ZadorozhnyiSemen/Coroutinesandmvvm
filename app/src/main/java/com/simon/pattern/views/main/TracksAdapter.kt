package com.simon.pattern.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.simon.pattern.R
import com.simon.pattern.domain.Track
import kotlinx.android.synthetic.main.track_item.view.*

class TracksAdapter : RecyclerView.Adapter<TracksAdapter.TrackHolder>() {
    var tracks: List<Track> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.track_item, parent, false)
        return TrackHolder(view)
    }

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        val track = tracks[position]

        holder.apply {
            trackName.text = track.name
            albumName.text = track.album.name
            trackUrl.text = track.uri
        }
    }

    inner class TrackHolder(
        itemView: View,
        val trackName: TextView = itemView.track_name,
        val albumName: TextView = itemView.track_album,
        val trackUrl: TextView = itemView.track_url
    ) : RecyclerView.ViewHolder(itemView)
}