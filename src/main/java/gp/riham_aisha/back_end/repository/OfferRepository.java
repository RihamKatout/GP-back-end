package gp.riham_aisha.back_end.repository;

import gp.riham_aisha.back_end.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByPublicOffer(Boolean publicOffer);
    List<Offer> findAllByStoreId(Long storeId);

}
