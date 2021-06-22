package com.chengchw.SHEA.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chengchw.SHEA.Model.Product;
import com.chengchw.SHEA.Repository.ProductRepository;

@Service
public class ProductService {
	
	private final ProductRepository prodRepo;
	
	public ProductService(ProductRepository prodRepo) {
		
		
		this.prodRepo = prodRepo;
	}

	public List<Product> getAllProd(){
		
		return this.prodRepo.findAll();
	}
	
	public Product registerProd(Product newProduct) {
		
		return this.prodRepo.save(newProduct);
		
	}
	
	public Product findProdById(Long Id) {
		
		return this.prodRepo.findById(Id).get();
	}
	
	public void removeProd(Product deletprod) {
		
		this.prodRepo.delete(deletprod);
	}
	
	public List<Product> findcurrentbidprod(Long Id){
		
		return this.prodRepo.findByCurrentBidId(Id);
	}
	
	
	public List<Product> findcurrentbidprodNot(Long Id){
		
		return this.prodRepo.findByCurrentBidIdNot(Id);
	}
}
