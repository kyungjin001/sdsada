package com.icia.member.entity;

import com.icia.member.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {  //pk를 무조건 가지고 있어야함
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "member_id") // 별도 컬럼이름 지정할 때
    private Long id;

    // memberEmail: 크기50, unique
    @Column(length = 50, unique = true) // 유니크 값 주기
    private String memberEmail;
//ㅇㅇ
    //memberEmail: 크기 20
    @Column(length = 20) // 컬럼 크기
    private String memberPassword;

    //Column 생략하면 생략하면 default 크기가 255로 지정됨
    private String memberName;


    // 카멜케이스로 작성하면 DB에 member_email로 자동 변경됨.
    //이것이 싫다면 소문자로만 작성해야함.

    /*
        DTO 클래스 객체를 전달받아 Entity 클래스
        Entity 객체를 리턴하는 메서드 선언

        static 메서드(정적메서드): 클래스 메서드, 객체를 만들지 않고도 바로 호출 가능

     */


    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        return memberEntity;
    }





}
