package com.dnl1.myliceum.Adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dnl1.myliceum.Model.SchoolClasses
import com.dnl1.myliceum.R
import com.dnl1.myliceum.ScheduleActivity
import com.google.android.material.internal.ContextUtils.getActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.student_schedule_list_view.view.*

class StudentScheduleAdapter(private val context: Context, private val classesList: MutableList<SchoolClasses>): RecyclerView.Adapter<StudentScheduleAdapter.StudentScheduleHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentScheduleHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.student_schedule_list_view, parent, false)
        return StudentScheduleHolder(itemView)
    }

    override fun getItemCount(): Int {
        return classesList.size
    }

    override fun onBindViewHolder(holder: StudentScheduleHolder, position: Int) {
        holder.classTitle.text = classesList[position].class_title


        holder.itemView.setOnClickListener { view: View ->
            //var bundle = bundleOf("schedule_type" to "students_schedule")
            //view.findNavController().navigate(R.id.action_studentScheduleFragment_to_scheduleActivity, bundle)
            //Toast.makeText(context, classesList[position].id.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ScheduleActivity::class.java)

            intent.putExtra(ScheduleActivity.SCHEDULE_TYPE, "students_schedule")
            intent.putExtra(ScheduleActivity.SCHEDULE_ID, classesList[position].id)
            intent.putExtra(ScheduleActivity.FRAGMENT_TITLE, classesList[position].class_title)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


            context.startActivity(intent)
        }
    }

    class StudentScheduleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var classTitle : TextView

        init {
            classTitle = itemView.class_title
        }


    }
}