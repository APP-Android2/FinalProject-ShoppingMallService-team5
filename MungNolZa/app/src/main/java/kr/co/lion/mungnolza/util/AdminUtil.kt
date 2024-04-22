package kr.co.lion.mungnolza.util

class AdminUtil {


}

// 게시판 종류를 나타내는 값을 정의한다.
enum class ContentType(var str:String, var number:Int) {
    TYPE_MEMBER("회원", 0),
    TYPE_PETSITTER("펫시터", 1),
    TYPE_APPOINTMENT("예약", 2),
    TYPE_BOARD("게시판", 3),
    TYPE_SETTING("세팅", 4)
}

// ContentActivity에서 보여줄 프래그먼트들의 이름
enum class ContentFragmentName(var str:String){
    ADMIN_MAIN_FRAGMENT("AdminMainFragment"),
    MEMBER_CONTENT_FRAGMENT("MemberContentFragment"),
    PETSITTER_CONTENT_FRAGMENT("PetsitterContentFragment"),
    MODIFY_CONTENT_FRAGMENT("ModifyContentFragment"),
    MODIFY_USER_FRAGMENT("ModifyUserFragment"),
}