package za.ac.cput.service.product;
/**  Name: Antin Williams
 *  Student Number: 219212058
 *  Group: 23
 *
 *  Class for Product Service
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.entity.product.Product;
import za.ac.cput.entity.user.Customer;
import za.ac.cput.repository.product.ProductRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private static ProductService productService = null;

    @Autowired
    private ProductRepository repository;

    @Override
    public Product create(Product product) {
        return this.repository.save(product);
    }

    @Override
    public Product read(String productId) {
        return this.repository.findById(productId).orElse(null);
    }

    @Override
    public Product update(Product product) {
        if (this.repository.existsById(product.getProductId())) {
            return this.repository.save(product);
        }
        return null;
    }

    @Override
    public void delete(String productId) {
        this.repository.deleteById(productId);
        if (this.repository.existsById(productId)) {
            System.out.println("Deleted");
        } else {
            System.out.println("Not found.");
        }
    }

    @Override
    public Set<Product> getAll()
    {
        return this.repository.findAll().stream().collect(Collectors.toSet());
    }

    public Set<Product> searchName(String searchValue) {
        Set<Product> getAllProducts = getAll();
        Set<Product> resultSet = new HashSet<>();
        for (Product product : getAllProducts) {
            String lowercaseName = product.getName().toLowerCase();
            String lowerCaseSearch = searchValue.toLowerCase();

            if (lowercaseName.contains(lowerCaseSearch)) {
                resultSet.add(product);
            }
        }
        return resultSet;
    }

    public Set<Product> searchPrice(String searchValue) {
        Set<Product> getAllProducts = getAll();
        Set<Product> resultSet = new HashSet<>();
        for (Product product : getAllProducts) {
            String expected = product.getPrice();
            String actual = searchValue;

            if (expected.contains(actual)) {
                resultSet.add(product);
            }
        }
        return resultSet;
    }
}

