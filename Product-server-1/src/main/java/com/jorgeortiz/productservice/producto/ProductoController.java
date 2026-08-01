package com.jorgeortiz.productservice.producto;

import com.jorgeortiz.productservice.producto.dto.ProductoRequestDTO;
import com.jorgeortiz.productservice.producto.dto.ProductoResponseDTO;
import com.jorgeortiz.productservice.producto.mapper.ProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoMapper productoMapper;

    @PostMapping("/agregar")
    public ProductoResponseDTO addProducto(@RequestBody ProductoRequestDTO request) {
        Producto producto = productoMapper.toEntity(request);
        return productoMapper.toResponse(productoService.addProducto(producto));
    }

    @GetMapping
    public List<ProductoResponseDTO> getProductos() {
        return productoService.getProductos().stream()
                .map(productoMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con id: " + id));
        return productoMapper.toResponse(producto);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<ProductoResponseDTO> getProductosByCliente(@PathVariable Long clienteId) {
        return productoService.getProductosByCliente(clienteId).stream()
                .map(productoMapper::toResponse)
                .toList();
    }

    @GetMapping("/tipo/{tipo}")
    public List<ProductoResponseDTO> getProductosByTipo(@PathVariable TipoProducto tipo) {
        return productoService.getProductosByTipo(tipo).stream()
                .map(productoMapper::toResponse)
                .toList();
    }

    @GetMapping("/activos")
    public List<ProductoResponseDTO> getProductosActivos() {
        return productoService.getProductosActivos().stream()
                .map(productoMapper::toResponse)
                .toList();
    }

    @PutMapping("/actualizar/{id}")
    public ProductoResponseDTO updateProducto(@PathVariable Long id, @RequestBody ProductoRequestDTO request) {
        Producto datosActualizados = productoMapper.toEntity(request);
        return productoMapper.toResponse(productoService.updateProducto(id, datosActualizados));
    }

    @DeleteMapping("/eliminar/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteById(id);
    }
}
