package com.dnl1.myliceum

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dnl1.myliceum.Interface.NewsDetailRetrofitService
import com.dnl1.myliceum.Model.News
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_news_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var mService : NewsDetailRetrofitService
    lateinit var dialog: AlertDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_news_detail, container, false)

        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var selected_news_id : Int? = arguments?.getInt("selected_news_id")


        mService = NewsDetailCommon.retrofitService

        dialog = SpotsDialog.Builder().setCancelable(false).setContext(activity).build()

        getSelectedNews(selected_news_id.toString())
    }





    private fun getSelectedNews(id: String) {
        dialog.show()

        mService.search(id).enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(activity!!.baseContext, "Не вдалось завантажити дані. Перевірте підключення до інтернету", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                var selected_news = response.body()
                news_detail_title.text = selected_news?.title
               /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    news_detail_post.setText(Html.fromHtml(selected_news?.post, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    news_detail_post.setText(Html.fromHtml(selected_news?.post));
                }*/
                selected_news!!.post?.let { news_detail_web_view.loadData(it, "text/html; charset=UTF-8", null) }
                Picasso.get().load(selected_news?.image).into(news_detail_image)



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
         * @return A new instance of fragment NewsDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}