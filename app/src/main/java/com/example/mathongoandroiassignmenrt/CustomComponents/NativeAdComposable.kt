package com.example.mathongoandroiassignmenrt.CustomComponents


import android.content.Context
import android.view.View.inflate
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.google.android.gms.ads.nativead.NativeAd

import com.example.mathongoandroiassignmenrt.databinding.NativeAdsMediumBinding

@Composable
fun NativeAdComposableNew(context: Context, state: NativeAd?) {

    LaunchedEffect(Unit) {


    }
    if (state != null)
        AndroidViewBinding(factory = NativeAdsMediumBinding::inflate, modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))) {
            state.let { nativeAd ->
                nativeAd.icon.let { this.icon.setImageDrawable(it?.drawable) }
                nativeAd.headline?.let { headline ->
                    this.primary.text = headline
                }
                nativeAd.body?.let { body ->
                    this.secondary.text = body
                }
                nativeAd.callToAction?.let { actionButton ->
                    this.cta.text = actionButton
                    this.nativeAdView.callToActionView = this.cta
                }

                nativeAd.let { this.nativeAdView.setNativeAd(it) }
            }
        }

}


