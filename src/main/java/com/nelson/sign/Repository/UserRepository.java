package com.nelson.sign.Repository;

import com.nelson.sign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
     User getUserByNameAndPassword(String name,String password);

     User save(User user);

     Boolean deleteUserByUid(Long uid);

}
