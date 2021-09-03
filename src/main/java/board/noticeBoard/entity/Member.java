package board.noticeBoard.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "member")
@Builder
@ApiModel("사용자")
public class Member implements UserDetails {

    @Id
    @ApiModelProperty(value="아이디", example="user001")
    private String id;

    @Column(nullable = false)
    @ApiModelProperty(value="비밀번호", example="1234")
    private String pw;

    @Column(nullable = false)
    @ApiModelProperty(value="이름", example="홍길동")
    private String name;

    @Column(nullable = false)
    @ApiModelProperty(value="닉네임", example="abc")
    private String nickname;

    @Column(nullable = false)
    @ApiModelProperty(value="이메일", example="abc@abc.abc")
    private String email;

    @Column(nullable = false)
    @ApiModelProperty(value="핸드폰 번호", example="01011111111")
    private String phone;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}