package com.hr_handlers.chat.mapper;

import com.hr_handlers.chat.dto.ChatRoomResponseDto;
import com.hr_handlers.chat.entity.ChatRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {

    // Entity -> DTO
    @Mapping(source = "id", target = "chatRoomId")  // id -> chatRoomId
    @Mapping(source = "user_count", target = "userCount")
    ChatRoomResponseDto toDto(ChatRoom chatRoom); // title은 동일한 이름이므로 자동 매핑
}
