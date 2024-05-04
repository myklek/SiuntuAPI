package savitarna.siuntusavitarna.service;

import org.springframework.stereotype.Service;
import savitarna.siuntusavitarna.repository.PackageRepository;
import savitarna.siuntusavitarna.model.Package;

import java.util.List;

@Service
public class PackageService
{
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository)
    {
        this.packageRepository = packageRepository;
    }

    public List<Package> findAll()
    {
        return packageRepository.findAllByCustomIsFalse();
    }


}
