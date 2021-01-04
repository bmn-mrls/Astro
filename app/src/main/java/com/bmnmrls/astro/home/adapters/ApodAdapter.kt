package com.bmnmrls.astro.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bmnmrls.astro.R
import com.bmnmrls.astro.databinding.ApodItemLayoutBinding
import com.bmnmrls.domain.models.Apod
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ApodAdapter @Inject constructor() : RecyclerView.Adapter<ApodAdapter.ViewHolder>() {

    var listener: ((Apod) -> Unit)? = null
    private val apods: MutableList<Apod> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.apod_item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(apods[position], listener)

    override fun getItemCount(): Int = apods.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: ApodItemLayoutBinding = ApodItemLayoutBinding.bind(view)

        fun bind(item: Apod, listener: ((Apod) -> Unit)?) = with(binding) {
            this.apod = item
            listener?.let { this.listener = View.OnClickListener { _ -> it(item) } }
            executePendingBindings()
        }

    }

}