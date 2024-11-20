package com.hr_handlers.chat.entity;

import com.hr_handlers.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @EmbeddedId
    private ChatId id;

    @ManyToOne
    @JoinColumn(name = "id.chat_room_id", referencedColumnName = "id", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "id.employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
}
