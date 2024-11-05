package oussama.it.backend.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import oussama.it.backend.Entities.User;
import oussama.it.backend.Entities.forgotPassword;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<forgotPassword, Long> {

    @Query("select fp from forgotPassword fp where fp.otp= ?1 and fp.user= ?2")
    Optional<forgotPassword> findByOtpAndUser(Long otp, User user);


}
