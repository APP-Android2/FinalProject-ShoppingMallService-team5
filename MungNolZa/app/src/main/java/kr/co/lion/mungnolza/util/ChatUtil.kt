package kr.co.lion.mungnolza.util


class ChatUtil{
}

enum class ChatFragmentName(var str:String){
    MAIN_CHAT_FRAGMENT("MainChatFragment"),
    DETAIL_CHAT_FRAGMENT("DetailChatFragment"),
}

enum class ChatUserType{
    CHAT_USER_ME,
    CHAT_USER_YOU
}
