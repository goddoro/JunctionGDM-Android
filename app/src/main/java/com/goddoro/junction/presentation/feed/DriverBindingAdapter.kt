package com.goddoro.junction.presentation.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.goddoro.junction.databinding.ItemDriverBinding
import com.goddoro.junction.network.model.Driver
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.koin.core.KoinComponent



/**
 * created By DORO 5/22/21
 */


class DriverBindingAdapter: RecyclerView.Adapter<DriverBindingAdapter.DriverViewHolder>() {

    private val TAG = DriverBindingAdapter::class.java.simpleName


    private val onClick: PublishSubject<Driver> = PublishSubject.create()
    val clickEvent: Observable<Driver> = onClick

    private val diff = object : DiffUtil.ItemCallback<Driver>() {
        override fun areItemsTheSame(oldItem: Driver, newItem: Driver): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Driver, newItem: Driver): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diff)

    fun submitItems(items: List<Driver>?) {
        differ.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDriverBinding.inflate(inflater, parent, false)

        return DriverViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) = holder.bind(differ.currentList[position ])

    inner class DriverViewHolder(private val binding: ItemDriverBinding) : RecyclerView.ViewHolder(binding.root),
        KoinComponent {
        init {

            binding.root.setOnClickListener {
                onClick.onNext(differ.currentList[layoutPosition])
            }




        }

        fun bind(item: Driver) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()




        }
    }

}

@BindingAdapter("app:recyclerview_driver_list")
fun RecyclerView.setDriverList(items: List<Driver>?) {
    (adapter as? DriverBindingAdapter)?.run {
        this.submitItems(items)
    }
}
