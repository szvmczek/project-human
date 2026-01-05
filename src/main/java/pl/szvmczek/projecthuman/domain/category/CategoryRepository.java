package pl.szvmczek.projecthuman.domain.category;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByIdAndUserId(Long id, Long userId);
    List<Category> findAllByUserId(Long userId);
}
