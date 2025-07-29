package com.example.presentation.main.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.courses.model.Course
import com.example.presentation.R
import com.example.presentation.core.extensions.AdapterDelegate
import com.example.presentation.databinding.ItemCourseBinding

class CourseAdapterDelegate(
    private val onClick: (Course) -> Unit
) : AdapterDelegate<Course> {

    override fun isForViewType(item: Course) = true

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = ItemCourseBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: Course) {
        (holder as CourseViewHolder).bind(item)
    }

    class CourseViewHolder(
        private val binding: ItemCourseBinding,
        private val onClick: (Course) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.tvCourseTitle.text = course.title
            binding.tvCourseDescription.text = course.description
            binding.tvPrice.text = itemView.context.getString(R.string.price, course.price)
            binding.tvRating.text = course.rate
            binding.tvDate.text = course.startDate
            binding.ivBookmark.isSelected = course.hasLike


            itemView.setOnClickListener { onClick(course) }
        }
    }
}