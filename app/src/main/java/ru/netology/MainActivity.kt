package ru.netology


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.dto.PostAbstract
import ru.netology.dto.PostEvent
import ru.netology.dto.PostVideo
import ru.netology.util.Location
import ru.netology.util.verboseTime
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val date: Date? = SimpleDateFormat("dd-MM-yyyy").parse("17-07-2020")
        val post = PostEvent(
            1,
            "Arshinsky Vadim",
            "На острове Ольхон, который является сакральным центром силы Байкала, располгается мыс Шаманка, который является вместилещем главного бурхана всей территории",
            date!!,
            "",
            Location("53.203965", "107.338867"),
            0,
            0,
            3
        )

        initPost(post)
        refreshPost(post)
        val postVideo = PostVideo(
            1,
            "Arshinsky Vadim",
            "Мыс Бурхан зимой (кликните на картинку для просмотра)",
            SimpleDateFormat("dd-MM-yyyy").parse("18-07-2020")!!,
            "https://youtu.be/73syI1uEWsM",
            177,
            0,
            0
        )
        initPost(postVideo)
        refreshPostVideo(postVideo)

    }

    private fun refreshPostVideo(post: PostVideo) {
        val curentDate = Date(System.currentTimeMillis())
        val elapsed = (curentDate.time - post.created.time) / 1_000
        createdTvVideo.text = verboseTime(elapsed)

        authorTvVideo.text = post.author
        contentTvVideo.text = post.content
        likeTvVideo.text = post.likes.toString()
        commentTvVideo.text = post.comments.toString()
        shareTvVideo.text = post.shares.toString()


        if (post.likes == 0) likeTvVideo.visibility = View.INVISIBLE else likeTvVideo.visibility =
            View.VISIBLE
        if (post.comments == 0) commentTvVideo.visibility =
            View.INVISIBLE else commentTvVideo.visibility =
            View.VISIBLE
        if (post.shares == 0) shareTvVideo.visibility =
            View.INVISIBLE else shareTvVideo.visibility =
            View.VISIBLE

        if (post.likedByMe) {
            likeButtonVideo.setImageResource(R.drawable.like_active)
            likeTvVideo.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            likeButtonVideo.setImageResource(R.drawable.like_inactive)
            likeTvVideo.setTextColor(resources.getColor(R.color.colorBlack))
        }
        if (post.commentedByMe) {
            commentButtonVideo.setImageResource(R.drawable.comment_active)
            commentTvVideo.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            commentButtonVideo.setImageResource(R.drawable.comment)
            commentTvVideo.setTextColor(resources.getColor(R.color.colorBlack))
        }
        if (post.sharedByMe) {
            shareButtonVideo.setImageResource(R.drawable.share_active)
            shareTvVideo.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            shareButtonVideo.setImageResource(R.drawable.share)
            shareTvVideo.setTextColor(resources.getColor(R.color.colorBlack))
        }

    }


    private fun initPost(post: PostAbstract) {
        if (post is PostEvent) {
            val location = "geo:" + post.coord.lat + ", " + post.coord.lon
            val uri: Uri = Uri.parse(location)
            geoTag.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            likeButton.setOnClickListener {
                if (post.likedByMe) {
                    post.likes--
                    post.likedByMe = false
                } else {
                    post.likes++
                    post.likedByMe = true
                }
                refreshPost(post)
            }
            commentButton.setOnClickListener {
                if (post.commentedByMe) {
                    post.comments--
                    post.commentedByMe = false
                } else {
                    post.comments++
                    post.commentedByMe = true
                }
                refreshPost(post)
            }
            shareButton.setOnClickListener {
                if (post.sharedByMe) {
                    post.shares--
                    post.sharedByMe = false
                } else {
                    post.shares++
                    post.sharedByMe = true
                }
                refreshPost(post)

            }
        }
        if (post is PostVideo) {
            val uri: Uri = Uri.parse(post.url)
            contentIvVideo.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            likeButtonVideo.setOnClickListener {
                if (post.likedByMe) {
                    post.likes--
                    post.likedByMe = false
                } else {
                    post.likes++
                    post.likedByMe = true
                }
                refreshPostVideo(post)
            }
            commentButtonVideo.setOnClickListener {
                if (post.commentedByMe) {
                    post.comments--
                    post.commentedByMe = false
                } else {
                    post.comments++
                    post.commentedByMe = true
                }
                refreshPostVideo(post)
            }
            shareButtonVideo.setOnClickListener {
                if (post.sharedByMe) {
                    post.shares--
                    post.sharedByMe = false
                } else {
                    post.shares++
                    post.sharedByMe = true
                }
                refreshPostVideo(post)

            }
        }


    }

    fun refreshPost(post: PostAbstract) {
        val curentDate = Date(System.currentTimeMillis())
        val elapsed = (curentDate.time - post.created.time) / 1_000
        createdTv.text = verboseTime(elapsed)

        authorTv.text = post.author
        contentTv.text = post.content
        likeTv.text = post.likes.toString()
        commentTv.text = post.comments.toString()
        shareTv.text = post.shares.toString()


        if (post.likes == 0) likeTv.visibility = View.INVISIBLE else likeTv.visibility =
            View.VISIBLE
        if (post.comments == 0) commentTv.visibility = View.INVISIBLE else commentTv.visibility =
            View.VISIBLE
        if (post.shares == 0) shareTv.visibility = View.INVISIBLE else shareTv.visibility =
            View.VISIBLE

        if (post.likedByMe) {
            likeButton.setImageResource(R.drawable.like_active)
            likeTv.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            likeButton.setImageResource(R.drawable.like_inactive)
            likeTv.setTextColor(resources.getColor(R.color.colorBlack))
        }
        if (post.commentedByMe) {
            commentButton.setImageResource(R.drawable.comment_active)
            commentTv.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            commentButton.setImageResource(R.drawable.comment)
            commentTv.setTextColor(resources.getColor(R.color.colorBlack))
        }
        if (post.sharedByMe) {
            shareButton.setImageResource(R.drawable.share_active)
            shareTv.setTextColor(resources.getColor(R.color.colorRed))
        } else {
            shareButton.setImageResource(R.drawable.share)
            shareTv.setTextColor(resources.getColor(R.color.colorBlack))
        }

    }

}
