package com.sssakib.androidpaging

import CourseModal
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.course_rv_item.view.*

class CourseRVAdapter(context: Context, courseModalArrayList: ArrayList<CourseModal>) :
    RecyclerView.Adapter<CourseRVAdapter.ViewHolder>() {

    private val context: Context
    private val courseModalArrayList: ArrayList<CourseModal>

    init {
        this.context = context
        this.courseModalArrayList = courseModalArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseRVAdapter.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.course_rv_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CourseRVAdapter.ViewHolder, position: Int) {

        val courses = courseModalArrayList[position]
        holder.courseNameTV.text = courses.courseName
        holder.courseTracksTV.text = courses.courseTracks
        holder.courseModesTV.text = courses.courseModes
        Picasso.get().load(courses.courseImg).into(holder.courseIV)
    }

    override fun getItemCount(): Int {
        return courseModalArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val courseNameTV: TextView = itemView.idTVCourseName
        val courseTracksTV: TextView = itemView.idTVCourseDuration
        val courseModesTV: TextView = itemView.idTVCourseDescription
        val courseIV: ImageView = itemView.idIVCourse


    }



}