package kr.co.lion.mungnolza.model

// 채팅에 대한 데이터 클래스는 어떻게 구성해야할까....?
data class ChatModel(
    var chatUserNameMe:String,
    var chatUserNameYou:String,
    var chatContent:String,
    var chatDate:String,
)


