package com.dnl1.myliceum.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dnl1.myliceum.Model.Teacher
import com.dnl1.myliceum.R
import com.dnl1.myliceum.ScheduleActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.teacher_schedule_list_view.view.*

class TeachersScheduleAdapter(private val context: Context, private val teachersList: MutableList<Teacher>): RecyclerView.Adapter<TeachersScheduleAdapter.TeachersScheduleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersScheduleHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.teacher_schedule_list_view, parent, false)
        return TeachersScheduleHolder(itemView)
    }

    override fun getItemCount(): Int {
        return teachersList.size
    }

    override fun onBindViewHolder(holder: TeachersScheduleHolder, position: Int) {
        holder.teachersName.text = "${teachersList[position].second_name} ${teachersList[position].first_name} ${teachersList[position].fathers_name}"


        holder.itemView.setOnClickListener { view: View ->
            //      var bundle = bundleOf("selected_news_id" to classesList[position].id)
            //    view.findNavController().navigate(R.id.action_newsFragment_to_newsDetailFragment, bundle)
            //Toast.makeText(context, teachersList[position].id.toString(), Toast.LENGTH_SHORT).show()

            val intent = Intent(context, ScheduleActivity::class.java)

            intent.putExtra(ScheduleActivity.SCHEDULE_TYPE, "teachers_schedule")
            intent.putExtra(ScheduleActivity.SCHEDULE_ID, teachersList[position].id)
            intent.putExtra(ScheduleActivity.FRAGMENT_TITLE, "${teachersList[position].second_name} ${teachersList[position].first_name} ${teachersList[position].fathers_name}")
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            context.startActivity(intent)
        }
    }

    class TeachersScheduleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var teachersName : TextView

        init {
            teachersName = itemView.teachers_name
        }


    }
}