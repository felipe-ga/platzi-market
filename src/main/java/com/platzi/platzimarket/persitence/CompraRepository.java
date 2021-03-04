package com.platzi.platzimarket.persitence;

import com.platzi.platzimarket.domain.Purchase;
import com.platzi.platzimarket.domain.repository.PurchaseRepository;
import com.platzi.platzimarket.persitence.crud.CompraCrudRepository;
import com.platzi.platzimarket.persitence.entity.Compra;
import com.platzi.platzimarket.persitence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {
    @Autowired
    public CompraCrudRepository compraCrudRespository;

    @Autowired
    public PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRespository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRespository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudRespository.save(compra));
    }
}
