package com.goddoro.junction.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.goddoro.junction.databinding.ItemCommentBinding
import com.goddoro.junction.network.model.Comment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class CommentBindingAdapter : RecyclerView.Adapter<CommentBindingAdapter.CommentViewHolder>(){


    private val onClickComment: PublishSubject<Comment> = PublishSubject.create()
    val clickComment: Observable<Comment> = onClickComment

    private val diff = object : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diff)

    fun submitItems(items: List<Comment>?) {
        differ.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommentBinding.inflate(inflater, parent, false)
        return CommentViewHolder(binding)

    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }


    inner class CommentViewHolder ( val binding : ItemCommentBinding ) : RecyclerView.ViewHolder(binding.root) {


        init {
//            binding.root.setOnDebounceClickListener {
//                onClickMatch.onNext(Pair(differ.currentList[layoutPosition],layoutPosition))
//            }

        }

        fun bind ( item : Comment) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()



        }

    }

}

@BindingAdapter("app:recyclerview_comment")
fun RecyclerView.setCommentList(items : List<Comment>?){
    (adapter as? CommentBindingAdapter)?.run {
        this.submitItems(items)
    }
}