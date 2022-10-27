package com.arief.news.ui.detail

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arief.news.R
import com.arief.news.databinding.FragmentDetailBinding
import com.arief.news.utils.DateHelper
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = DetailFragmentArgs.fromBundle(requireArguments()).news

        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
        binding.date.text = DateHelper().changeFormatTime(item.publishedAt)
        binding.source.text = item.source.name
        if(!item.urlToImage.isNullOrEmpty()) {
            Glide.with(this)
                .load(item.urlToImage)
                .error(R.drawable.no_image)
                .into(binding.poster)
        }
        binding.title.text = item.title
        if(item.content == null){
            val content = "${item.description} (Click to Read More)"
            val ss = SpannableString(content)
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(item.url)
                    startActivity(i)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }
            ss.setSpan(
                clickableSpan,
                content.length - 20,
                content.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.description.apply {
                text = ss
                movementMethod = LinkMovementMethod.getInstance()
                highlightColor = Color.TRANSPARENT
            }
        }else {
            val endContents = item.content!!.indexOf("[+", 0, true)
            if (endContents == -1) {
                binding.description.text = item.content
            } else {
                val content = "${item.content!!.substring(0, endContents)} (Click to Read More)"
                val ss = SpannableString(content)
                val clickableSpan: ClickableSpan = object : ClickableSpan() {
                    override fun onClick(textView: View) {
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(item.url)
                        startActivity(i)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }
                }
                ss.setSpan(
                    clickableSpan,
                    content.length - 20,
                    content.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.description.apply {
                    text = ss
                    movementMethod = LinkMovementMethod.getInstance()
                    highlightColor = Color.TRANSPARENT
                }
            }
        }
        binding.author.text = item.authour
    }
}