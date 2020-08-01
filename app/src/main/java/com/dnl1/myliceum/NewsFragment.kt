package com.dnl1.myliceum

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnl1.myliceum.Adapter.NewsAdapter
import com.dnl1.myliceum.Common.NewsListCommon
import com.dnl1.myliceum.Interface.NewsListRetrofitService
import com.dnl1.myliceum.Model.News
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {



    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mService : NewsListRetrofitService
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter : NewsAdapter
    lateinit var dialog: AlertDialog
    

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment




        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mService = NewsListCommon.retrofitService

        recyclerNewsList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        recyclerNewsList.layoutManager = layoutManager

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(activity).build()

        getAllNewsList()

    }


    private fun getAllNewsList() {
        dialog.show()

        mService.getNewsList().enqueue(object : Callback<MutableList<News>> {
            override fun onFailure(call: Call<MutableList<News>>, t: Throwable) {

                Toast.makeText(activity!!.baseContext, "Не вдалось завантажити дані. Перевірте підключення до інтернету", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            override fun onResponse(call: Call<MutableList<News>>, response: Response<MutableList<News>>) {
                adapter = NewsAdapter(activity!!.baseContext, response.body() as MutableList<News>)
                adapter.notifyDataSetChanged()
                recyclerNewsList.adapter = adapter

                dialog.dismiss()
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}