package spring.avaliacao.spring.model;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String password;
}
