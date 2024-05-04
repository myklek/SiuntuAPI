package savitarna.siuntusavitarna.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import savitarna.siuntusavitarna.model.Package;

import java.util.List;

@Repository
public interface PackageRepository extends CrudRepository<Package, Integer>
{
    List<Package> findAllByCustomIsFalse();

    Package findById(int id);

}