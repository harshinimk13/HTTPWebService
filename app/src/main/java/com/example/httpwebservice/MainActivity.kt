package com.example.httpwebservice

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : Activity() {

    private lateinit var btnFetch: Button
    private lateinit var progressBar: ProgressBar

    private lateinit var tvStatus: TextView
    private lateinit var tvPostId: TextView
    private lateinit var tvUserId: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView

    private val executor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFetch = findViewById(R.id.btnFetch)
        progressBar = findViewById(R.id.progressBar)

        tvStatus = findViewById(R.id.tvStatus)
        tvPostId = findViewById(R.id.tvPostId)
        tvUserId = findViewById(R.id.tvUserId)
        tvTitle = findViewById(R.id.tvTitle)
        tvBody = findViewById(R.id.tvBody)

        btnFetch.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        progressBar.visibility = View.VISIBLE
        btnFetch.isEnabled = false

        tvStatus.text = "● LOADING"
        tvStatus.setTextColor(0xFF1565C0.toInt())
        tvStatus.setBackgroundColor(0xFFE3F2FD.toInt())

        tvPostId.text = "--"
        tvUserId.text = "--"
        tvTitle.text = "Fetching data..."
        tvBody.text = "Please wait while the server responds."

        executor.execute {
            var connection: HttpURLConnection? = null

            try {
                val url = URL(
                    "https://jsonplaceholder.typicode.com/posts/1"
                )

                connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = "GET"
                connection.connectTimeout = 15000
                connection.readTimeout = 15000

                connection.setRequestProperty(
                    "Accept",
                    "application/json"
                )

                val statusCode = connection.responseCode

                val stream = if (statusCode in 200..299) {
                    connection.inputStream
                } else {
                    connection.errorStream
                }

                val response = stream?.use { input ->
                    BufferedReader(
                        InputStreamReader(input)
                    ).use { reader ->
                        reader.readText()
                    }
                } ?: ""

                if (statusCode in 200..299) {
                    val json = JSONObject(response)

                    val id = json.getInt("id")
                    val userId = json.getInt("userId")
                    val title = json.getString("title")
                    val body = json.getString("body")

                    runOnUiThread {
                        tvStatus.text = "● SUCCESS"
                        tvStatus.setTextColor(
                            0xFF2E7D32.toInt()
                        )
                        tvStatus.setBackgroundColor(
                            0xFFE8F5E9.toInt()
                        )

                        tvPostId.text = id.toString()
                        tvUserId.text = userId.toString()
                        tvTitle.text = title
                        tvBody.text = body
                    }

                } else {
                    runOnUiThread {
                        tvStatus.text = "● HTTP $statusCode"
                        tvStatus.setTextColor(
                            0xFFC62828.toInt()
                        )
                        tvStatus.setBackgroundColor(
                            0xFFFFEBEE.toInt()
                        )

                        tvTitle.text = "Request failed"
                        tvBody.text =
                            "Server returned HTTP $statusCode\n$response"
                    }
                }

            } catch (e: IOException) {
                runOnUiThread {
                    showError(
                        "Network error",
                        e.localizedMessage
                            ?: "Please check your internet connection."
                    )
                }

            } catch (e: Exception) {
                runOnUiThread {
                    showError(
                        "Unexpected error",
                        e.localizedMessage ?: "Something went wrong."
                    )
                }

            } finally {
                connection?.disconnect()

                runOnUiThread {
                    progressBar.visibility = View.GONE
                    btnFetch.isEnabled = true
                }
            }
        }
    }

    private fun showError(title: String, message: String) {
        tvStatus.text = "● ERROR"
        tvStatus.setTextColor(0xFFC62828.toInt())
        tvStatus.setBackgroundColor(0xFFFFEBEE.toInt())

        tvPostId.text = "--"
        tvUserId.text = "--"
        tvTitle.text = title
        tvBody.text = message
    }

    override fun onDestroy() {
        executor.shutdownNow()
        super.onDestroy()
    }


}
