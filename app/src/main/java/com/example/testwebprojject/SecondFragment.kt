package com.example.testwebprojject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testwebprojject.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var nameEditText:EditText
    lateinit var ageEditText:EditText
    lateinit var phoneEditText:EditText
    lateinit var addressEditText:EditText
    lateinit var conditionsCheckBox:CheckBox
    lateinit var resultMsg:TextView
    lateinit var genderRcheckBox:RadioGroup
    lateinit var maleRadioButton: RadioButton
    lateinit var femaleRadioButton: RadioButton
    var genderbool = false

  lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         genderRcheckBox = view.findViewById(R.id.radioGroup)
         button = view.findViewById(R.id.button)
         resultMsg  =view.findViewById(R.id.msg)
         nameEditText = view.findViewById(R.id.names) as EditText
         fullName = nameEditText.text.toString()
         ageEditText = view.findViewById(R.id.age) as EditText
         phoneEditText = view.findViewById(R.id.phone) as EditText
         addressEditText = view.findViewById(R.id.address) as EditText
         conditionsCheckBox = view.findViewById(R.id.conditions) as CheckBox
         genderbool = conditionsCheckBox.isChecked

        maleRadioButton = view.findViewById(R.id.male)
        maleRadioButton.setOnClickListener(radioButtonClickListener)
        femaleRadioButton = view.findViewById(R.id.female)
        femaleRadioButton.setOnClickListener(radioButtonClickListener)


        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        button.setOnClickListener {
            if(conditionsCheckBox.isChecked){
                nameEditText.visibility = View.INVISIBLE
                ageEditText.visibility = View.INVISIBLE
                phoneEditText.visibility = View.INVISIBLE
                addressEditText.visibility = View.INVISIBLE
                conditionsCheckBox.visibility = View.INVISIBLE
                genderRcheckBox.visibility = View.INVISIBLE
                if (genderbool)
                  val  gender = "Мужской"
                else
                   val gender = "Женcкий"
                val title:TextView = view.findViewById(R.id.title)
                title.text = "Регистрация завершена"
                resultMsg.text = nameEditText.text.toString() + " Возраст: "+ageEditText.text.toString()+" Телефон: "+phoneEditText.text.toString()+" Адресс: "+addressEditText.text.toString()+" Пол: "+ gender
                resultMsg.visibility = View.VISIBLE

            }
        }
    }
    var radioButtonClickListener =
        View.OnClickListener { v ->
            val rb = v as RadioButton
            when (rb.id) {
                R.id.male -> genderbool = true
                R.id.female -> genderbool = false
                else -> {}
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}