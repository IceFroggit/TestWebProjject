package com.example.testwebprojject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testwebprojject.Catfact
import com.example.testwebprojject.R
import com.example.testwebprojject.api.ApiRequest
import com.example.testwebprojject.databinding.FragmentFirstBinding
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@DelicateCoroutinesApi
class FirstFragment : Fragment() {
    private lateinit var tvText: TextView
    private lateinit var  progressBar: ProgressBar
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = binding.progressBar
        tvText = binding.textviewFirst
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        getCurrentData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    /**
     * @return Функция добавляет данные на экран, полученные из Api Get-Запроса
     *
     */

    private fun getCurrentData(){
        tvText.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        val api:ApiRequest = Retrofit.Builder()
            .baseUrl("https://catfact.ninja")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO){
            val response: Response<Catfact> = api.getQuestListItem().awaitResponse()
            if (response.isSuccessful){
                val data: Catfact = response.body()!!
                Log.d("TAG",data.message)

                withContext(Dispatchers.Main){
                    tvText.text = data.message
                    tvText.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE

                }
            }
        }
    }
}