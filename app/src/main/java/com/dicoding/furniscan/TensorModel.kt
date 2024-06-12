package com.dicoding.furniscan

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.CastOp
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class TensorModel(private val context: Context, private val modelName: String) {

    private var tflite: Interpreter? = null
    private var imageSizeX: Int = 640 // sesuaikan dengan ukuran input model Anda
    private var imageSizeY: Int = 640 // sesuaikan dengan ukuran input model Anda

    init {
        tflite = Interpreter(loadModelFile())
    }

    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor = context.assets.openFd(modelName)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun runModelOnImage(bitmap: Bitmap): Array<Array<FloatArray>> {
        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)
        val imageProcessor = ImageProcessor.Builder()
//            .add(ResizeWithCropOrPadOp(imageSizeX, imageSizeY)) // Crop image
            .add(ResizeOp(imageSizeX, imageSizeY, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR)) // Resize image
//            .add(NormalizeOp(0f, 255f)) // Normalize image
            .add(CastOp(DataType.FLOAT32))
            .build()
        val tImage = imageProcessor.process(tensorImage)
        val tfliteModel = tflite ?: throw RuntimeException("TFLite model is not initialized.")
        val output = Array(1) { Array(25200) { FloatArray(19) } }
        try {
            tfliteModel.run(tImage.buffer, output)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error running model", Toast.LENGTH_SHORT).show()
        }
        return output
    }

    fun toBitmap(uri: Uri): Bitmap {
        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }

        return bitmap.copy(Bitmap.Config.ARGB_8888, true)
    }
}