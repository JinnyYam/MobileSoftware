package com.example.finalproject
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class FeedAdapter(private val context: Context, private val imageList: List<Int> // 이미지 리소스 ID 리스트
    ) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])

        // 부모 뷰의 LayoutParams 가져오기
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams

        // 랜덤 마진 생성 (0dp ~ 32dp 범위)
        val randomMargin1 = Random.nextInt(0, 33) // 0 ~ 32 사이의 랜덤 숫자
        val randomMargin2 = Random.nextInt(0, 33) // 0 ~ 32 사이의 랜덤 숫자

        if (position % 2 == 1) {
            // 홀수: 좌측 여백에 랜덤 값 추가
            layoutParams.setMargins(randomMargin1, 0, 0, randomMargin2)
        } else {
            // 짝수: 우측 여백에 랜덤 값 추가
            layoutParams.setMargins(0, 0, randomMargin1, randomMargin2)
        }

        // LayoutParams 다시 설정
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = imageList.size

}

