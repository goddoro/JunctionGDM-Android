package com.goddoro.junction.presentation.indriving

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goddoro.junction.databinding.ItemDriverBinding
import com.goddoro.junction.databinding.ItemDrivingTextBinding
import com.goddoro.junction.network.model.InDrivingText
import com.goddoro.junction.presentation.feed.DriverBindingAdapter
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.koin.core.KoinComponent
import java.util.*


class InDrivingTextAdapter ( val context : Context): RecyclerView.Adapter<InDrivingTextAdapter.InDrivingTextHolder>() {

    private val TAG = DriverBindingAdapter::class.java.simpleName

    private lateinit var textToSpeech : TextToSpeech


    private val onClick: PublishSubject<InDrivingText> = PublishSubject.create()
    val clickEvent: Observable<InDrivingText> = onClick

    private val diff = object : DiffUtil.ItemCallback<InDrivingText>() {
        override fun areItemsTheSame(oldItem: InDrivingText, newItem: InDrivingText): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: InDrivingText, newItem: InDrivingText): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diff)

    fun submitItems(items: List<InDrivingText>?) {
        differ.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InDrivingTextHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDrivingTextBinding.inflate(inflater, parent, false)

        return InDrivingTextHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: InDrivingTextHolder, position: Int) = holder.bind(differ.currentList[position])

    inner class InDrivingTextHolder(private val binding: ItemDrivingTextBinding) : RecyclerView.ViewHolder(binding.root),
        KoinComponent {
        init {

            binding.root.setOnClickListener {
                onClick.onNext(differ.currentList[layoutPosition])
            }




        }

        fun bind(item: InDrivingText) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()

            binding.txtDescription.apply {

                text = ""
                setCharacterDelay(100)
                animateText(item.description)



            }


            textToSpeech = TextToSpeech(context) {
                if ( it != TextToSpeech.ERROR) {
                    textToSpeech.language =(Locale.KOREAN)

                }
                textToSpeech.speak(item.description,TextToSpeech.QUEUE_FLUSH,null,null)

            }

        }
    }

}

@BindingAdapter("app:recyclerview_in_driving_text_list")
fun RecyclerView.setInDrivingTextList(items: List<InDrivingText>?) {
    (adapter as? InDrivingTextAdapter)?.run {
        this.submitItems(items)
    }
}
