package com.platzi.platzimarket.persitence.crud;

import com.platzi.platzimarket.persitence.entity.Compra;
import com.platzi.platzimarket.persitence.entity.ComprasProducto;
import com.platzi.platzimarket.persitence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto,Integer> {
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
