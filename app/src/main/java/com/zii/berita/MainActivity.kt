package com.zii.berita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region News Headline
        val headlinenews: news?
        if(NewsModel.newslist.isNotEmpty()) {
            headlinenews = NewsModel.newslist[NewsModel.newslist.size-1]
            tvw_TitleHeadline.setText(headlinenews.title)
            tvw_DescHeadline.setText(headlinenews.desc)
            img_news0.setImageResource(headlinenews.photo)

            cdv_newsheadline.setOnClickListener{
                val intent_detail = Intent(this@MainActivity,
                    DetailActivity::class.java).apply {
                    putExtra(DetailActivity.cont_TitleNews,
                        headlinenews.title.toString())
                    putExtra(DetailActivity.cont_PhotoNews,
                        headlinenews.photo.toString())

                }
                startActivity(intent_detail)
                finish()
            }
        }
        val layManager = LinearLayoutManager(this)
        layManager.orientation = LinearLayoutManager.VERTICAL
        rcv_daftarberita.layoutManager = layManager

        newsAdapter = NewsAdapter(this, NewsModel.newslist)
        rcv_daftarberita.adapter = newsAdapter

        newsAdapter.setOnItemClickCallback(
            object : NewsAdapter.OnItemClickCallback {
                override fun onItemClick(data: news) {
                    val intent = Intent(this@MainActivity,
                        DetailActivity::class.java).apply {
                        putExtra(DetailActivity.cont_TitleNews,
                            data.title)
                        putExtra(DetailActivity.cont_PhotoNews,
                            data.photo.toString())
                    }
                    startActivity(intent)
                    finish()
                }
            })
    }
}