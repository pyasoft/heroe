package com.aac.superheroe.ui.heroe

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.aac.superheroe.databinding.ItemSuperheroeListBinding
import com.aac.superheroe.domain.model.HeroeComun
import com.squareup.picasso.Picasso

class HeroeListAdapter internal constructor (val adapterOnClick: AdapterOnClick) : RecyclerView.Adapter<HeroeListAdapter.HeroeViewHolder>() {

    private var heroes = emptyList<HeroeComun>()

    inner class HeroeViewHolder(private val binding:ItemSuperheroeListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(superhero: HeroeComun) {
            binding.tvSuperhero.text = superhero.name
            binding.tvRealName.text = superhero.fullName
            binding.tvPublisher.text = superhero.publisher
            binding.ivAvatar.loadUrl(superhero.xs)
            binding.root.setOnClickListener { adapterOnClick.onClick(superhero) }
        }

        private fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url).into(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroeViewHolder {
        val binding = ItemSuperheroeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroeViewHolder, position: Int) {
        val current = heroes[position]
        holder.bind(current)
    }

    override fun getItemCount() = heroes.size

    internal fun setHeroes(heroes: List<HeroeComun>) {
        this.heroes = heroes
        notifyDataSetChanged()
    }

}