
package com.web.blog.dao.user;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
    
    User getUserByEmail(String email);
    User getUserByUid(String nickName);

    User save(User user);

    Optional<User> findByUid(String uid);
    Optional<User> findByEmail(String Email);
    Optional<User> findUserByEmailAndPassword(String email, String password);
    Optional<User> findByUidAndEmail(String uid, String email);

}
