package com.youcode.networkstorageservice.NetworkStorage.Repository;

//import com.youcode.interplanetary.NetworkStorage.Entity.MetaData;
import com.youcode.networkstorageservice.NetworkStorage.Entity.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import java.util.UUID;

@Repository
public interface MetaRepository extends JpaRepository<MetaData, String> {

    MetaData findByUserCid(String userCid);

//    void updateByUserCid(String userCid);

//    void updateByIdNumber(String idNumber);


    @Modifying
    @Query("update MetaData u set u.userCid = :userCid where u.IdNumber = :IdNumber")
    void updateByIdNumber(@Param(value = "IdNumber") String IdNumber, @Param(value = "userCid") String userCid);

//    void updateById(UUID id);

}
