package com.nestorcastaneda.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nestorcastaneda.apirest.apirest.Entities.Producto;
import com.nestorcastaneda.apirest.apirest.Repositories.ProductoRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts(){//lista donde aparecerán todos los productos
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoRepository.findById(id).orElseThrow(
             () -> new RuntimeException("El producto con el id " + id + " no existe"));
         
    }
    

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto p){
        Producto producto = productoRepository.findById(id).orElseThrow( 
            () -> new RuntimeException("El producto con el id " + id + " no existe"));

            producto.setNombre(p.getNombre());
            producto.setPrecio(p.getPrecio());

            return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Producto producto = productoRepository.findById(id).orElseThrow( 
            () -> new RuntimeException("El producto con el id " + id + " no existe"));

            productoRepository.delete(producto);
            return "el producto con el id: " + id + "ha sido eliminado...";
    }
    

}
