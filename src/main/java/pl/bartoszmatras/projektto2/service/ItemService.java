package pl.bartoszmatras.projektto2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bartoszmatras.projektto2.dto.ItemSaveDto;
import pl.bartoszmatras.projektto2.entity.Item;
import pl.bartoszmatras.projektto2.entity.QuantityType;
import pl.bartoszmatras.projektto2.entity.Warehouse;
import pl.bartoszmatras.projektto2.repository.ItemRepository;
import pl.bartoszmatras.projektto2.repository.QuantityTypeRepository;
import pl.bartoszmatras.projektto2.repository.WarehouseRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final QuantityTypeRepository quantityTypeRepository;

    public Item saveItem(ItemSaveDto dto){
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(dto.getIdWarehouse());
        Optional<QuantityType> quantityTypeOptional = quantityTypeRepository.findById(dto.getIdQuantityType());
        if(!warehouseOptional.isPresent() || !quantityTypeOptional.isPresent()){
            throw new RuntimeException("Incorrect identifiers: idWarehouse: " + dto.getIdWarehouse()
                    + ", idQuantityType: " + dto.getIdQuantityType());
        }

        Warehouse warehouse = warehouseOptional.get();
        QuantityType quantityType = quantityTypeOptional.get();
        Item item = Item.of(dto);
        item.setQuantityType(quantityType);
        item.setWarehouse(warehouse);
        return itemRepository.save(item);
    }



}
