package opt.springframework.services;

import opt.springframework.domain.Product;
import opt.springframework.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 1/10/17.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product saveOrUpdateProductForm(Product productForm) {
        Product savedProduct = saveOrUpdate(productForm);
        System.out.println("Saved Product Id: " + savedProduct.getId());
        return savedProduct;
    }
}
