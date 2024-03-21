package com.init.products.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.products.dao.ProductsDAO;
import com.init.products.entitys.Product;
 



@RestController
@RequestMapping("products")
public class ProductsREST {
	
	
	//prueba 1
	/*
	//@GetMapping
	@RequestMapping(value="hello",method=RequestMethod.GET)
	public String hello() {
		return "Hello Word";
	}
	*/
	
	//prueba 2
	/*
	@GetMapping
	public ResponseEntity<Product> getProduct()
	{
		Product product = new Product();
		product.setId(1L);
		product.setName("Producto 1");
		return ResponseEntity.ok(product);
	}
	*/
	//Ahora queremos leer los productos desde una base de datos
	@Autowired
	private ProductsDAO productDAO;
	
	
	@GetMapping
	public ResponseEntity<List<Product>> getProduct()
	{
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}
	
	
	//queremos consultarle solo un producto
	
	@RequestMapping(value="{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId)
	{
		Optional<Product> optionalProduct = productDAO.findById(productId);
		if(optionalProduct.isPresent()) {
			return ResponseEntity.ok(optionalProduct.get()); 
		}else {
		return ResponseEntity.noContent().build();
		}
	}
	
	//si queremos hacer un servicio para insertar un nuevo valor a la base de datos. 
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody  Product product)
	{
		Product newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct); 
	}
	
	//Ahora vamos a hacer un método para borrar un producto. 
	@DeleteMapping(value="{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId)
	{
		productDAO.deleteById(productId);
		return ResponseEntity.ok(null); 
	}
	
	//Ahora haremos una actualización de nuestro producto. El nombre no el id 
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Optional<Product> optionalProduct = productDAO.findById(product.getId());
		if(optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		}
		else {
		return ResponseEntity.notFound().build();
		}
	}
	
}
