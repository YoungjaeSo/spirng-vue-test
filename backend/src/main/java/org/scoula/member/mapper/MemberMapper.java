package org.scoula.member.mapper;

import org.scoula.member.dto.ChangePasswordDTO;
import org.scoula.security.account.domain.AuthVO;
import org.scoula.security.account.domain.MemberVO;

public interface MemberMapper {
    MemberVO get(String username);
    MemberVO findByUsername(String username);
    int insert(MemberVO member);
    int insertAuth(AuthVO auth);
    int update(MemberVO member);
    int updatePassword(ChangePasswordDTO changePasswordDTO);
}
