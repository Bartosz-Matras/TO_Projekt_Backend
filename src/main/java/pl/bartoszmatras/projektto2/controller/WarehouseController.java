package pl.bartoszmatras.projektto2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmatras.projektto2.dto.WarehouseModuleDto;
import pl.bartoszmatras.projektto2.entity.Warehouse;
import pl.bartoszmatras.projektto2.repository.WarehouseRepository;
import pl.bartoszmatras.projektto2.service.WarehouseService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseService warehouseService;

    @PostMapping("/warehouses")
    public Warehouse newWarehouse(@RequestBody Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    @GetMapping("/warehouses")
    public List<Warehouse> listWarehouse(){
        return warehouseRepository.findAll();
    }

    @DeleteMapping("/warehouses")
    public ResponseEntity deleteWarehouse(@RequestBody Long idWarehouse){
        warehouseRepository.deleteById(idWarehouse);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/warehouse_module_data")
    public WarehouseModuleDto getWarehouseModuleData(@RequestParam Optional<String> idWarehouse){
        if(idWarehouse.isPresent()){
            return warehouseService.getWarehouseModuleData(Long.parseLong(idWarehouse.get()));
        }else{
            return warehouseService.getWarehouseModuleData();
        }
    }

}
