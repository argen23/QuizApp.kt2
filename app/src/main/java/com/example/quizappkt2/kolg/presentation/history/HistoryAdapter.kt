package com.example.quizappkt2.kolg.presentation.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappkt2.R
import com.example.quizappkt2.databinding.ItemHistoryBinding
import com.example.quizappkt2.kolg.domain.model.History
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter(private val list: List<History>, private val listener:
    (history: History) -> Unit) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.ivMenu.setOnClickListener {
            listener(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    class HistoryViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView) {

        val binding = ItemHistoryBinding.bind(itemView)

        fun bind(history: History) {

            val category = history.category
            val diff = history.difficulty
            val corrAns = history.correctAnswers.toString()

            binding.tvCategory.text = itemView.resources.getString(R.string.category_, category)
            binding.tvCa.text =
                itemView.resources.getString(R.string.correct_answers_, corrAns, history.amount)
            binding.tvDif.text = itemView.resources.getString(R.string.difficulty_, diff)
            binding.tvDate.text = convertLongToTime(history.date)

        }

        @SuppressLint("SimpleDateFormat")
        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat(itemView.context.getString(R.string.date_format))
            return format.format(date)
        }

    }

}