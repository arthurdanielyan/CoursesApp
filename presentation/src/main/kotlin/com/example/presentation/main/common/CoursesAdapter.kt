package com.example.presentation.main.common

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.courses.model.Course

class CoursesAdapter(
    onClick: (Course) -> Unit
) : ListAdapter<Course, RecyclerView.ViewHolder>(CourseDiffCallback()) {

    private val delegates = listOf(CourseAdapterDelegate(onClick))

    override fun getItemViewType(position: Int): Int =
        delegates.indexOfFirst { it.isForViewType(getItem(position)) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegates[getItemViewType(position)].onBindViewHolder(holder, getItem(position))

    fun submitCourses(list: List<Course>, onCommit: () -> Unit = {}) {
        submitList(list) {
            onCommit()
        }
    }

    private class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(old: Course, new: Course): Boolean =
            old.id == new.id

        override fun areContentsTheSame(old: Course, new: Course): Boolean =
            old == new
    }
}
