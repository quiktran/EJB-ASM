package aptech.fpt.spring.model;

import aptech.fpt.spring.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserModel extends PagingAndSortingRepository<User, Integer> {

    @Query("select u from User u where u.username = :username and u.password = :password")
    User checkLogin(@Param("username") String username,@Param("password") String password);

    @Query("select u from User u where u.username like concat('%', :username, '%') ")
    Page<User> findByUsername(@Param("username") String username, Pageable pageable);
}
