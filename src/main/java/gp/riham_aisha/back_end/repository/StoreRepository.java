package gp.riham_aisha.back_end.repository;

import gp.riham_aisha.back_end.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByManagerId(Long managerId);
}
