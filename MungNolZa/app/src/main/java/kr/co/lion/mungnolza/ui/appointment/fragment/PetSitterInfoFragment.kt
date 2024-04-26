package kr.co.lion.mungnolza.ui.appointment.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.FragmentPetSitterInfoBinding
import kr.co.lion.mungnolza.ext.repeatOnViewStarted
import kr.co.lion.mungnolza.ui.appointment.adapter.ReviewAdapter
import kr.co.lion.mungnolza.ui.appointment.vm.PetSitterInfoViewModel
import kr.co.lion.mungnolza.ui.appointment.vm.PetSitterInfoViewModelFactory

class PetSitterInfoFragment : Fragment(R.layout.fragment_pet_sitter_info) {
    private val args: PetSitterInfoFragmentArgs by navArgs()
    private val viewModel: PetSitterInfoViewModel by viewModels { PetSitterInfoViewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPetSitterInfoBinding.bind(view)

        with(binding) {
            toolbar.setNavigationOnClickListener {
                val action = PetSitterInfoFragmentDirections.toMatchingFragment()
                Navigation.findNavController(view).navigate(action)
            }

            val petSitter = args.info
            viewModel.onLoadingPetSitterPage(petSitter.petSitterIdx.toInt())

            repeatOnViewStarted {
                viewModel.review.collect { it ->

                    val reviewCount = it.size
                    reviewCnt.text = "${reviewCount}개의 리뷰"

                    val averageReviewCount = it.map { it.review.reviewStarCount.toInt() }.average()
                    reviewScore.text = averageReviewCount.toFloat().toString()
                    ratingBar.rating = averageReviewCount.toFloat()
                    reviewRating.rating = averageReviewCount.toFloat()

                    val adapter = ReviewAdapter(it)
                    rvReview.adapter = adapter
                    rvReview.layoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                }
            }


            val careerStr = StringBuilder()
            petSitter.petSitterCareer.map { career ->
                careerStr.append(career).append("\n")
            }

            val certificationStr = StringBuilder()
            petSitter.petSitterCertificate.map { certification ->
                certificationStr.append(certification).append("\n")
            }

            val introduce = petSitter.petSitterIntroduce.split("\\.".toRegex())
                .joinToString("\n\n") { result ->
                    result.trim()
                }

            viewLifecycleOwner.lifecycleScope.launch {
                val img = viewModel.fetchPetSitterImage(petSitter.petSitterIdx, petSitter.imgName)
                Glide.with(binding.root)
                    .load(img.toString())
                    .into(binding.imgPetSitter)
            }

            petSitterName.text = petSitter.petSitterName
            petSitterIntroduce.text = introduce
            petSitterCertification.text = certificationStr
            this.petSitterCareer.text = careerStr
        }
    }
}