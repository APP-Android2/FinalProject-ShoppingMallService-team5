package kr.co.lion.mungnolza.ui.admin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.co.lion.mungnolza.R
import kr.co.lion.mungnolza.databinding.ActivityAdminContentBinding
import kr.co.lion.mungnolza.databinding.ActivityNoPetBinding
import kr.co.lion.mungnolza.ui.admin.fragment.AdminMainFragment
import kr.co.lion.mungnolza.util.ContentFragmentName
import kr.co.lion.mungnolza.util.ContentType

class AdminContentActivity : AppCompatActivity() {

//    private lateinit var binding: ActivityAdminContentBinding
lateinit var activityAdminContentBinding: ActivityAdminContentBinding

    // 프래그먼트 객체를 담을 변수
    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAdminContentBinding = ActivityAdminContentBinding.inflate(layoutInflater)
        setContentView(activityAdminContentBinding.root)

        settingNavigationView()

        // MainFragment가 나타나도록 한다.
        val mainBundle = Bundle()
        // 게시판 종류를 담는다.
        mainBundle.putString("TypeName", ContentType.TYPE_ALL.str)
        mainBundle.putInt("TypeNumber", ContentType.TYPE_ALL.number)

        replaceFragment(ContentFragmentName.ADMIN_MAIN_FRAGMENT, false, false, mainBundle)
    }

    // 네비게이션 뷰 설정
    fun settingNavigationView(){
        activityAdminContentBinding.apply {
            navigationViewContent.apply {

                // 메뉴를 눌렀을 때 동작하는 리스너
                setNavigationItemSelectedListener {

                    // 딜레이
                    SystemClock.sleep(200)

                    // 메뉴의 id로 분기한다.
                    when(it.itemId){
                        R.id.menuItemMemberManagement -> {
                            val mainBundle = Bundle()
                            // 게시판 종류를 담는다.
                            mainBundle.putString("TypeName", ContentType.TYPE_MEMBER.str)
                            mainBundle.putInt("TypeNumber", ContentType.TYPE_MEMBER.number)

                            replaceFragment(ContentFragmentName.ADMIN_MAIN_FRAGMENT, false, false, mainBundle)
                            // NavigationView를 닫아준다.
                            drawerLayoutContent.close()
                        }
                        R.id.menuItemContentPetManagement -> {
                            val mainBundle = Bundle()
                            // 게시판 종류를 담는다.
                            mainBundle.putString("TypeName", ContentType.TYPE_PETSITTER.str)
                            mainBundle.putInt("TypeNumber", ContentType.TYPE_PETSITTER.number)

                            replaceFragment(ContentFragmentName.ADMIN_MAIN_FRAGMENT, false, false, mainBundle)
                            // NavigationView를 닫아준다.
                            drawerLayoutContent.close()
                        }


//                        // 로그아웃
//                        R.id.menuItemContentNavigationLogout -> {
//                            logoutProcess()
//                            startMainActivity()
//                        }

                    }

                    true
                }
            }
        }
    }

    // 지정한 Fragment를 보여주는 메서드
    // name : 프래그먼트 이름
    // addToBackStack : BackStack에 포함 시킬 것인지
    // isAnimate : 애니메이션을 보여줄 것인지
    // data : 새로운 프래그먼트에 전달할 값이 담겨져 있는 Bundle 객체
    fun replaceFragment(name:ContentFragmentName, addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){

        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(name) {
            // 게시글 목록 화면
            ContentFragmentName.ADMIN_MAIN_FRAGMENT -> {
                newFragment = AdminMainFragment()
            }

            else -> {}
        }
            // 게시글 작성 화면
//            ContentFragmentName.ADD_CONTENT_FRAGMENT -> {
//                newFragment = AddContentFragment()
//            }
//            // 게시글 읽기 화면
//            ContentFragmentName.READ_CONTENT_FRAGMENT -> {
//                newFragment = ReadContentFragment()
//            }
//            // 게시글 수정 화면
//            ContentFragmentName.MODIFY_CONTENT_FRAGMENT -> {
//                newFragment = ModifyContentFragment()
//            }
//            // 사용자 정보 수정 화면
//            ContentFragmentName.MODIFY_USER_FRAGMENT -> {
//                newFragment = ModifyUserFragment()
//            }
//        }

        // 새로운 Fragment에 전달할 객체가 있다면 arguments 프로퍼티에 넣어준다.
        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){

            // 애니메이션 설정
            if(isAnimate == true){
                // oldFragment -> newFragment
                // oldFragment : exitTransition
                // newFragment : enterTransition

                // newFragment -> oldFragment
                // oldFragment : reenterTransition
                // newFragment : returnTransition

                // MaterialSharedAxis : 좌우, 위아래, 공중 바닥 사이로 이동하는 애니메이션 효과
                // X - 좌우
                // Y - 위아래
                // Z - 공중 바닥
                // 두 번째 매개변수 : 새로운 화면이 나타나는 것인지 여부를 설정해준다.
                // true : 새로운 화면이 나타나는 애니메이션이 동작한다.
                // false : 이전으로 되돌아가는 애니메이션이 동작한다.

                if(oldFragment != null){
                    // old에서 new가 새롭게 보여질 때 old의 애니메이션
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    // new에서 old로 되돌아갈때 old의 애니메이션
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                // old에서 new가 새롭게 보여질 때 new의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                // new에서 old로 되돌아갈때 new의 애니메이션
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
            }

            // Fragment를 교체한다.(이전 Fragment가 없으면 새롭게 추가하는 역할을 수행한다)
            // 첫 번째 매개 변수 : Fragment를 배치할 FragmentContainerView의 ID
            // 두 번째 매개 변수 : 보여주고하는 Fragment 객체를
            fragmentTransaction.replace(R.id.containerContent, newFragment!!)

            // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시켜 준다.
            if(addToBackStack == true){
                // BackStack 포함 시킬때 이름을 지정해주면 원하는 Fragment를 BackStack에서 제거할 수 있다.
                fragmentTransaction.addToBackStack(name.str)
            }
            // Fragment 교체를 확정한다.
            fragmentTransaction.commit()
        }
    }

    // BackStack에서 Fragment를 제거한다.
    fun removeFragment(name:ContentFragmentName){
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


    // 현재 액티비티를 종료하고 MainActivity를 실행하는 메서드
    fun startMainActivity(){
        // MainActivity를 실행하고 현재 Activity는 종료한다.
        val mainIntent = Intent(this@AdminContentActivity, AdminMainActivity::class.java)
        startActivity(mainIntent)

        finish()
    }
}