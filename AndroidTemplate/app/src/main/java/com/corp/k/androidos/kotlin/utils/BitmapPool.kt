package com.corp.k.androidos.kotlin.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.widget.ImageView

class BitmapPool(){

    fun setBitmapInImageView(imgView: ImageView, fileInPath: String, fileInPathTwo: String) {
        val bitmapOne = BitmapFactory.decodeFile(fileInPath)
        imgView.setImageBitmap(bitmapOne)
        //change to next Bitmap
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(fileInPathTwo, options)
        if (canUseForInBitmap(bitmapOne, options)) {
            options.inMutable = true
            options.inBitmap = bitmapOne
        }
        options.inJustDecodeBounds = false
        val bitmapTwo = BitmapFactory.decodeFile(fileInPathTwo, options)
        imgView.setImageBitmap(bitmapTwo)
    }

    fun canUseForInBitmap(candidate: Bitmap, targetOptions: BitmapFactory.Options): Boolean {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // From Android 4.4 (KitKat) onward we can re-use if the byte size of
            // the new bitmap is smaller than the reusable bitmap candidate
            // allocation byte count.
            val width = targetOptions.outWidth / targetOptions.inSampleSize
            val height = targetOptions.outHeight / targetOptions.inSampleSize
            val byteCount = width * height * getBytesPerPixel(candidate.config)

            try {
                return byteCount <= candidate.allocationByteCount
            } catch (e: NullPointerException) {
                return byteCount <= candidate.height * candidate.rowBytes
            }

        }
        // On earlier versions, the dimensions must match exactly and the inSampleSize must be 1
        return (candidate.width == targetOptions.outWidth
                && candidate.height == targetOptions.outHeight
                && targetOptions.inSampleSize == 1)
    }

    private fun getBytesPerPixel(config: Bitmap.Config?): Int {
        var config = config
        if (config == null) {
            config = Bitmap.Config.ARGB_8888
        }
        val bytesPerPixel: Int
        when (config) {
            Bitmap.Config.ALPHA_8 -> bytesPerPixel = 1
            Bitmap.Config.RGB_565, Bitmap.Config.ARGB_4444 -> bytesPerPixel = 2
            Bitmap.Config.ARGB_8888 -> bytesPerPixel = 4
            else -> bytesPerPixel = 4
        }
        return bytesPerPixel
    }

    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    fun decodeSampledBitmapFromResource(res: Resources, resId: Int, reqWidth: Int, reqHeight: Int): Bitmap {

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }
}