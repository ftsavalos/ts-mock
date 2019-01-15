package gr.eurobank.transactions.esbmock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h.id from History h where h.requestId = :requestId")
    Long findIdByRequestId(@Param("requestId") String requestId);
}
